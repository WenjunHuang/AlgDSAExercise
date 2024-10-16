package leetcode.editor.cn

object PseudoPalindromicPathsInABinaryTree {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    def pseudoPalindromicPaths(root: TreeNode): Int =
      traverse(root, Map.empty).execState(0)

    import StateOps.*
    private def traverse(node: TreeNode, count: Map[Int, Int]): State[Int, Unit] =
      if node == null then point(())
      else
        val newCount = count.updatedWith(node.value)(_.map(_ + 1).orElse(Some(1)))
        if node.left == null && node.right == null then
          val palindromic = newCount.count(_._2 % 2 == 1) <= 1
          if palindromic then modify[Int](c => c + 1) else point(())
        else
          for
            _ <- traverse(node.left, newCount)
            _ <- traverse(node.right, newCount)
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
