package leetcode.editor.cn
object LongestSubstringWithoutRepeatingCharacters {
//leetcode submit region begin(Prohibit modification and deletion)
object Solution {
  import scala.collection.mutable
    def lengthOfLongestSubstring(s: String): Int = {
      val window = mutable.Map.empty[Char,Int]
      var left = 0
      var right =0
      var res = 0

      while (right < s.length){
        val c = s(right)
        right += 1
        window.update(c,window.getOrElse(c,0)+1)

        while (window(c) > 1) {
         val d = s(left)
          left += 1
          window.updateWith(d){
            case Some(v) if v > 1 => Some(v-1)
            case _ => None
          }
        }

        res = math.max(res,right - left)
      }

      res
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
