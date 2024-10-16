package leetcode.editor.cn

object LowestCommonAncestorOfABinaryTree {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(var _value: Int) { var value: Int = _value var left: TreeNode = null var right: TreeNode = null }
    */

  object Solution {

    import scala.util.control.TailCalls.*

    def lowestCommonAncestor(root: TreeNode, p: TreeNode, q: TreeNode): TreeNode =
      find(root, p.value, q.value).result

    def find(node: TreeNode, val1: Int, val2: Int): TailRec[TreeNode] =
      if node == null then done(null)
      else if node.value == val1 || node.value == val2 then done(node)
      else
        for
          left  <- find(node.left, val1, val2)
          right <- find(node.right, val1, val2)
        yield
          if left != null && right != null then node
          else if left != null then left
          else right
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
