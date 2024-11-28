package leetcode.editor.cn
object CompleteBinaryTreeInserter {
  import scala.util.control.TailCalls.*
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  class CBTInserter(private val _root: TreeNode) {
    import scala.collection.mutable
    private val queue = mutable.Queue[TreeNode]()

    init()

    private def init(): Unit =
      _root match
        case null => ()
        case _ =>
          val temp  = mutable.Queue[TreeNode]()
          temp.enqueue(_root)
          while temp.nonEmpty do
            val cur = temp.dequeue()
            if cur.left != null then temp.enqueue(cur.left)
            if cur.right != null then temp.enqueue(cur.right)

            if cur.left == null || cur.right == null then queue.enqueue(cur)

    def insert(value: Int): Int = {
      val node = TreeNode(value)
      val cur  = queue.head
      
      if cur.left == null then cur.left = node
      else if cur.right == null then
        cur.right = node
        queue.dequeue()

      queue.enqueue(node)
      cur.value
    }

    def get_root(): TreeNode = _root

  }

  /** Your CBTInserter object will be instantiated and called as such: val obj = new CBTInserter(root) val param_1 = obj.insert(`val`) val param_2 = obj.get_root()
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
