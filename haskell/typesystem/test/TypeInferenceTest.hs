module TypeInferenceTest where

import Test.HUnit
import TypeInference

varQuantify1 :: Test
varQuantify1 = TestCase (assertEqual "should return 0 gens" (Forall 0 (TVar (Tyvar "a"))) (quantify [] (TVar (Tyvar "a"))))

varQuantify2 :: Test
varQuantify2 =
  TestCase
    (assertEqual "should return 1 gen and TGen with 0" (Forall 1 (TGen 0)) (quantify [Tyvar "a"] (TVar (Tyvar "a"))))

applicationQuantifyWithMultipleVars :: Test
applicationQuantifyWithMultipleVars =
  TestCase
    ( do
        let tvars = [Tyvar "a", Tyvar "b", Tyvar "c"]
        let type_ = TAp (TAp tArrow (TVar (Tyvar "a"))) (TVar (Tyvar "b"))
        let Forall n tpe = quantify tvars type_
        assertEqual "should return 2 gens" 2 n
        assertEqual "should return (a -> b)" (TAp (TAp tArrow (TGen 0)) (TGen 1)) tpe
    )

appWithOneVar :: Test
appWithOneVar =
  TestCase
    (assertEqual "should return 1 gen and TGen 0 -> TGen 0" (Forall 1 (TAp (TAp tArrow (TGen 0)) (TGen 0))) (quantify [Tyvar "a"] (TAp (TAp tArrow (TVar (Tyvar "a"))) (TVar (Tyvar "a")))))

identity' :: Expr
identity' = Abs "x" (Var "x")

letIdentity :: Expr
letIdentity = Let (Binding "id" identity') (Ap (Var "id") (Var "id"))

letType :: Test
letType =
  TestCase
    ( do
        let tpe = runTI (tiExpr [] letIdentity)
        assertEqual "should return (a -> a)" (TAp (TAp tArrow (TVar (Tyvar "$t_2"))) (TVar (Tyvar "$t_2"))) tpe
    )

testQuantify :: Test
testQuantify =
  TestList
    [ varQuantify1,
      varQuantify2,
      appWithOneVar,
      applicationQuantifyWithMultipleVars,
      letType
    ]