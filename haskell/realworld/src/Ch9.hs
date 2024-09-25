module Ch9 where

import Control.Exception (bracket, handle)
import Control.Monad (filterM, forM)
import Data.Time.Clock (UTCTime (..))
import System.Directory (Permissions (..), doesDirectoryExist, getDirectoryContents, getModificationTime, getPermissions)
import System.FilePath ((</>))
import System.IO (hClose, openFile,IOMode(..),hFileSize, withFile)

getRecursiveContents :: FilePath -> IO [FilePath]
getRecursiveContents topdir = do
  names <- getDirectoryContents topdir
  let properNames = filter (`notElem` [".", ".."]) names
  paths <- forM properNames $ \name -> do
    let path = topdir </> name
    isDirectory <- doesDirectoryExist path
    if isDirectory
      then getRecursiveContents path
      else return [path]
  return (concat paths)

type Predicate =
  FilePath ->
  Permissions ->
  Maybe Integer ->
  UTCTime ->
  Bool

simpleFind :: (FilePath -> Bool) -> FilePath -> IO [FilePath]
simpleFind p path = do
  names <- getRecursiveContents path
  return (filter p names)

betterFind :: Predicate -> FilePath -> IO [FilePath]
betterFind p path = getRecursiveContents path >>= filterM check
  where
    check name = do
      perms <- getPermissions name
      size <- getFileSize name
      modified <- getModificationTime name
      return (p name perms size modified)

getFileSize :: FilePath -> IO (Maybe Integer)
getFileSize path = handle ((\_ -> return Nothing) :: IOError -> IO (Maybe Integer)) $
  withFile path ReadMode $ \h -> do
    size <- hFileSize h
    return (Just size)