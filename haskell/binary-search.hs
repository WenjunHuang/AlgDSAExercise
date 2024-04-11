search :: (Ord a) => a -> [a] -> Int
search = rightSearch

normalSearch :: (Ord a) => a -> [a] -> Int
normalSearch target nums = go 0 (length nums - 1)
  where
    go :: Int -> Int -> Int
    go lo hi
      | lo > hi = -1
      | otherwise =
          let mid = lo + (hi - lo) `div` 2
           in if nums !! mid == target
                then mid
                else
                  if nums !! mid < target
                    then go (mid + 1) hi
                    else go lo (mid - 1)

leftSearch :: (Ord a) => a -> [a] -> Int
leftSearch target nums
  | r < 0 || r >= length nums - 1 = -1
  | nums !! r /= target = -1
  | otherwise = r
  where
    go :: Int -> Int -> Int
    go leftInc rightInc
      | leftInc > rightInc = leftInc
      | otherwise =
          let mid = leftInc + (rightInc - leftInc) `div` 2
           in (if (nums !! mid == target) || (nums !! mid > target) then go leftInc (mid - 1) else go (mid + 1) rightInc)
    r = go 0 (length nums - 1)

rightSearch :: (Ord a) => a -> [a] -> Int
rightSearch target nums
  | r < 0 || r >= l - 1 = -1
  | nums !! r /= target = -1
  | otherwise = r
  where
    go :: Int -> Int -> Int
    go leftInc rightInc
      | leftInc > rightInc = rightInc
      | otherwise =
          let mid = leftInc + (rightInc - leftInc) `div` 2
           in (if (nums !! mid == target) || (nums !! mid < target) then go (mid + 1) rightInc else go leftInc (mid - 1))
    l = length nums
    r = go 0 (l - 1)

main = do
  print $ search 5 [1, 2, 3, 4, 5, 5, 7, 8, 9, 10]