package leetcode.editor.cn

import leetcode.editor.cn.BinaryTreeZigzagLevelOrderTraversal.Solution.Direction.LeftToRight

object BinaryTreeZigzagLevelOrderTraversal {
  import scala.util.control.TailCalls.*
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {

    import scala.collection.mutable

    def zigzagLevelOrder(root: TreeNode): List[List[Int]] =
      root match
        case null => Nil
        case _ =>
          val queue  = mutable.Queue[TreeNode](root)
          var res    = List[List[Int]]()
          var curDir = Direction.LeftToRight
          while queue.nonEmpty do
            // 按照从左到右的方式添加队列
            val size             = queue.size
            var level: List[Int] = Nil
            for _ <- 0 until size yield
              val cur = queue.dequeue()
              if cur.left != null then queue.enqueue(cur.left)
              if cur.right != null then queue.enqueue(cur.right)

              curDir match
                case Direction.LeftToRight =>
                  level = level :+ cur.value
                case Direction.RightToLeft =>
                  level = cur.value +: level

            res = res :+ level
            curDir = curDir.switchDirection
          res

    enum Direction {
      case LeftToRight
      case RightToLeft

      def switchDirection: Direction =
        this match
          case LeftToRight =>
            RightToLeft
          case RightToLeft =>
            LeftToRight
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
