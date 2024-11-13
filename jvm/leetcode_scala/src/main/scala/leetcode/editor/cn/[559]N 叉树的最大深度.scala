package leetcode.editor.cn

object MaximumDepthOfNAryTree {
  import scala.util.control.TailCalls.*
  class Node(var _value: Int) {
    var value: Int           = _value
    var children: List[Node] = List()
  }
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a Node.
    */

  object Solution {
    def maxDepth(root: Node): Int = traverse(root)

    private var depth = 0
    private var res = 0
    private def traverse(node:Node):Int =
      depth = 0
      res = 0
      traverseImpl(node)
      res
    private def traverseImpl(node:Node):Unit =
      node match
        case null => ()
        case _ =>
          depth += 1
          res = math.max(res,depth)
          node.children.foreach(traverseImpl)
          depth -= 1

    private def recursive(node: Node): Int =
      node match
        case null => 0
        case _ =>
          val childrenDepth = node.children.foldLeft(0) { (accum, node) =>
            math.max(recursive(node), accum)
          }
          childrenDepth + 1
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
