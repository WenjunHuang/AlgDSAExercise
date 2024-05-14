package leetcode.editor.cn
object OpenTheLock {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    import scala.collection.mutable
//    def openLock(deadends: Array[String],target:String):Int = {
//      val deads = deadends.toSet
//
//      @annotation.tailrec
//      def dfs(cur:String,depth:Int,visited:Set[String]):Int = {
//        if (deads.contains(cur) || visited.contains(cur)) Int.MaxValue
//        else if (cur == target) depth
//        else {
//          val nextCombinations = for {
//            i <- 0 until 4
//            up = plusOne(cur,i)
//            down = minusOne(cur,i)
//          } yield Seq(dfs(up,depth+1,visited+cur),dfs(down,depth+1,visited+cur))
//          nextCombinations.flatten.min
//        }
//      }
//
//      val result = dfs("0000",0,Set())
//      if (result == Int.MaxValue) -1 else result
//    }
    def openLock(deadends: Array[String], target: String): Int = {
      val visited = mutable.Set[String]()
      val deads = mutable.Set[String](deadends *)
      val q = mutable.Queue[String]()
      q.enqueue("0000")
      visited.add("0000")

      var step = 0
      var result:Option[Int] = None
      while (q.nonEmpty && result.isEmpty) {
        val sz = q.size
        for (i <- 0 until sz if result.isEmpty) {
          val cur = q.dequeue()
          if (!deads.contains(cur)) {
            if (cur == target) {
              result = Some(step)
            } else {
              for (i <- 0 until 4) {
                val up = plusOne(cur, i)
                if (!visited.contains(up)) {
                  q.enqueue(up)
                  visited.add(up)
                }
                val down = minusOne(cur, i)
                if (!visited.contains(down)) {
                  q.enqueue(down)
                  visited.add(down)
                }
              }
            }
          }
        }
        step = step + 1
      }

      result match {
        case Some(r) =>r
        case None => -1
      }
    }

    def plusOne(s: String, j: Int): String = {
      val arr = s.toCharArray
      if (arr(j) == '9') {
        arr(j) = '0'
      } else {
        arr(j) = (arr(j) + 1).toChar
      }
      arr.mkString
    }

    def minusOne(s: String, j: Int): String = {
      val arr = s.toCharArray
      if (arr(j) == '0') {
        arr(j) = '9'
      } else {
        arr(j) = (arr(j) - 1).toChar
      }
      arr.mkString
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}

