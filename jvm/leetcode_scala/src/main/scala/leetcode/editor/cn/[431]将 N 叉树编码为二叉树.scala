package leetcode.editor.cn
object EncodeNAryTreeToBinaryTree {
  import scala.util.control.TailCalls.*

  class Node(var _value: Int) {
    var value: Int           = _value
    var children: List[Node] = List()
  }
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a Node. class Node(var _value: Int) { var value: Int = _value var children: List[Node] = List() }
    */

  /** Definition for a binary tree node. class TreeNode(var _value: Int) { var value: Int = _value var left: TreeNode = null var right: TreeNode = null }
    */

  class Codec {
    import scala.collection.mutable
    // Encodes an n-ary tree to a binary tree.
    def encode(root: Node): TreeNode =
      root match
        case null => null
        case _ =>
          val btRoot = TreeNode(root.value)
          val q      = mutable.Queue[(Node, TreeNode)]((root, btRoot))
          while q.nonEmpty do
            val (curNtNode, curBtNode) = q.dequeue()
            val dummy                  = TreeNode(-1)
            var p                      = dummy
            for child <- curNtNode.children do
              val newBtNode = TreeNode(child.value)
              q.enqueue((child, newBtNode))
              p.right = newBtNode
              p = p.right
            curBtNode.left = dummy.right
          btRoot

    // Decodes your binary tree to an n-ary tree.
    def decode(root: TreeNode): Node =
      root match
        case null => null
        case _ =>
          val ntRoot = new Node(root.value)
          val q      = mutable.Queue[(TreeNode, Node)]((root, ntRoot))
          while q.nonEmpty do
            val (curBtNode, curNtNode) = q.dequeue()

            val children = mutable.ListBuffer[Node]()
            var p        = curBtNode.left
            while p != null do
              val newNtNode = new Node(p.value)
              q.enqueue((p, newNtNode))
              children.addOne(newNtNode)
              p = p.right
            curNtNode.children = children.toList
          ntRoot
  }

  /** Your Codec object will be instantiated and called as such: var obj = new Codec() var data = obj.encode(root) var ans = obj.decode(data)
    */
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
