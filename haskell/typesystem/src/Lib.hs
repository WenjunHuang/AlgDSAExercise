module Lib
    ( someFunc
    ) where

someFunc :: IO ()
someFunc = putStrLn "someFunc"

data CrazyList a = End | List a (CrazyList [a])

crazyListSize :: CrazyList a -> Int
crazyListSize End = 0
crazyListSize (List _ rest) = 1 + crazyListSize rest
