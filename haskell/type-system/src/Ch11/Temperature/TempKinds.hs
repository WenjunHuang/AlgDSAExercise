{-# LANGUAGE DataKinds #-}
{-# LANGUAGE GeneralizedNewtypeDeriving #-}
{-# LANGUAGE KindSignatures #-}
{-# LANGUAGE AllowAmbiguousTypes #-}

module Ch11.Temperature.TempKinds where


data TempUnits = F | C

newtype Temp (u :: TempUnits) = Temp Double deriving (Num, Fractional,Show)

paperBurning :: Temp F
paperBurning = 451

absoluteZero :: Temp C
absoluteZero = -273.15

f2c :: Temp F -> Temp C
f2c (Temp f) = Temp ((f - 32) * 5 / 9)

class UnitName (u :: TempUnits) where
  unitName :: String