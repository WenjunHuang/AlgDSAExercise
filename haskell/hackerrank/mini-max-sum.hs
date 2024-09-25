{-# LANGUAGE DuplicateRecordFields, FlexibleInstances, UndecidableInstances #-}

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
import Prelude (sum, show, putStrLn, read, Int, String)

--
-- Complete the 'miniMaxSum' function below.
--
-- The function accepts INTEGER_ARRAY arr as parameter.
--

miniMaxSum arr = do
    -- Write your code here
    let sorted = sort arr
    let minSum = sum (Data.List.init sorted)
    let maxSum = sum (Data.List.tail sorted)
    putStrLn $ show minSum ++ "  " ++ show maxSum

lstrip = Data.Text.unpack . Data.Text.stripStart . Data.Text.pack
rstrip = Data.Text.unpack . Data.Text.stripEnd . Data.Text.pack

main :: IO()
main = do

    arrTemp <- getLine

    let arr = Data.List.map (read :: String -> Int) . Data.List.words $ rstrip arrTemp

    miniMaxSum arr
