package leetcode.editor.cn
object LongestPalindromicSubstring {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def longestPalindrome(s: String): String = {
      @annotation.tailrec
      def palindrome(str: String, l: Int, r: Int): String = {
        if (l < 0 || r >= str.length || str(l) != str(r))
          str.substring(l + 1, r)
        else palindrome(str, l - 1, r + 1)
      }

      var result = ""
      for { i <- 0 until s.length } {
        val s1 = palindrome(s, i, i)
        val s2 = palindrome(s, i, i + 1)

        if (s1.length > result.length) result = s1
        if (s2.length > result.length) result = s2
      }
      result
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}


