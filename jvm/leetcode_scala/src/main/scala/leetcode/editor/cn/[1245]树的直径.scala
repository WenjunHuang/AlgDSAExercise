package leetcode.editor.cn

object TreeDiameter {
  import scala.util.control.TailCalls.*
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def treeDiameter(edges: Array[Array[Int]]): Int =
      if edges.isEmpty then 0
      else
        var tree    = Map.empty[Int, List[Int]]
        var visited = Set.empty[Int]

        for edge <- edges do
          val a = edge(0)
          val b = edge(1)
          tree = tree.updatedWith(a) {
            case None    => Some(List(b))
            case Some(l) => Some(b +: l)
          }
          tree = tree.updatedWith(b) {
            case None    => Some(List(a))
            case Some(l) => Some(a +: l)
          }

        var maxDiameter = 0
        def maxDepth(node: Int): Int =
          if visited.contains(node) then 0
          else
            visited = visited + node
            var firstMaxDepth  = 0
            var secondMaxDepth = 0
            tree.get(node).foreach { children =>
              for child <- children do
                val childDepth = maxDepth(child)
                if childDepth >= firstMaxDepth then
                  secondMaxDepth = firstMaxDepth
                  firstMaxDepth = childDepth
                else if childDepth > secondMaxDepth then secondMaxDepth = childDepth
            }

            val myDiameter = firstMaxDepth + secondMaxDepth
            maxDiameter = math.max(maxDiameter, myDiameter)
            firstMaxDepth + 1

        maxDepth(edges(0)(0))
        maxDiameter
  }
//leetcode submit region end(Prohibit modification and deletion)

  @main
  def runTreeDiameter(): Unit =
    println(Solution.treeDiameter(Array(Array(0, 1), Array(0, 2))))

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
