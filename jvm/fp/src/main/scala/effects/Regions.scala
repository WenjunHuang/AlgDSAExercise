package effects

import cats.effect.std.{ Semaphore, Supervisor }
import cats.effect.{ IO, IOApp }
import cats.effect.syntax.all.*

import scala.util.Random
import scala.concurrent.duration.*

object Regions extends IOApp.Simple:
  override def run: IO[Unit] =
    Supervisor[IO].use { s =>
      Semaphore[IO](1).flatMap { sem =>
        s.supervise(p1(sem).foreverM).void *>
          s.supervise(p2(sem).foreverM).void *>
          IO.sleep(5.seconds).void
      }
    }

  def randomSleep: IO[Unit] =
    IO(Random.nextInt(100)).flatMap { ms =>
      IO.sleep((ms + 700).millis)
    }

  def p1(sem: Semaphore[IO]): IO[Unit] =
    sem.permit.surround(IO.println("Running P1")) >> randomSleep

  def p2(sem: Semaphore[IO]): IO[Unit] =
    sem.permit.surround(IO.println("Running P2")) >> randomSleep
