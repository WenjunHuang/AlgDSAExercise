package leetcode.editor.cn

object PopulatingNextRightPointersInEachNodeIi {

  class Node(var _value: Int) {
    var value: Int  = _value
    var left: Node  = null
    var right: Node = null
    var next: Node  = null
  }

  // leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a Node.
    */

  object Solution {
    import scala.collection.mutable.Queue
    def connect(root: Node): Node =
      root match
        case null => null
        case _ =>
          val queue              = Queue[Node](root)
          var prev: Option[Node] = None
          while queue.nonEmpty do
            val size = queue.size
            for _ <- 0 until size do
              val cur = queue.dequeue()

              prev.foreach(node => node.next = cur)
              cur.next = null
              prev = Some(cur)

              if cur.left != null then queue.enqueue(cur.left)
              if cur.right != null then queue.enqueue(cur.right)
            prev = None
          root
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
