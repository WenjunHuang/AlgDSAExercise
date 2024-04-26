import qualified Data.Set as Set
import qualified Data.Sequence as Seq
import Data.Char (intToDigit, digitToInt)

type Queue = Seq.Seq
type Set = Set.Set

openLock :: [String] -> String -> Int
openLock deadends target = bfs (Seq.singleton "0000") (Set.fromList deadends) Set.empty 0
  where
    bfs :: Queue String -> Set String -> Set String -> Int -> Int
    bfs queue deads visited steps
      | Seq.null queue = -1
      | otherwise =
          let (cur, queue') = (Seq.index queue 0, Seq.drop 1 queue)
          in if Set.member cur deads || Set.member cur visited
            then bfs queue' deads visited steps
            else if cur == target
              then steps
              else let visited' = Set.insert cur visited
                       queue'' = foldl (\acc x -> acc Seq.|> x) queue' (nextCombinations cur)
                   in bfs queue'' deads visited' (steps + 1)

    nextCombinations :: String -> [String]
    nextCombinations s = [plusOne s i | i <- [0..3]] ++ [minusOne s i | i <- [0..3]]

    plusOne :: String -> Int -> String
    plusOne s j = let (front, back) = splitAt j s
                      c = back !! 0
                  in front ++ [if c == '9' then '0' else intToDigit ((digitToInt c) + 1)] ++ drop 1 back

    minusOne :: String -> Int -> String
    minusOne s j = let (front, back) = splitAt j s
                       c = back !! 0
                   in front ++ [if c == '0' then '9' else intToDigit ((digitToInt c) - 1)] ++ drop 1 back