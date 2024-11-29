package leetcode.editor.cn
object AllNodesDistanceKInBinaryTree {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(var _value: Int) { var value: Int = _value var left: TreeNode = null var right: TreeNode = null }
    */
  object Solution {

    import scala.util.control.TailCalls.*
    import scala.collection.mutable
    def distanceK(root: TreeNode, target: TreeNode, k: Int): List[Int] = {
      val parentRel = mutable.Map[Int, Option[TreeNode]]()
      def traverse(node: TreeNode, parent: TreeNode): TailRec[Unit] =
        node match
          case null => done(())
          case _ =>
            for {
              _ <- done(parentRel.put(node.value, Option(parent)))
              _ <- traverse(node.left, node)
              _ <- traverse(node.right, node)
            } yield ()

      traverse(root, null).result

      val q       = mutable.Queue[TreeNode](target)
      val visited = mutable.Set[Int](target.value)
      var dist    = 0
      val res     = mutable.ListBuffer[Int]()

      while q.nonEmpty do
        val size = q.size
        for _ <- 0 until size do
          val cur = q.dequeue()
          if dist == k then res.addOne(cur.value)

          parentRel.get(cur.value).collect {
            case Some(p) if !visited.contains(p.value) =>
              visited.add(p.value)
              q.enqueue(p)
          }

          if cur.left != null && !visited.contains(cur.left.value) then
            visited.add(cur.left.value)
            q.enqueue(cur.left)
          if cur.right != null && !visited.contains(cur.right.value) then
            visited.add(cur.right.value)
            q.enqueue(cur.right)

        dist += 1

      res.toList
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

  @main
  def runAllNodesDistanceKInBinaryTree(): Unit = {
    val node = TreeNode(2)
    val root = TreeNode(1, node, TreeNode(3))
    println(Solution.distanceK(root, node, 2))
  }
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
