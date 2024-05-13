package leetcode.editor.cn
object ThreeSum {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def threeSum(nums: Array[Int]): List[List[Int]] =
      var res = List[List[Int]]()
      nums.sortInPlace()
      nSum(nums, 0, 3, 0)

    private def nSum(nums: Array[Int], start: Int, n: Int, target: Int): List[List[Int]] =
      if n < 2 || nums.length < n then Nil
      else if n == 2 then
        var res = List[List[Int]]()
        var p1 = start
        var p2 = nums.length - 1
        while p1 < p2 do
          val left = nums(p1)
          val right = nums(p2)
          val sum = left + right
          if sum < target then while (p1 < p2 && nums(p1) == left) p1 += 1
          else if sum > target then while (p1 < p2 && nums(p2) == right) p2 -= 1
          else
            res = List(left, right) :: res
            while (p1 < p2 && nums(p1) == left) p1 += 1
            while (p1 < p2 && nums(p2) == right) p2 -= 1

        res
      else
        var res = List[List[Int]]()
        for (i <- start until nums.length if i == start || nums(i - 1) != nums(i)) do
          nSum(nums, i + 1, n - 1, target - nums(i))
            .foreach(t => res = (nums(i) +: t) :: res)
        res
  }
//leetcode submit region end(Prohibit modification and deletion)

}
