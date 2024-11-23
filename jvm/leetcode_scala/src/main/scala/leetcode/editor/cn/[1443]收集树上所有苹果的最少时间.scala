package leetcode.editor.cn

object MinimumTimeToCollectAllApplesInATree {
  import scala.util.control.TailCalls.*
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def minTime(n: Int, edges: Array[Array[Int]], hasApple: List[Boolean]): Int = {
      var graph   = Map.from[Int, List[Int]]((0 until n).map((_, Nil)))
      var visited = Set.empty[Int]

      for edge <- edges do
        val a = edge(0)
        val b = edge(1)
        graph = graph.updatedWith(a) {
          case None    => Some(List(b))
          case Some(l) => Some(b +: l)
        }
        graph = graph.updatedWith(b) {
          case None    => Some(List(a))
          case Some(l) => Some(a +: l)
        }

      def collect(node: Int): Int =
        if visited.contains(node) then -1
        else
          visited = visited + node
          var sum = 0
          for child <- graph(node) do
            val subTime = collect(child)
            if subTime != -1 then sum += subTime + 2

          if sum > 0 then sum
          else if sum == 0 && hasApple(node) then 0
          else -1
          
      val res = collect(0)
      if res == -1 then 0 else res
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
