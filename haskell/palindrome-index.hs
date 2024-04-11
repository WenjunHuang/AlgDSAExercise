{-# LANGUAGE DuplicateRecordFields #-}
{-# LANGUAGE FlexibleInstances #-}
{-# LANGUAGE UndecidableInstances #-}

import Control.Monad
import Data.Array
import Data.Bits
import Data.Text
import Data.List
import Data.Set
import Debug.Trace
import System.Environment
import System.IO
import System.IO.Unsafe
import qualified Data.ByteString.Char8 as BS

--
-- Complete the 'palindromeIndex' function below.
--
-- The function is expected to return an INTEGER.
-- The function accepts STRING s as parameter.
--

isPalindrome :: BS.ByteString -> Bool
isPalindrome str = go 0 (BS.length str - 1)
  where
    go :: Int -> Int -> Bool
    go left right
      | left >= right = True
      | BS.index str left /= BS.index str right = False
      | otherwise = go (left + 1) (right - 1)

palindromeIndex :: BS.ByteString -> Int
palindromeIndex s =
  if isPalindrome s
    then -1
    else go 0 (BS.length s - 1)
  where
    go :: Int -> Int -> Int
    go left right
      | left > right = -1
      | BS.index s left == BS.index s right = go (left + 1) (right - 1)
      | otherwise =
          if isPalindrome (BS.take left s <> BS.drop (left + 1) s)
            then left
            else right

lstrip = Data.Text.unpack . Data.Text.stripStart . Data.Text.pack

rstrip = Data.Text.unpack . Data.Text.stripEnd . Data.Text.pack

main :: IO ()
main = do
  stdout <- getEnv "OUTPUT_PATH"
  fptr <- openFile stdout WriteMode

  qTemp <- getLine
  let q = read $ rstrip $ lstrip qTemp :: Int

  forM_ [1 .. q] $ \q_itr -> do
    s <- getLine

    let result = palindromeIndex (BS.pack $ lstrip $ rstrip s)

    hPrint fptr result

  hFlush fptr
  hClose fptr
