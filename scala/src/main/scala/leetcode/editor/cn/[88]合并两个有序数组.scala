package leetcode.editor.cn
object MergeSortedArray {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def merge(nums1: Array[Int], m: Int, nums2: Array[Int], n: Int): Unit = {
      var p1 = m - 1
      var p2 = n - 1
      var p = nums1.length - 1

      while p1 >= 0 && p2 >= 0 do
        if nums1(p1) > nums2(p2)
        then
          nums1(p) = nums1(p1)
          p1 -= 1
        else
          nums1(p) = nums2(p2)
          p2 -= 1

        p -= 1

      while p2 >= 0 do
        nums1(p) = nums2(p2)
        p2 -= 1
        p -= 1
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
