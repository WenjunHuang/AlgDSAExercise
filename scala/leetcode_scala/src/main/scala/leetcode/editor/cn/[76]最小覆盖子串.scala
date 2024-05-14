package leetcode.editor.cn
object MinimumWindowSubstring {
  //leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def minWindow(s: String, t: String): String = {
      val need = t.groupBy(identity).view.mapValues(_.length)
      var window = Map.empty[Char, Int]
      var left = 0
      var right = 0
      var valid = 0
      var start = 0
      var len = Int.MaxValue

      @annotation.tailrec
      def outter(): Unit = {
        if (right < s.length) {
          val c = s(right)

          // 扩大窗口
          right += 1

          if (need.contains(c)) {
            window = window.updatedWith(c)(_.map(_ + 1).orElse(Some(1)))
            if (window(c) == need(c)) valid += 1
          }
          inner()
          outter()
        }
      }

      @annotation.tailrec
      def inner(): Unit = {
        if (valid == need.size) {
          if (right - left < len) {
            start = left
            len = right - left
          }
          val d = s(left)

          if (need.contains(d)) {
            if (window(d) == need(d)) valid -= 1
            window = window.updatedWith(d)(_.map(_ - 1).orElse(None))
          }
          left += 1
          inner()
        }
      }

      outter()
      if (len == Int.MaxValue) "" else s.substring(start, start + len)
    }

    def minWindow2(s: String, t: String): String = {
      val need = t.groupBy(identity).view.mapValues(_.length)
      var window = Map.empty[Char, Int]
      var left = 0
      var right = 0
      var valid = 0
      var start = 0
      var len = Int.MaxValue
      while (right < s.length) {
        val c = s(right)

        // 扩大窗口
        right += 1

        if (need.contains(c)) {
          window = window.updatedWith(c)(_.map(_ + 1).orElse(Some(1)))
          if (window(c) == need(c)) valid += 1
        }

        // 判断左侧窗口是否要收缩
        while (valid == need.size) {
          // 更新最小覆盖子串
          if (right - left < len) {
            start = left
            len = right - left
          }

          // d是将移出窗口的字符
          val d = s(left)
          // 缩小窗口
          left += 1

          // 更新条件
          if (need.contains(d)) {
            if (window(d) == need(d)) valid -= 1
            window = window.updatedWith(d)(_.map(_ - 1).orElse(None))
          }
        }
      }
      if (len == Int.MaxValue) "" else s.substring(start, start + len)
    }
    //leetcode submit region end(Prohibit modification and deletion)

  }
}
