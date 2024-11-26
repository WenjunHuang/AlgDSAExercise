package tofuLearn

import cats.Monad
import cats.effect.{IO, IOApp}
import cats.syntax.all.*
import tofu.syntax.logging.*
import tofu.logging.{Loggable, LoggedValue, Logging}
import tofu.logging.derivation.hidden
import tofu.logging.derivation.loggable

import java.util.UUID
type CardNumber = String


case class Client(name: String, @hidden cardNumber: CardNumber, id: UUID)
object Client:
  given instance: Loggable[Client] = loggable.autoDerived[Client]

def processPayment[F[_]: Monad: Logging](client: Client, amount: Long): F[Unit] =
  for
    _ <- info"Processing payment for $client"
    _ <- warn"Amount $amount is lower than zero!".whenA(amount < 0)
  yield ()

//object RunLogging extends IOApp.Simple{
//  override def run: IO[Unit] =
//    val client = Client("Wenjun","abcd",UUID.randomUUID())
//    processPayment[IO](client,100)
//}
