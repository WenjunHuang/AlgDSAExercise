{-# LANGUAGE DataKinds #-}
{-# LANGUAGE ExplicitForAll #-}
{-# LANGUAGE KindSignatures #-}
{-# LANGUAGE ScopedTypeVariables #-}
{-# LANGUAGE TypeApplications #-}

module Ch11.TypeLits.Pointers where

import Data.Data
import GHC.TypeLits

newtype Pointer (align :: Natural) = Pointer Integer

zeroPtr :: Pointer n
zeroPtr = Pointer 0

inc :: Pointer align -> Pointer align
inc (Pointer p) = Pointer (p + 1)

ptrValue :: forall align. (KnownNat align) => Pointer align -> Integer
ptrValue (Pointer p) = p * natVal @align @Proxy Proxy

maybePtr :: forall align. (KnownNat align) => Integer -> Maybe (Pointer align)
maybePtr p
  | remainder == 0 = Just (Pointer quotient)
  | otherwise = Nothing
  where
    (quotient, remainder) = divMod p (natVal (Proxy :: Proxy align))