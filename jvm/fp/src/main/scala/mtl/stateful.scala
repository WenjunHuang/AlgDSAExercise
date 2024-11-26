package mtl

import cats.Monad
import cats.data.StateT
import cats.effect.{ IO, IOApp }
import cats.effect.kernel.Ref
import cats.syntax.all.*
import cats.effect.syntax.all.*
import cats.effect.std.Console
import cats.mtl.Stateful

case class FooState(str: String)
type HasFoo[F[_]] = Stateful[F, FooState]
object HasFoo {
  def apply[F[_]](implicit inst: Stateful[F, FooState]): HasFoo[F] = inst
}

def program[F[_]: Console: HasFoo: Monad]: F[Unit] =
  for {
    a <- HasFoo[F].get
    _ <- Console[F].println(a)
    _ <- HasFoo[F].set(FooState("foo"))
    b <- HasFoo[F].get
    _ <- Console[F].println(b)
  } yield ()

object StatefulRef {
  def of[F[_]: Ref.Make: Monad, A](init: A): F[Stateful[F, A]] =
    Ref.of[F, A](init).map { ref =>
      new Stateful[F, A] {
        override def monad: Monad[F] = implicitly

        override def get: F[A] = ref.get

        override def set(s: A): F[Unit] = ref.set(s)
      }
    }
}

object RunHasFoo extends IOApp.Simple {
  val p1: IO[Unit] = program[StateT[IO, FooState, *]]
    .run(FooState("init"))
    .void

  val p2: IO[Unit] = StatefulRef.of[IO, FooState](FooState("init")).flatMap { implicit st =>
    program[IO]
  }

  override def run: IO[Unit] = p2
}
