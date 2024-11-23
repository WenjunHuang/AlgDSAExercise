package shoppingCart.algebras

import io.circe.Encoder
import monocle.Iso
import io.circe.generic.auto._, io.circe.syntax._
enum Status:
  case Okay
  case Unreachable

  val _Bool: Iso[Status, Boolean] =
    Iso[Status, Boolean] {
      case Okay        => true
      case Unreachable => false
    }(if (_) Okay else Unreachable)

object Status:
  given encoder: Encoder[Status] = Encoder.forProduct1("status")(_.toString)

case class RedisStatus(value: Status)
case class MySQLStatus(value: Status)

case class AppStatus(redis: RedisStatus, mysql: MySQLStatus)
trait HealthCheck[F[_]]:
  def status: F[AppStatus]
