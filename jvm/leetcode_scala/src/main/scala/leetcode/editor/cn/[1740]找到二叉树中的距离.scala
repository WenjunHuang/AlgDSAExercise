package leetcode.editor.cn

object FindDistanceInABinaryTree {
  import scala.util.control.TailCalls.*
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {

    def findDistance(root: TreeNode, p: Int, q: Int): Int =
      find(root, p, q).execState(Context(false, 0)).distance

    enum FindResult:
      case Found(distance: Int)
      case NotFound

    case class Context(found: Boolean, distance: Int)
    import StateOps.*
    def find(node: TreeNode, p: Int, q: Int): State[Context, FindResult] =
      get[Context].flatMap { ctx =>
        if ctx.found then point(FindResult.Found(ctx.distance))
        else if node == null then point(FindResult.NotFound)
        else
          for
            left  <- find(node.left, p, q)
            right <- find(node.right, p, q)
            r <- (left, right) match
              case (FindResult.NotFound, FindResult.NotFound) =>
                if node.value == p || node.value == q then point(FindResult.Found(1))
                else point(FindResult.NotFound)
              case (FindResult.Found(l), FindResult.Found(r)) if !ctx.found => for _ <- put(Context(true, l + r)) yield FindResult.Found(l + r)
              case (FindResult.Found(l), _) =>
                if node.value == p || node.value == q then for _ <- modify[Context](_.copy(distance = l)) yield FindResult.Found(l + 1)
                else point(FindResult.Found(l + 1))
              case (_, FindResult.Found(r)) =>
                if node.value == p || node.value == q then for _ <- modify[Context](_.copy(distance = r)) yield FindResult.Found(r + 1)
                else point(FindResult.Found(r + 1))
          yield r

      }

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
