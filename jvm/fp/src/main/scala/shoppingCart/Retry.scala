package shoppingCart

import cats.effect.Temporal
import org.typelevel.log4cats.Logger
import cats.syntax.all.*
import retry.RetryDetails.{ GivingUp, WillDelayAndRetry }
import retry.RetryPolicies.limitRetries
import retry.{ retryingOnAllErrors, RetryDetails, RetryPolicy }

enum Retriable:
  case Orders
  case Payments

  def show: String = toString

trait Retry[F[_]]:
  def retry[A](policy: RetryPolicy[F], retriable: Retriable)(fa: F[A]): F[A]

object Retry:
  def apply[F[_]: Retry]: Retry[F] = implicitly

  given forLoggerTemporal[F[_]: Logger: Temporal]: Retry[F] = new Retry[F]:
    override def retry[A](policy: RetryPolicy[F], retriable: Retriable)(fa: F[A]): F[A] =
      def onError(e: Throwable, details: RetryDetails): F[Unit] =
        details match
          case WillDelayAndRetry(_, retriesSoFar, _) =>
            Logger[F].error(s"Failed on ${retriable.show}. We retried $retriesSoFar times.")
          case GivingUp(totalRetries, totalDelay) =>
            Logger[F].error(s"Giving up on ${retriable.show} after $totalRetries retries.")

      retryingOnAllErrors[A](policy, onError)(fa)
