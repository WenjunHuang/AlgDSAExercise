package leetcode.editor.cn

object CheckIfAStringIsAValidSequenceFromRootToLeavesPathInABinaryTree {
  import scala.util.control.TailCalls.*
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    def isValidSequence(root: TreeNode, arr: Array[Int]): Boolean =
      traverse(root, arr)
//      check(root, arr, 0)

    private def check(node: TreeNode, arr: Array[Int], i: Int): Boolean =
      if node == null || i == arr.length then false
      else if node.left == null && node.right == null then arr(i) == node.value && i == arr.length - 1
      else if node.value != arr(i) then false
      else check(node.left, arr, i + 1) || check(node.right, arr, i + 1)

    private def traverse(root: TreeNode, arr: Array[Int]): Boolean =
      var depth   = 0
      var isValid = false
      def impl(node: TreeNode): Unit =
        if node == null || isValid then ()
        else if node.left == null && node.right == null then
          if depth == arr.length - 1 && arr(depth) == node.value then isValid = true
          ()
        else if depth >= arr.length || arr(depth) != node.value then ()
        else
          depth += 1
          impl(node.left)
          impl(node.right)
          depth -= 1

      impl(root)
      isValid
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
