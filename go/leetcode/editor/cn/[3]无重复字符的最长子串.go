package leetcode

// leetcode submit region begin(Prohibit modification and deletion)
func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
func lengthOfLongestSubstring(s string) int {
	var window = make(map[byte]int)
	var left = 0
	var right = 0
	var res = 0

	for right < len(s) {
		var c = s[right]
		right++
		window[c]++
		for window[c] > 1 {
			var d = s[left]
			left++
			window[d]--
		}
		res = max(res, right-left)
	}
	return res
}

//leetcode submit region end(Prohibit modification and deletion)
