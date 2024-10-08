{-# LANGUAGE DuplicateRecordFields #-}
{-# LANGUAGE FlexibleInstances #-}
{-# LANGUAGE UndecidableInstances #-}

module Main where

import Control.Monad
import Data.Array
import Data.Bits
import Data.List
-- import Data.List.Split
import Data.Set
import Data.Text
import Debug.Trace
import System.Environment
import System.IO
import System.IO.Unsafe

--
-- Complete the 'nonDivisibleSubset' function below.
--
-- The function is expected to return an INTEGER.
-- The function accepts following parameters:
--  1. INTEGER k
--  2. INTEGER_ARRAY s
--

nonDivisibleSubset :: Int -> [Int] -> Int
nonDivisibleSubset k s = 0


lstrip = Data.Text.unpack . Data.Text.stripStart . Data.Text.pack

rstrip = Data.Text.unpack . Data.Text.stripEnd . Data.Text.pack

main :: IO ()
main = do
  stdout <- getEnv "OUTPUT_PATH"
  fptr <- openFile stdout WriteMode

  firstMultipleInputTemp <- getLine
  let firstMultipleInput = Data.List.words $ rstrip firstMultipleInputTemp

  let n = read (firstMultipleInput !! 0) :: Int

  let k = read (firstMultipleInput !! 1) :: Int

  sTemp <- getLine

  let s = Data.List.map (read :: String -> Int) . Data.List.words $ rstrip sTemp

  let result = nonDivisibleSubset k s

  hPutStrLn fptr $ show result

  hFlush fptr
  hClose fptr
