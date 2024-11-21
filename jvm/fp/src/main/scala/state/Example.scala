package state

import cats.effect.kernel.Sync
import cats.effect.std.Console
import cats.effect.{ IO, IOApp, Ref }
import cats.syntax.all.*
import cats.{ effect, Applicative, Monad }

import java.time.{ Instant, ZoneId, ZoneOffset, ZonedDateTime }
import java.time.temporal.ChronoField

trait Counter[F[_]]:
  def incr: F[Unit]
  def get: F[Int]

object Counter:
  def make[F[_]: Ref.Make: Sync]: F[Counter[F]] =
    Ref.of[F, Int](0).map { ref =>
      new Counter:
        override def incr: F[Unit] = ref.update(_ + 1)

        override def get: F[Int] = ref.get
    }

trait Log[F[_]]:
  def info(str: String): F[Unit]

object Log:
  def apply[F[_]: Log]: Log[F] = implicitly

  def noop[F[_]: Applicative]: Log[F] =
    new Log[F]:
      override def info(str: String): F[Unit] = Applicative[F].pure(())

  given forConsole[F[_]: Console]: Log[F] =
    new Log[F]:
      override def info(str: String): F[Unit] = Console[F].println(str)

trait Time[F[_]]:
  def getHour: F[Time.Hour]

object Time {
  def apply[F[_]: Time]: Time[F] = implicitly
  def of[F[_]: Applicative](inst: Instant): Time[F] =
    new Time[F]:
      override def getHour: F[Hour] = Hour.from(inst).pure[F]

  opaque type Hour = Int
  object Hour:
    def from(instant: Instant): Hour =
      instant.atZone(ZoneOffset.UTC).getHour
  extension (h: Hour) def value: Int = h

  given forSync[F[_]: Sync]: Time[F] =
    new Time[F]:
      override def getHour: F[Hour] = Sync[F].delay(Hour.from(Instant.now()))
}

def constrained[F[_]: Log: Monad: Time](c: Counter[F]): F[Int] =
  for
    x <- c.get
    _ <- Log[F].info(s"Current count: $x")
    t <- implicitly[Time[F]].getHour
    _ <- Log[F].info(s"Current hour: $t")
    _ <- c.incr.replicateA(10).void.whenA(t.value >= 12)
    y <- c.get
  yield y

def testIncrBy10: IO[Unit] =
  given time: Time[IO] = Time.of[IO](ZonedDateTime.of(2024, 11, 21, 14, 49, 0, 0, ZoneId.systemDefault()).toInstant)
  given log: Log[IO]   = Log.noop[IO]
  for
    c <- Counter.make[IO]
    p <- constrained[IO](c)
  yield assert(p == 10, "Expected result == 10")

object CounterApp extends IOApp.Simple:
  override def run: IO[Unit] =
    testIncrBy10
