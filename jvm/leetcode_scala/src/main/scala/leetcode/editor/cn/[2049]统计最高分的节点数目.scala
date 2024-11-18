package leetcode.editor.cn

object CountNodesWithTheHighestScore {
  import scala.util.control.TailCalls.*
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    class MyTreeNode {
      var left: MyTreeNode  = _
      var right: MyTreeNode = _

      def addChild(node: MyTreeNode): Unit =
        if left == null then left = node
        else right = node
    }

    def countHighestScoreNodes(parents: Array[Int]): Int =
      val nodes = Array.fill(parents.length)(MyTreeNode())
      for i <- parents.indices do if parents(i) != -1 then nodes(parents(i)).addChild(nodes(i))
      getScore(nodes(0), parents.length)

    private def getScore(node: MyTreeNode, total: Int): Int =
      var maxScore = 0L
      var count    = 0
      def impl(node: MyTreeNode): Int =
        node match
          case null => 0
          case _ =>
            val left  = impl(node.left)
            val right = impl(node.right)
            val score = calScore(left) * calScore(right) * calScore(total - left - right - 1)
            if score == maxScore then count += 1
            else if score > maxScore then
              maxScore = score
              count = 1
            left + right + 1
      impl(node)
      count
    private def calScore(count: Int):Long =
      if count == 0 then 1 else count
  }

//leetcode submit region end(Prohibit modification and deletion)

  @main
  def runSolution(): Unit =
    print(Solution.countHighestScoreNodes(Array(-1, 2, 0)))
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
