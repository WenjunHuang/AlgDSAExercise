package leetcode.editor.cn

case class State[S, A](run: S => (S, A)) {
  def evalState(s: S): A = run(s)._2
  def execState(s: S): S = run(s)._1
  def map[B](ab: A => B): State[S, B] =
    State(s =>
      run(s) match {
        case (s, a) => (s, ab(a))
      }
    )
  def flatMap[B](afb: A => State[S, B]): State[S, B] =
    State(s =>
      run(s) match {
        case (s, a) => afb(a).run(s)
      }
    )

}
object State {
  def point[S, A](a: A): State[S, A] = State(s => (s, a))
  def get[S]: State[S, S] = State(s => (s, s))
  def gets[S, A](f: S => A): State[S, A] = State { (s: S) => (s, f(s)) }
  def put[S](s: S): State[S, Unit] = State(_ => (s, ()))
  def modify[S](ss: S => S): State[S, Unit] =
    for { s <- get[S]; _ <- put(ss(s)) } yield ()
}
