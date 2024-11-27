package leetcode.editor.cn

object DeepestLeavesSum {

  // leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    def deepestLeavesSum(root: TreeNode): Int = dfs(root)

    import scala.collection.mutable
    private def bfs(root: TreeNode): Int =
      root match
        case null => 0
        case _ =>
          val q   = mutable.Queue[TreeNode](root)
          var res = 0
          while q.nonEmpty do
            res = (0 until q.size).foldLeft(0) { (accum, _) =>
              val cur = q.dequeue()
              if cur.left != null then q.enqueue(cur.left)
              if cur.right != null then q.enqueue(cur.right)
              accum + cur.value
            }

          res

    private def dfs(root: TreeNode): Int =
      root match
        case null => 0
        case _ =>
          val res = mutable.ArrayBuffer[Int]()
          def traverse(node: TreeNode, depth: Int): Unit =
            node match
              case null => ()
              case _ =>
                if res.size == depth then res.addOne(node.value)
                else res(depth) = res(depth) + node.value

                traverse(node.left, depth + 1)
                traverse(node.right, depth + 1)

          traverse(root,0)
          res.last
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
