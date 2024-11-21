package shoppingCart.programs

import cats.data.NonEmptyList
import cats.syntax.all.*
import cats.{ Monad, MonadThrow }
import org.typelevel.log4cats.Logger
import retry.RetryPolicies.{ exponentialBackoff, limitRetries }
import retry.RetryPolicy
import shoppingCart.BusinessError.*
import shoppingCart.Retriable.{ Orders, Payments }
import shoppingCart.{ Background, Retry }
import shoppingCart.algebras.*
import squants.Money

import scala.concurrent.duration.*

final case class Checkout[F[_]: MonadThrow: Retry: Logger: Background](payments: PaymentClient[F], cart: ShoppingCart[F], orders: Orders[F], policy: RetryPolicy[F]) {

  def process(userId: UserId, card: Card): F[OrderId] =
    cart.get(userId).flatMap { case CartTotal(items, total) =>
      for
        its <- ensureNonEmpty(items)
        pid <- processPayment(Payment(userId, total, card))
        oid <- createOrder(userId, pid, its, total)
        _   <- cart.delete(userId).attempt.void
      yield oid
    }

  private def processPayment(in: Payment): F[PaymentId] =
    Retry[F].retry(policy, Payments)(payments.process(in)).adaptError { case e =>
      PaymentError(Option(e.getMessage).getOrElse("Unknown"))
    }

  private def createOrder(userId: UserId, paymentId: PaymentId, items: NonEmptyList[CartItem], total: Money): F[OrderId] =
    val action = Retry[F].retry(policy, Orders)(orders.create(userId, paymentId, items, total)).adaptError { case e =>
      OrderError(e.getMessage)
    }

    def bgAction(fa: F[OrderId]): F[OrderId] =
      fa.onError { case _ =>
        Logger[F].error(s"Failed to create order for: ${paymentId}") *> Background[F].schedule(bgAction(fa), 1.hour)
      }

    bgAction(action)
  private def ensureNonEmpty[A](xs: List[A]): F[NonEmptyList[A]] =
    MonadThrow[F].fromOption(NonEmptyList.fromList(xs), EmptyCartError)
}
