package leetcode.editor.cn

object BinaryTreeLongestConsecutiveSequenceIi {
  import scala.util.control.TailCalls.*
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    def longestConsecutive(root: TreeNode): Int =
      root match
        case null => 0
        case _    => findSequence(root).length

    case class NodeInfo(increasing: List[Int], decreasing: List[Int])

    def findSequence(node: TreeNode): List[Int] =
      var res: List[Int] = Nil
      def impl(node: TreeNode): NodeInfo =
        node match
          case null => NodeInfo(Nil, Nil)
          case _ =>
            val left                   = impl(node.left)
            val right                  = impl(node.right)
            var nodeIncrLen: List[Int] = List(node.value)
            var nodeDecrLen: List[Int] = List(node.value)

            if node.left != null then
              if node.left.value - 1 == node.value then nodeIncrLen = node.value +: left.increasing
              else if node.left.value + 1 == node.value then nodeDecrLen = node.value +: left.decreasing

            if node.right != null then
              if node.right.value - 1 == node.value then nodeIncrLen = if nodeIncrLen.length > right.increasing.length + 1 then nodeIncrLen else node.value +: right.increasing
              else if node.right.value + 1 == node.value then nodeDecrLen = if nodeDecrLen.length > right.decreasing.length + 1 then nodeDecrLen else node.value +: right.decreasing

            res =
              if res.length > nodeDecrLen.length + nodeIncrLen.length - 1 then res
              else nodeIncrLen ++  nodeDecrLen.tail
            NodeInfo(nodeIncrLen, nodeDecrLen)
      impl(node)
      res
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
