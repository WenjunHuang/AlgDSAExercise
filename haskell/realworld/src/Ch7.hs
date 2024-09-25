module Ch7 where

import Control.Exception
import Data.Char (toUpper)
import System.Directory
import System.FilePath
import System.IO
import System.IO.Error

mainLoop :: Handle -> Handle -> IO ()
mainLoop inh outh = do
  ineof <- hIsEOF inh
  if ineof
    then return ()
    else do
      inpStr <- hGetLine inh
      hPutStrLn outh (map toUpper inpStr)
      mainLoop inh outh

myCopyFile :: IO ()
myCopyFile = do
  path <- getCurrentDirectory
  inh <- openFile (path </> "input.txt") ReadMode
  outh <- openFile (path </> "temp" </> "output.txt") ReadWriteMode
  mainLoop inh outh
  hClose inh
  hClose outh

myPutStrLn :: IO ()
myPutStrLn = hPutStrLn stdout "Hello, World!"

withTempFile :: String -> (FilePath -> Handle -> IO a) -> IO a
withTempFile pattern func = do
  tempdir <- catchIOError getTemporaryDirectory (const getCurrentDirectory)
  (tempfile, temph) <- openTempFile tempdir pattern
  finally
    (func tempfile temph)
    ( do
        hClose temph
        removeFile tempfile
    )

myAction :: FilePath -> Handle -> IO ()
myAction tempname temph = do
  putStrLn "Welcome to tempfile.hs"
  putStrLn $ "I have a temporary file at " ++ tempname
  pos <- hTell temph
  putStrLn $ "My initial position is " ++ show pos

  let tempdata = show [1 .. 10]
  putStrLn $ "Writing one line contain " ++ show (length tempdata) ++ " bytes: " ++ tempdata
  hPutStrLn temph tempdata

  pos <- hTell temph
  putStrLn $ "After writing, my new position is " ++ show pos

  putStrLn $ "The file content is: "
  hSeek temph AbsoluteSeek 0

  c <- hGetContents temph
  putStrLn c

  putStrLn "Which could be expressed as this Haskell literal:"
  print c

workWithTempFile :: IO ()
workWithTempFile = withTempFile "mytemp.txt" myAction

myInteract :: IO ()
myInteract = interact ((++) "Your data, in uppercase, is:\n\n" . map toUpper)