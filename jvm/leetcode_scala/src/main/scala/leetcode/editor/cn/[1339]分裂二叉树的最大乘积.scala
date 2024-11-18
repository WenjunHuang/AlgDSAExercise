package leetcode.editor.cn

object MaximumProductOfSplittedBinaryTree {
  import scala.util.control.TailCalls.*
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    def maxProduct(root: TreeNode): Int =
      val totalSum = getTreeSum(root)
      val res      = findMatch(root, totalSum)
      (res % (1e9 + 7)).toInt

    private def findMatch(node: TreeNode, treeSum: Long): Long =
      var res = 0L
      def impl(node: TreeNode): Long =
        node match
          case null => 0
          case _ =>
            val left    = impl(node.left)
            val right   = impl(node.right)
            val nodeSum = left + right + node.value.toLong
            res = math.max(res, nodeSum * (treeSum - nodeSum))
            nodeSum
      impl(node)
      res

    private def getTreeSum(node: TreeNode): Long =
      node match
        case null => 0L
        case _ =>
          val left  = getTreeSum(node.left)
          val right = getTreeSum(node.right)
          left + right + node.value.toLong
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
