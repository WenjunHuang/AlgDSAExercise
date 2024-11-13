package leetcode.editor.cn

object PathSumIi {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {

    import scala.util.control.TailCalls.*

    def pathSum(root: TreeNode, targetSum: Int): List[List[Int]] =
      recursive(root, targetSum).result
//      traverse(root, targetSum).execState(Context(Nil, Nil)).result

    import StateOps.*
    private case class Context(result: List[List[Int]], currentPath: List[Int])
    private def traverse(node: TreeNode, targetSum: Int): State[Context, Unit] =
      node match
        case null => point(())
        case _ =>
          val remain = targetSum - node.value
          if node.left == null && node.right == null then
            if remain == 0 then for _ <- modify[Context](ctx => ctx.copy(result = (ctx.currentPath :+ node.value) +: ctx.result)) yield ()
            else point(())
          else
            for
              _ <- modify[Context](ctx => ctx.copy(currentPath = ctx.currentPath :+ node.value))
              _ <- traverse(node.left, remain)
              _ <- traverse(node.right, remain)
              _ <- modify[Context](ctx => ctx.copy(currentPath = ctx.currentPath.take(ctx.currentPath.length - 1)))
            yield ()

    private def recursive(node: TreeNode, targetSum: Int): TailRec[List[List[Int]]] =
      node match
        case null => done(Nil)
        case _ =>
          if node.left == null && node.right == null then
            if node.value == targetSum then done(List(List(node.value)))
            else done(Nil)
          else
            val remain = targetSum - node.value
            for
              leftAnswers  <- recursive(node.left, remain)
              rightAnswers <- recursive(node.right, remain)
            yield leftAnswers.map(l => node.value +: l) ++ rightAnswers.map(l => node.value +: l)

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
