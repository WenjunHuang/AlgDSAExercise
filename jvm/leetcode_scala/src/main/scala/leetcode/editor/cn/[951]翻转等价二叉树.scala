package leetcode.editor.cn

object FlipEquivalentBinaryTrees {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {

    import scala.util.control.TailCalls.*

    def flipEquiv(root1: TreeNode, root2: TreeNode): Boolean =
      impl(root1, root2).result

    private def impl(node1: TreeNode, node2: TreeNode): TailRec[Boolean] =
      (node1, node2) match
        case (null, null)                    => done(true)
        case (null, _)                       => done(false)
        case (_, null)                       => done(false)
        case _ if node1.value != node2.value => done(false)
        case _ =>
          impl(node1.left, node2.left).flatMap { r =>
            if r then impl(node1.right, node2.right)
            else
              impl(node1.left, node2.right).flatMap { r =>
                if r then impl(node1.right, node2.left)
                else done(false)
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
