package leetcode.editor.cn

object DeleteNodeInABst {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    def deleteNode(root: TreeNode, key: Int): TreeNode =
      impl().evalState(Context(root, key))

    private def impl(): State[Context, TreeNode] =
      import StateOps.*
      get[Context].flatMap { s =>
        if s.node == null then point(null)
        else if s.key == s.node.value then
          if s.node.left == null then point(s.node.right)
          else if s.node.right == null then point(s.node.left)
          else
            val min = getMin(s.node.right)
            for
              _ <- put(s.copy(node = s.node.right, key = min.value))
              n <- impl()
            yield
              min.left = s.node.left
              min.right = n
              min
        else if s.key < s.node.value then
          for
            _ <- put(s.copy(node = s.node.left))
            n <- impl()
          yield
            s.node.left = n
            s.node
        else
          for
            _ <- put(s.copy(node = s.node.right))
            n <- impl()
          yield
            s.node.right = n
            s.node
      }

    private def getMin(node: TreeNode): TreeNode = {
      var n = node
      while n.left != null do n = n.left
      n
    }

    case class Context(node: TreeNode, key: Int)

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
