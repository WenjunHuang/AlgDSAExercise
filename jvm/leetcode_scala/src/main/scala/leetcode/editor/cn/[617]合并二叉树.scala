package leetcode.editor.cn

object MergeTwoBinaryTrees {
  import scala.util.control.TailCalls.*
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    def mergeTrees(root1: TreeNode, root2: TreeNode): TreeNode =
      if root1 == null then root2
      else
        traverse(root1, root2)
        root1

    def recursive(root1: TreeNode, root2: TreeNode): TreeNode =
      if root1 == null then root2
      else if root2 == null then root1
      else
        root1.value += root2.value
        root1.left = recursive(root1.left, root2.left)
        root1.right = recursive(root1.right, root2.right)
        root1

    def traverse(root1: TreeNode, root2: TreeNode): Unit =
      if root1 == null || root2 == null then ()
      else
        if root1 != null && root2 != null then root1.value += root2.value

        if root1.left == null && root2.left != null then
          root1.left = root2.left
          root2.left = null

        if root1.right == null && root2.right != null then
          root1.right = root2.right
          root2.right = null

        traverse(root1.left, root2.left)
        traverse(root1.right, root2.right)
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
