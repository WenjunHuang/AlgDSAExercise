package leetcode.editor.cn

object BinaryTreeLongestConsecutiveSequence {
  import scala.util.control.TailCalls.*
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    def longestConsecutive(root: TreeNode): Int =
      traverse(root).execState(Context(0)).maxLen

    import StateOps.*
    private case class Context(maxLen: Int)
    private def traverse(node: TreeNode, currentLen: Int = 1, parentVal: Int = Int.MinValue): State[Context, Unit] =
      if node == null then point(())
      else
        var len = currentLen
        if node.value == parentVal + 1 then len = len + 1
        else len = 1
        for
          _ <- modify[Context](ctx => ctx.copy(maxLen = math.max(ctx.maxLen, len)))
          _ <- traverse(node.left, len, node.value)
          _ <- traverse(node.right, len, node.value)
        yield ()

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
//leetcode submit region end(Prohibit modification and deletion)

}
