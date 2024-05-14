package leetcode.editor.cn
object FindAllAnagramsInAString {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    import scala.collection.mutable
    def findAnagrams(s: String, p: String): List[Int] = {
      val need = p.groupBy(identity).view.mapValues(_.length).toMap
      val window = mutable.Map.empty[Char, Int]
      var left = 0
      var right = 0
      var valid = 0
      var result: List[Int] = Nil

      while (right < s.length) {
        val c = s(right)
        right += 1

        if (need.contains(c)) {
          window.updateWith(c) {
            case Some(v) =>
              Some(v + 1)
            case None => Some(1)
          }
          if (window(c) == need(c)) valid += 1
        }

        while (right - left >= p.length) {
          if (valid == need.size) {
            result = left :: result
          }

          val d = s(left)
          left += 1

          if (need.contains(d)) {
            window.updateWith(d) {
              case Some(v) =>
                if (v == need(d)) valid -= 1
                Some(v - 1)
              case None => None
            }
          }
        }
      }

      result
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
