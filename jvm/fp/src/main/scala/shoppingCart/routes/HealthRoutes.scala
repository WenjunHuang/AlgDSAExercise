package shoppingCart.routes

import cats.Monad
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router
import shoppingCart.algebras.HealthCheck
import org.http4s.circe.CirceEntityEncoder.*
import io.circe.generic.auto.deriveEncoder

final case class HealthRoutes[F[_]: Monad](healthCheck: HealthCheck[F]) extends Http4sDsl[F]:
  private[routes] val prefixPath = "/healthcheck"

  private val httpRoutes: HttpRoutes[F] = HttpRoutes.of[F] { case GET -> Root =>
    Ok(healthCheck.status)
  }

  val routes: HttpRoutes[F] = Router(
    prefixPath -> httpRoutes
  )
