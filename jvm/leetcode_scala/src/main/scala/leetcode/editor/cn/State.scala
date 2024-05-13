package leetcode.editor.cn
import scala.util.control.TailCalls.*

/** 栈安全的State Monad。可用于leetcode的用fp方式来解决问题。
  */
case class State[S, A](run: S => TailRec[(S, A)]):
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

object StateOps:
  def point[S, A](a: A): State[S, A] = State(s => done((s, a)))

  def get[S]: State[S, S] = State(s => done((s, s)))

  def put[S](s: S): State[S, Unit] = State(_ => done((s, ())))

  def modify[S](ss: S => S): State[S, Unit] =
    for { s <- get[S]; _ <- put(ss(s)) } yield ()

def sumState(nums: List[Int]): State[Long, Unit] =
  import StateOps.*
  nums match
    case Nil => point(())
    case x :: xs =>
      for
        _   <- modify[Long](sum => sum + x)
        sum <- sumState(xs)
      yield ()

@main
def stateTest(): Unit =
  println(sumState((1 to 100000).toList).execState(0L)) // 50005000
