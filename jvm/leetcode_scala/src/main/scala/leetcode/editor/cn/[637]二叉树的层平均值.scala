package leetcode.editor.cn

object AverageOfLevelsInBinaryTree {

  // leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    import scala.collection.mutable
    def averageOfLevels(root: TreeNode): Array[Double] = dfs(root)

    private def bfs(root: TreeNode): Array[Double] =
      root match
        case null => Array.empty
        case _ =>
          val res = mutable.ArrayBuffer[Double]()
          val q   = mutable.Queue[TreeNode](root)

          while q.nonEmpty do
            val size = q.size
            var sum  = 0.0
            for _ <- 0 until size do
              val cur = q.dequeue()
              sum += cur.value.toDouble

              if cur.left != null then q.enqueue(cur.left)
              if cur.right != null then q.enqueue(cur.right)

            res.addOne(sum / size)

          res.toArray

    private def dfs(root: TreeNode): Array[Double] =
      root match
        case null => Array.empty
        case _ =>
          val res = mutable.ArrayBuffer[(Double, Int)]()
          def traverse(node: TreeNode, depth: Int): Unit =
            if res.size == depth then res.addOne((node.value.toDouble, 1))
            else
              val (oldSum, oldSize) = res(depth)
              res(depth) = (oldSum + node.value.toDouble, oldSize + 1)

            if node.left != null then traverse(node.left, depth + 1)
            if node.right != null then traverse(node.right, depth + 1)

          traverse(root, 0)
          res.map { case (sum, size) =>
            sum / size
          }.toArray
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
