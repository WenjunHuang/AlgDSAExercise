{-# LANGUAGE DuplicateRecordFields, FlexibleInstances, UndecidableInstances #-}

module Main where

import Control.Monad
import Data.Array
import Data.Bits
import Data.List
import Data.Set
import Data.Text
import Debug.Trace
import System.Environment
import System.IO
import System.IO.Unsafe
import Text.Printf (printf)

--
-- Complete the 'plusMinus' function below.
--
-- The function accepts INTEGER_ARRAY arr as parameter.
--

plusMinus arr = do
  let n = Data.List.length arr
  let pos = Data.List.length $ Data.List.filter (> 0) arr
  let neg = Data.List.length $ Data.List.filter (< 0) arr
  let zeros = Data.List.length $ Data.List.filter (== 0) arr
  print (fromIntegral pos / fromIntegral n)
  print (fromIntegral neg / fromIntegral n)
  print (fromIntegral zeros / fromIntegral n)
    -- Write your code here

lstrip = Data.Text.unpack . Data.Text.stripStart . Data.Text.pack
rstrip = Data.Text.unpack . Data.Text.stripEnd . Data.Text.pack

main :: IO()
main = do
    nTemp <- getLine
    let n = read $ lstrip $ rstrip nTemp :: Int

    arrTemp <- getLine

    let arr = Data.List.map (read :: String -> Int) . Data.List.words $ rstrip arrTemp

    plusMinus arr
