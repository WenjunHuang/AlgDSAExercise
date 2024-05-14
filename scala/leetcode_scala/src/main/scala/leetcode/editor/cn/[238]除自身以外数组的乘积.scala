package leetcode.editor.cn
object ProductOfArrayExceptSelf {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def productExceptSelf(nums: Array[Int]): Array[Int] = {
      // 前缀积，prefix[i]是nums[0..i]的积
      val prefix = Array.fill[Int](nums.length)(0)
      // 后缀积，suffix[i]是nums[i..n-1]的积
      val suffix = Array.fill[Int](nums.length)(0)

      prefix(0) = nums(0)
      suffix(nums.length - 1) = nums.last
      for (i <- 1 until nums.length)
        prefix(i) = prefix(i - 1) * nums(i)
        suffix(nums.length - i - 1) = suffix(nums.length - i) * nums(nums.length - i - 1)

//      for (j <- nums.length - 2 to 0 by -1)
//        suffix(j) = suffix(j + 1) * nums(j)

      // 计算结果
      val res = Array.fill[Int](nums.length)(0)
      res(0) = suffix(1)
      res(nums.length - 1) = prefix(nums.length - 2)
      for (k <- 1 until nums.length - 1)
        res(k) = prefix(k - 1) * suffix(k + 1)
      res
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
