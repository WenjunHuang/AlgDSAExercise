package leetcode.editor.cn
object MoveZeroes {
//leetcode submit region begin(Prohibit modification and deletion)
object Solution {
    def moveZeroes(nums: Array[Int]): Unit = {
      var slow = 0
      var fast = 0
      while (fast < nums.length) {
        if (nums(fast) != 0){
          nums(slow) = nums(fast)
          slow += 1
        }
        fast += 1
      }

      for (i <- slow until nums.length) nums(i) = 0
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
