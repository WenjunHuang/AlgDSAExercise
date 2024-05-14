package leetcode.editor.cn
object GouJianChengJiShuZuLcof {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def statisticalResult(arrayA: Array[Int]): Array[Int] =
      if arrayA.isEmpty then Array.empty
      else
        // prefix[i] = arrayA[0..i]的乘积
        val prefix = Array.fill(arrayA.length)(1)
        // suffix[i] = array[i..n-1] 的乘积
        val suffix = Array.fill(arrayA.length)(1)

        prefix(0) = arrayA(0)
        suffix(arrayA.length - 1) = arrayA(arrayA.length - 1)
        for i <- 1 until arrayA.length do
          prefix(i) = prefix(i - 1) * arrayA(i)
          suffix(arrayA.length - 1 - i) = suffix(arrayA.length - i) * arrayA(arrayA.length - 1 - i)

        val res = Array.fill(arrayA.length)(1)
        res(0) = suffix(1)
        res(arrayA.length - 1) = prefix(arrayA.length - 2)
        for k <- 1 until arrayA.length - 1 do res(k) = prefix(k - 1) * suffix(k + 1)

        res
  }
//leetcode submit region end(Prohibit modification and deletion)

}
