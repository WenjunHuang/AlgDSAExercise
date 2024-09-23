package leetcode.editor.cn

object FindDuplicateSubtrees {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */

  import scala.util.control.TailCalls.*
  /** 栈安全的State Monad。可用于leetcode的用fp方式来解决问题。
   */
  case class State[S, A](run: S => TailRec[(S, A)]):
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

  object StateOps:
    def point[S, A](a: A): State[S, A] = State(s => done((s, a)))

    def get[S]: State[S, S] = State(s => done((s, s)))

    def put[S](s: S): State[S, Unit] = State(_ => done((s, ())))

    def modify[S](ss: S => S): State[S, Unit] =
      for {s <- get[S]; _ <- put(ss(s))} yield ()
  object Solution {

    import scala.util.control.TailCalls.*

    private case class Result(subTrees: Map[String, Int], res: List[TreeNode])

    def findDuplicateSubtrees(root: TreeNode): List[TreeNode] =
      serialize(root).execState(Result(Map.empty, Nil)).res

    private def serialize(node: TreeNode): State[Result, String] =
      import StateOps.*
      if node == null then point("#")
      else
        for
          _     <- modify[Result](identity)
          left  <- serialize(node.left)
          right <- serialize(node.right)
          res = s"$left,$right,${node.value}"
          _ <- modify[Result](r =>
            val count = r.subTrees.getOrElse(res, 0) + 1
            r.copy(subTrees = r.subTrees.updated(res, count), res = if count == 2 then node :: r.res else r.res)
          )
        yield res
  }
//leetcode submit region end(Prohibit modification and deletion)

}
