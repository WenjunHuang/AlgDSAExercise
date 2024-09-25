{-# LANGUAGE AllowAmbiguousTypes #-}
{-# LANGUAGE GeneralizedNewtypeDeriving #-}
{-# LANGUAGE ScopedTypeVariables #-}
{-# LANGUAGE TypeApplications #-}

module Ch11.Temperature.UnitNameTypeApps where

newtype Temp unit = Temp Double deriving (Num, Fractional)

data C

data F

class UnitName u where
  unitName :: String

instance UnitName C where
  unitName = "C"

instance UnitName F where
  unitName = "F"

instance (UnitName u) => Show (Temp u) where
  show (Temp t) = show t <> "°" <> unitName @u

unit :: forall u. (UnitName u) => Temp u -> String
unit _ = unitName @u

