package leetcode.editor.cn

object CloneNAryTree {
  import scala.util.control.TailCalls.*

  class Node(var _value: Int) {
    var value: Int           = _value
    var children: List[Node] = List()
  }
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a Node. class Node(var _value: Int) { var value: Int = _value var children: List[Node] = List() }
    */

  object Solution {
    def cloneTree(root: Node): Node =
      traverse(root)
//      getClone(root)

    private def getClone(node: Node): Node =
      node match
        case null => null
        case _ =>
          val clone = new Node(node.value)
          clone.children = node.children.map(getClone)
          clone

    private def traverse(node: Node): Node =
      var nodeToCopy = Map.empty[Node, Node]
      def traverse1(node: Node): Unit =
        node match
          case null => ()
          case _ =>
            val cpNode = new Node(node.value)
            nodeToCopy = nodeToCopy + (node -> cpNode)
            node.children.foreach(traverse1)
            ()

      def traverse2(node: Node): Unit =
        node match
          case null => ()
          case _ =>
            val cpNode = nodeToCopy(node)
            cpNode.children = node.children.map { c =>
              traverse2(c)
              nodeToCopy(c)
            }
            ()

      node match
        case null => null
        case _ =>
          traverse1(node)
          traverse2(node)
          nodeToCopy(node)
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
