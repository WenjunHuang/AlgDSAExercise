package leetcode.editor.cn

object UniqueBinarySearchTrees {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def numTrees(n: Int): Int =
      impl().evalState(Context(1, n))

    private def impl(): State[Context, Int] =
      import StateOps.*
      get[Context].flatMap { s =>
        if s.low > s.high then point(1)
        else if s.memo.contains((s.low, s.high)) then point(s.memo((s.low, s.high)))
        else
          for
            v <- (s.low to s.high).foldLeft(point[Context, Int](0))((acc, num) =>
              for
                c     <- acc
                left  <- modify[Context](st => st.copy(low = s.low, high = num - 1)).flatMap(_ => impl())
                right <- modify[Context](st => st.copy(low = num + 1, high = s.high)).flatMap(_ => impl())
              yield c + left * right
            )
            _ <- modify[Context](st => st.copy(memo = st.memo.updated((s.low, s.high), v)))
          yield v
      }

    private case class Context(low: Int, high: Int, memo: Map[(Int, Int), Int] = Map.empty)
    import scala.util.control.TailCalls.*

    /** 栈安全的State Monad。可用于leetcode的用fp方式来解决问题。
      */
    private case class State[S, A](run: S => TailRec[(S, A)]) {
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

    private object StateOps {
      def point[S, A](a: A): State[S, A] = State(s => done((s, a)))

      def get[S]: State[S, S] = State(s => done((s, s)))

      def put[S](s: S): State[S, Unit] = State(_ => done((s, ())))

      def modify[S](ss: S => S): State[S, Unit] =
        for { s <- get[S]; _ <- put(ss(s)) } yield ()
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}

@main def runUniqueBinarySearchTrees: Unit =
  println(
    UniqueBinarySearchTrees.Solution.numTrees(19)
  )
