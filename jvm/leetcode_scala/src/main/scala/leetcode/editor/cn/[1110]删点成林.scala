package leetcode.editor.cn

object DeleteNodesAndReturnForest {
  import scala.util.control.TailCalls.*
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {

    def delNodes(root: TreeNode, to_delete: Array[Int]): List[TreeNode] =
      val toDelete = to_delete.toSet
      doDelete(Option(root), hasParent = false).execState(Context(toDelete, Nil)).result

    import StateOps.*
    private case class Context(toDelete: Set[Int], result: List[TreeNode])
    private def doDelete(node: Option[TreeNode], hasParent: Boolean): State[Context, Option[TreeNode]] =
      node match
        case None => point(None)
        case Some(node) =>
          for
            ctx <- get[Context]
            deleted = ctx.toDelete.contains(node.value)
            _     <- if !deleted && !hasParent then modify[Context](ctx => ctx.copy(result = node +: ctx.result)) else point(())
            left  <- doDelete(Option(node.left), !deleted)
            right <- doDelete(Option(node.right), !deleted)
          yield
            if deleted then None
            else
              node.left = left.orNull
              node.right = right.orNull
              Some(node)

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
