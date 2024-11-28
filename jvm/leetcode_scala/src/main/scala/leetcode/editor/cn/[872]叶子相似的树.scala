package leetcode.editor.cn
object LeafSimilarTrees {
  import scala.util.control.TailCalls.*
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    import scala.util.boundary
    def leafSimilar(root1: TreeNode, root2: TreeNode): Boolean = {
      val itr1 = LeafIterator(root1)
      val itr2 = LeafIterator(root2)

      boundary:
        while itr1.hasNext && itr2.hasNext do if itr1.next.value != itr2.next.value then boundary.break(false)

        itr1.hasNext == itr2.hasNext
    }

    import scala.collection.mutable
    class LeafIterator(node: TreeNode) extends Iterator[TreeNode] {
      private val stack = mutable.Stack[TreeNode](node)

      override def hasNext: Boolean = stack.nonEmpty

      override def next(): TreeNode = {
        boundary:
          while stack.nonEmpty  do
            val cur = stack.pop()
            if cur.left == null && cur.right == null then boundary.break(cur)
            else
              if cur.right != null then stack.push(cur.right)
              if cur.left != null then stack.push(cur.left)
          null
      }
    }
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
