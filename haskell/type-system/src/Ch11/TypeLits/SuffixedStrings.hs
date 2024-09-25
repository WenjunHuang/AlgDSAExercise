{-# LANGUAGE DataKinds #-}
{-# LANGUAGE KindSignatures #-}
{-# LANGUAGE ExplicitForAll #-}
{-# LANGUAGE ScopedTypeVariables #-}

module Ch11.TypeLits.SuffixedStrings where
import GHC.TypeLits
import Data.Data (Proxy(..))

data SuffixedString (suffix :: Symbol) = SS String

suffixed :: String -> SuffixedString suffix
suffixed s = SS s

asString :: forall suffix. KnownSymbol suffix => SuffixedString suffix -> String
asString (SS str) = str <> "@" <> symbolVal (Proxy :: Proxy suffix)
