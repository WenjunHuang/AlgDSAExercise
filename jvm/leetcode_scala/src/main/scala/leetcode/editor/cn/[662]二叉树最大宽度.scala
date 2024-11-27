package leetcode.editor.cn

import scala.collection.mutable.ArrayBuffer

object MaximumWidthOfBinaryTree {

  // leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    import scala.collection.mutable.{ ArrayBuffer, Queue }
    def widthOfBinaryTree(root: TreeNode): Int =
      root match
        case null => 0
        case _    => dfs(root)

    private def dfs(root: TreeNode): Int =
      val firstIdOfLevel = ArrayBuffer[Int]()
      var maxWidth       = 1
      def traverse(node: TreeNode, id: Int, depth: Int): Unit =
        node match
          case null => ()
          case _ =>
            if firstIdOfLevel.size == depth then firstIdOfLevel.addOne(id)
            else maxWidth = math.max(maxWidth, id - firstIdOfLevel(depth) + 1)
            traverse(node.left, id * 2, depth + 1)
            traverse(node.right, id * 2 + 1, depth + 1)
      traverse(root, 1, 0)
      maxWidth

    private case class Point(node: TreeNode, index: Int)
    private def bfs(root: TreeNode): Int =
      val queue = Queue[Point](Point(root, 1))
      var res   = 1
      while queue.nonEmpty do
        val size  = queue.size
        var start = 0
        var end   = 0
        for i <- 0 until size do
          val Point(cur, curId) = queue.dequeue()

          if i == 0 then start = curId
          if i == size - 1 then end = curId

          if cur.left != null then queue.enqueue(Point(cur.left, curId * 2))
          if cur.right != null then queue.enqueue(Point(cur.right, curId * 2 + 1))

        res = math.max(res, end - start + 1)
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
