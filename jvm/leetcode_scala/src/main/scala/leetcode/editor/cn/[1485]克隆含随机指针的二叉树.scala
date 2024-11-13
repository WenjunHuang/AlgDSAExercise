package leetcode.editor.cn

object CloneBinaryTreeWithRandomPointer {

  class Node(var _value: Int, _left: Node = null, _right: Node = null, _random: Node = null) {
    var value: Int   = _value
    var left: Node   = _left
    var right: Node  = _right
    var random: Node = _random
  }
  class NodeCopy(var _value: Int, _left: NodeCopy = null, _right: NodeCopy = null, _random: NodeCopy = null) {
    var value: Int       = _value
    var left: NodeCopy   = _left
    var right: NodeCopy  = _right
    var random: NodeCopy = _random
  }
  import scala.util.control.TailCalls.*
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a Node. class Node(var _value: Int, _left: Node = null, _right: Node = null, _random: Node = null) { var value: Int = _value var left: Node = _left var right: Node = _right var
    * random: Node = _random }
    */

  object Solution {
    def copyRandomBinaryTree(root: Node): NodeCopy =
      impl(Option(root)).execState(Context(Map.empty)).cache.get(root).orNull

    import StateOps.*
    private case class Context(cache: Map[Node, NodeCopy])
    private def impl(node: Option[Node]): State[Context, Option[NodeCopy]] =
      node match
        case None => point(None)
        case Some(node) =>
          for
            ctx <- get[Context]
            result <-
              if ctx.cache.contains(node) then point(ctx.cache(node))
              else
                val rootCopy = NodeCopy(node.value)
                for
                  _      <- modify[Context](ctx => ctx.copy(cache = ctx.cache + (node -> rootCopy)))
                  left   <- impl(Option(node.left))
                  right  <- impl(Option(node.right))
                  random <- impl(Option(node.random))
                yield
                  rootCopy.left = left.orNull
                  rootCopy.right = right.orNull
                  rootCopy.random = random.orNull
                  rootCopy
          yield Some(result)

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
//leetcode submit region end(Prohibit modification and deletion)

}
