{-# LANGUAGE TypeFamilies #-}
module Ch11.TypeFamilies.XListable where

data family XList a

newtype instance XList () = XListUnit Integer
data instance XList Bool = XBits Integer Integer