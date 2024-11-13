package leetcode.editor.cn

object NestedListWeightSumIi {
  import scala.util.control.TailCalls.*

  trait NestedInteger {

    // Return true if this NestedInteger holds a single integer, rather than a nested list.
    def isInteger: Boolean

    // Return the single integer that this NestedInteger holds, if it holds a single integer.
    def getInteger: Int

    // Set this NestedInteger to hold a single integer.
    def setInteger(i: Int): Unit

    // Return the nested list that this NestedInteger holds, if it holds a nested list.
    def getList: Array[NestedInteger]

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    def add(ni: NestedInteger): Unit
  }
//leetcode submit region begin(Prohibit modification and deletion)
  /** // This is the interface that allows for creating nested lists. // You should not implement it, or speculate about its implementation
    */
  object Solution {
    def depthSumInverse(nestedList: List[NestedInteger]): Int =
      val maxDepth = getMaxDepth(nestedList)
      getSum(nestedList, 1, maxDepth)

    private def getMaxDepth(nestedList: List[NestedInteger]): Int =
      nestedList match
        case Nil => 0
        case _ =>
          var max = 1
          nestedList.foreach {
            case n if !n.isInteger => max = math.max(max, getMaxDepth(n.getList.toList) + 1)
            case _                 => ()
          }
          max

    private def getSum(nestedList: List[NestedInteger], depth: Int, maxDepth: Int): Int =
      nestedList match
        case Nil => 0
        case _ =>
          var sum = 0
          nestedList.foreach {
            case n if n.isInteger =>
              sum += n.getInteger * (maxDepth - depth + 1)
            case l =>
              sum += getSum(l.getList.toList, depth + 1, maxDepth)
          }
          sum
  }
//leetcode submit region end(Prohibit modification and deletion)

  import scala.util.control.TailCalls.*

  /** 栈安全的State Monad。可用于leetcode的用fp方式来解决问题。
    */
  case class State[S, A](run: S => TailRec[(S, A)]) {
    def evalState(s: S): A = run(s).result._2
    def execState(s: S): S = run(s).result._1
    def map[B](ab: A => B): State[S, B] =
      flatMap(a => State(s => done((s, ab(a)))))

    def flatMap[B](afb: A => State[S, B]): State[S, B] =
      State(s =>
        tailcall(
          run(s).flatMap { case (s, a) => tailcall(afb(a).run(s)) }
        )
      )
  }
  object StateOps {
    def point[S, A](a: A): State[S, A] = State(s => done((s, a)))

    def get[S]: State[S, S] = State(s => done((s, s)))

    def put[S](s: S): State[S, Unit] = State(_ => done((s, ())))

    def modify[S](ss: S => S): State[S, Unit] =
      for { s <- get[S]; _ <- put(ss(s)) } yield ()
  }

}
