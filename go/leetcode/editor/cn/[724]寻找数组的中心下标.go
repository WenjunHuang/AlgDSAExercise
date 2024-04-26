package leetcode

// leetcode submit region begin(Prohibit modification and deletion)
func pivotIndex(nums []int) int {
	var preSums = make([]int, len(nums)+1)
	preSums[0] = 0
	for i := 1; i < len(preSums); i++ {
		preSums[i] = preSums[i-1] + nums[i-1]
	}

	for j := 0; j < len(nums); j++ {
		if preSums[j] == preSums[len(nums)]-preSums[j+1] {
			return j
		}
	}

	return -1

}

//leetcode submit region end(Prohibit modification and deletion)
