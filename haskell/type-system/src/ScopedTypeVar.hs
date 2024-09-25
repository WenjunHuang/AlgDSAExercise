{-# LANGUAGE ExistentialQuantification #-}
{-# LANGUAGE ScopedTypeVariables #-}

module ScopedTypeVar where

data T = forall a. MkT [a]

k :: T -> T
k (MkT [t :: a]) = MkT t3
  where
    (t3 :: [a]) = [t, t, t]