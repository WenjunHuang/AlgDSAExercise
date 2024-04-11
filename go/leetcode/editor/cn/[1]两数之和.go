package leetcode

import "sort"

// leetcode submit region begin(Prohibit modification and deletion)
func twoSum(nums []int, target int) []int {
	pairs := make([][2]int, len(nums))
	for i, num := range nums {
		pairs[i] = [2]int{i, num}
	}
	sort.Slice(pairs, func(i int, j int) bool {
		return pairs[i][1] < pairs[j][1]
	})
	l, r := 0, len(pairs)-1
	for l < r {
		if pairs[l][1]+pairs[r][1] == target {
			return []int{pairs[l][0], pairs[r][0]}
		} else if pairs[l][1]+pairs[r][1] < target {
			l = l + 1
		} else {
			r = r - 1
		}
	}
	return []int{-1, -1}
}

//leetcode submit region end(Prohibit modification and deletion)
