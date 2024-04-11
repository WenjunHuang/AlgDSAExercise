package leetcode.editor.cn
object PermutationInString {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    import scala.collection.mutable
    import scala.util.boundary
    def checkInclusion(s1: String, s2: String): Boolean = {
      val need = s1.groupBy(identity).view.mapValues(_.length).toMap
      val window = mutable.Map.empty[Char, Int]
      var left = 0
      var right = 0
      var valid = 0

      boundary {
        while (right < s2.length) {
          val c = s2(right)
          right += 1

          if (need.contains(c)) {
            window.updateWith(c) {
              case Some(v) =>
                Some(v+1)
              case None =>
                Some(1)
            }
            if (window(c) == need(c)) valid += 1
          }

          while (right - left >= s1.length) {
            if (valid == need.size)
              boundary.break(true)

            val d = s2(left)
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
        false
      }
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}

@main
def run(): Unit = {
  println(PermutationInString.Solution.checkInclusion("ab", "eidbaooo"))
}
