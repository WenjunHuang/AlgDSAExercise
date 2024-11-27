package leetcode.editor.cn

import scala.util.control.NonLocalReturns

object CheckCompletenessOfABinaryTree {

  // leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    import scala.collection.mutable
    import scala.util.boundary
    def isCompleteTree(root: TreeNode): Boolean = {
      val q   = mutable.Queue[Option[TreeNode]](Option(root))
      var end = false
      boundary {
        while q.nonEmpty do
          val size = q.size
          for i <- 0 until size do
            q.dequeue() match
              case None =>
                end = true
              case Some(node) =>
                if end then boundary.break(false)
                else
                  q.enqueue(Option(node.left))
                  q.enqueue(Option(node.right))
        end
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
