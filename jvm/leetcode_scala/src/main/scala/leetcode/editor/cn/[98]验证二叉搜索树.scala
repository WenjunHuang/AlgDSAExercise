package leetcode.editor.cn

object ValidateBinarySearchTree {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    def isValidBST(root: TreeNode): Boolean = impl(root).evalState(Context(null, null))

    private def impl(root: TreeNode): State[Context, Boolean] =
      import StateOps.*
      if root == null then point(true)
      else
        get[Context].flatMap(ctx =>
          if ctx.min != null && root.value <= ctx.min.value then point(false)
          else if ctx.max != null && root.value >= ctx.max.value then point(false)
          else
            put(Context(ctx.min, root))
              .flatMap(_ => impl(root.left))
              .flatMap(r =>
                if !r then point(false)
                else put(Context(root, ctx.max)).flatMap(_ => impl(root.right))
              )
        )

    private case class Context(min: TreeNode, max: TreeNode)

    import scala.util.control.TailCalls.*

    /** 栈安全的State Monad。可用于leetcode的用fp方式来解决问题。
      */
    case class State[S, A](run: S => TailRec[(S, A)]):
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

    object StateOps:
      def point[S, A](a: A): State[S, A] = State(s => done((s, a)))

      def get[S]: State[S, S] = State(s => done((s, s)))

      def put[S](s: S): State[S, Unit] = State(_ => done((s, ())))

      def modify[S](ss: S => S): State[S, Unit] =
        for { s <- get[S]; _ <- put(ss(s)) } yield ()
  }
//leetcode submit region end(Prohibit modification and deletion)

}
