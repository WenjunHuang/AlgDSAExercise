package leetcode.editor.cn
object RemoveElement {
//leetcode submit region begin(Prohibit modification and deletion)
object Solution {
    def removeElement(nums: Array[Int], `val`: Int): Int = {
      var slow = 0
      var fast = 0
      while (fast < nums.length) {
        if (nums(fast) != `val`){
          nums(slow) = nums(fast)
          slow += 1
        }
        fast += 1
      }
      slow
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
