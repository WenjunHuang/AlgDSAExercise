package leetcode.editor.cn

object RangeSumOfBst {
  import scala.util.control.TailCalls.*
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    def rangeSumBST(root: TreeNode, low: Int, high: Int): Int = recursive(root, low, high)

    private var sum = 0
    private def traverse(node: TreeNode, low: Int, high: Int): Int =
      sum = 0
      traverseImpl(node, low, high)
      sum
    private def traverseImpl(node: TreeNode, low: Int, high: Int): Unit =
      node match
        case null                   => ()
        case _ if node.value < low  => traverseImpl(node.right, low, high)
        case _ if node.value > high => traverseImpl(node.left, low, high)
        case _ =>
          sum += node.value
          traverseImpl(node.left, low, high)
          traverseImpl(node.right, low, high)

    private def recursive(node: TreeNode, low: Int, high: Int): Int =
      node match
        case null                   => 0
        case _ if node.value < low  => recursive(node.right, low, high)
        case _ if node.value > high => recursive(node.left, low, high)
        case _ =>
          node.value + recursive(node.left, low, high) + recursive(node.right, low, high)
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
