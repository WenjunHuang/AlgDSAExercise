package mtl

import cats.MonadError
import cats.data.{EitherT, StateT}
import cats.effect.{IO, IOApp}
import cats.implicits.*
import cats.mtl.Stateful

def checkState[F[_]](implicit S: Stateful[F, Int], E: MonadError[F, Exception]): F[String] =
  for {
    currentState <- S.get
    result       <- if (currentState > 0) E.raiseError(new Exception("Too large")) else E.pure("OK")
  } yield {
    result
  }

object RunCheckState extends IOApp.Simple {
  override def run: IO[Unit] = IO {
   checkState[EitherT[StateT[IO, Int, *],Exception,*]]
     .value.run(0).flatMap(v =>
     IO.println(v))
  }
}
