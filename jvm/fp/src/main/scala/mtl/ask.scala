package mtl

import cats.FlatMap
import cats.data.Kleisli
import cats.effect.{ IO, IOApp }
import cats.effect.std.Console
import cats.mtl.Ask
import cats.syntax.all.*

package ask {

  import cats.Applicative

  final case class Foo(value: String)

  final case class Bar(value: Int)

  final case class Ctx(foo: Foo, bar: Bar)

  type HasFoo[F[_]] = Ask[F, Foo]
  type HasBar[F[_]] = Ask[F, Bar]
  type HasCtx[F[_]] = Ask[F, Ctx]

  object ManualAsk {
    def of[F[_]:Applicative,A](ctx:A):Ask[F,A] =
      new Ask[F,A]{
        override def applicative: Applicative[F] = implicitly
        override def ask[A2 >: A]: F[A2] = ctx.pure[F].widen
      }
  }

  def program[F[_] : Console : FlatMap : HasCtx]: F[Unit] =
    Ask[F, Ctx].ask.flatMap(Console[F].println)

  object app extends IOApp.Simple {
    override def run: IO[Unit] =
      program[Kleisli[IO, Ctx, *]].run(Ctx(Foo("foo"), Bar(42)))
  }
}
