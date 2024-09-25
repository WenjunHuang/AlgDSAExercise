module TypeInference where

import Control.Monad
import Data.Functor
import Data.List (nub, union)
import GHC.OldList ((\\))

data Literal
  = LitInt Integer
  | LitChar Char
  | LitRat Rational
  | LitStr String

type Id = String

newtype Tycon = Tycon Id deriving (Eq, Ord, Show)

newtype Tyvar = Tyvar Id deriving (Eq, Ord, Show)

data Type
  = TVar Tyvar
  | TCon Tycon
  | TAp Type Type
  | TGen Int
  deriving (Eq, Ord, Show)

newtype TI a = TI (Subst -> Int -> (Subst, Int, a))

instance Functor TI where
  fmap = liftM

instance Applicative TI where
  pure x = TI (\s n -> (s, n, x))
  (<*>) = ap

instance Monad TI where
  TI f >>= g =
    TI
      ( \s n -> case f s n of
          (s', m, x) -> let TI gx = g x in gx s' m
      )

runTI :: TI Type -> Type 
runTI (TI f) = apply s x 
  where (s, n, x) = f nullSubst 0

tUnit = TCon (Tycon "()")

tChar = TCon (Tycon "Char")

tInt = TCon (Tycon "Int")

tInteger = TCon (Tycon "Integer")

tFloat = TCon (Tycon "Float")

tDouble = TCon (Tycon "Double")

tList = TCon (Tycon "[]")

tArrow = TCon (Tycon "(->)")

tTuple2 = TCon (Tycon "(,)")

list :: Type -> Type
list = TAp tList

tString :: Type
tString = list tChar

infixr 4 `fn`

fn :: Type -> Type -> Type
a `fn` b = TAp (TAp tArrow a) b

pair :: Type -> Type -> Type
pair a b = TAp (TAp tTuple2 a) b

data Assump = Id :>: Scheme deriving (Eq, Show)

find :: (Monad m) => Id -> [Assump] -> m Scheme
find i [] = error $ "unbound identifier: " ++ i
find i ((i' :>: t) : as)
  | i == i' = return t
  | otherwise = find i as

toScheme :: Type -> Scheme
toScheme t = Forall 0 t

quantify :: [Tyvar] -> Type -> Scheme
quantify vs t = Forall (length s) (apply s t)
  where
    vs' = [v | v <- tv t, v `elem` vs]
    s = zip vs' (map TGen [0 ..])

tiLit :: Literal -> TI Type
tiLit (LitChar _) = return tChar
tiLit (LitInt _) = return tInteger
tiLit (LitStr _) = return tString
tiLit (LitRat _) = return tFloat

type Impl = (Id,Expr)
type BindGroup = [[Impl]]

data Expr
  = Var Id
  | Lit Literal
  | Const Assump
  | Ap Expr Expr
  | Abs Id Expr
  | Let BindGroup Expr

data Binding = Binding Id Expr

type Infer e t = [Assump] -> e -> TI t

newTVar :: TI Type
newTVar =
  TI
    ( \s n ->
        let v = Tyvar (enumId n)
         in (s, n + 1, TVar v)
    )

enumId :: Int -> Id
enumId n = "$t_" ++ show n

tiExpr :: Infer Expr Type
tiExpr as (Var i) = do
  sc <- find i as
  freshInst sc
tiExpr as (Lit l) = tiLit l
tiExpr _ (Const (_ :>: sc)) = freshInst sc
tiExpr as (Ap e f) = do
  te <- tiExpr as e
  tf <- tiExpr as f
  t <- newTVar
  unify (tf `fn` t) te
  return t
tiExpr as (Abs i e) = do
  t <- newTVar
  te <- tiExpr ((i :>: toScheme t) : as) e
  return $ t `fn` te
tiExpr as (Let binds e) = do
  as' <- tiBindGroup as binds
  tiExpr (as' ++ as) e

tiBinding :: Infer Binding [Assump]
tiBinding as (Binding name expr) =
  do
    t <- tiExpr as expr
    sub <- getSubst
    let t' = apply sub t
    let typeVars = tv t' \\ tv (apply sub as)
    let sc = quantify typeVars t'
    return [name :>: sc]

tiProgram :: Infer Expr Type
tiProgram as e = do
  t <- tiExpr as e
  sub <- getSubst
  return $ apply sub t

type Subst = [(Tyvar, Type)]

nullSubst :: Subst
nullSubst = []

(+->) :: Tyvar -> Type -> Subst
u +-> t = [(u, t)]

infixr 4 @@

(@@) :: Subst -> Subst -> Subst
s1 @@ s2 = [(u, apply s1 t) | (u, t) <- s2] ++ s1

getSubst :: TI Subst
getSubst = TI (\s n -> (s, n, s))

extSubst :: Subst -> TI ()
extSubst s' = TI (\s n -> (s' @@ s, n, ()))

data Scheme = Forall Int Type deriving (Eq, Show)

class Types t where
  apply :: Subst -> t -> t
  tv :: t -> [Tyvar]

instance Types Scheme where
  apply s (Forall n t) = Forall n (apply s t)
  tv (Forall _ t) = tv t

instance Types Type where
  apply s (TVar u) =
    case lookup u s of
      Just t -> t
      Nothing -> TVar u
  apply s (TAp l r) = TAp (apply s l) (apply s r)
  apply _ t = t

  tv (TVar u) = [u]
  tv (TAp l r) = tv l `union` tv r
  tv (TCon _) = []
  tv (TGen _) = []

instance (Types a) => Types [a] where
  apply s = map (apply s)
  tv = nub . concatMap tv

instance Types Assump where
  apply s (i :>: sch) = i :>: apply s sch
  tv (_ :>: sch) = tv sch

varBind :: (Monad m) => Tyvar -> Type -> m Subst
varBind u t
  | t == TVar u = return nullSubst
  | u `elem` tv t = error "occurs check fails"
  | otherwise = return (u +-> t)

mgu :: (Monad m) => Type -> Type -> m Subst
mgu (TAp l r) (TAp l' r') =
  do
    s1 <- mgu l l'
    s2 <- mgu (apply s1 r) (apply s1 r')
    return (s2 @@ s1)
mgu (TVar u) t = varBind u t
mgu t (TVar u) = varBind u t
mgu (TCon tc1) (TCon tc2) | tc1 == tc2 = return nullSubst
mgu t1 t2 = error "types do not unify"

unify :: Type -> Type -> TI ()
unify t1 t2 =
  do
    s <- getSubst
    u <- mgu (apply s t1) (apply s t2)
    extSubst u

class Instantiate t where
  inst :: [Type] -> t -> t

freshInst :: Scheme -> TI Type
freshInst (Forall n t) = do
  ts <- mapM (const newTVar) [1 .. n]
  return (inst ts t)

instance Instantiate Type where
  inst ts (TAp l r) = TAp (inst ts l) (inst ts r)
  inst ts (TGen n) = ts !! n
  inst _ t = t
