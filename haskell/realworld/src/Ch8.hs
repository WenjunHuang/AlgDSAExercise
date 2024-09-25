module Ch8 where

import qualified Data.ByteString.Lazy as L
import qualified Data.ByteString.Lazy.Char8 as LC

hasElfMagic :: L.ByteString -> Bool
hasElfMagic content = L.take 4 content == elfMagic
  where
    elfMagic = L.pack [0x7f, 0x45, 0x4c, 0x46]

isElfFile :: FilePath -> IO Bool
isElfFile path = do
  content <- L.readFile path
  return (hasElfMagic content)

readPrice :: L.ByteString -> Maybe Int
readPrice str =
  case LC.readInt str of
    Nothing -> Nothing
    Just (dollars, rest) ->
      case LC.readInt (L.tail rest) of
        Nothing -> Nothing
        Just (cents, more) ->
          Just (dollars * 100 + cents)

closing :: L.ByteString -> Maybe Int
closing = readPrice . (!! 4) . LC.split ','

highestClose :: L.ByteString -> Maybe Int
highestClose = maximum . (Nothing :) . map closing . LC.lines

highestCloseFrom :: FilePath -> IO ()
highestCloseFrom path = do
  contents <- L.readFile path
  print (highestClose contents)
