package leetcode.editor.cn

object LargestBstSubtree {
  import scala.util.control.TailCalls.*
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    def largestBSTSubtree(root: TreeNode): Int = findBST(root)

    private case class Result(minVal: Int, maxVal: Int, nodeCount: Int)
    private def findBST(node: TreeNode): Int =
      var res = 0
      def impl(node: TreeNode): Option[Result] =
        node match
          case null => Some(Result(Int.MaxValue, Int.MinValue, 0))
          case _ =>
            val r = for
              left  <- impl(node.left)
              right <- impl(node.right)
            yield (left, right) match
              case (Result(leftMin, leftMax, leftCount), Result(rightMin, rightMax, rightCount)) if node.value > leftMax && node.value < rightMin =>
                val nodeMin   = math.min(leftMin, node.value)
                val nodeMax   = math.max(rightMax, node.value)
                val nodeCount = leftCount + rightCount + 1
                res = math.max(res, nodeCount)
                Some(Result(nodeMin, nodeMax, nodeCount))
              case (_, _) => None

            r.flatten
      impl(node)
      res
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
