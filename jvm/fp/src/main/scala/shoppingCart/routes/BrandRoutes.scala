package shoppingCart.routes

import cats.Monad
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router
import org.http4s.circe.CirceEntityEncoder.*
import io.circe.generic.auto.deriveEncoder
import shoppingCart.algebras.Brands

final case class BrandRoutes[F[_]: Monad](brands: Brands[F]) extends Http4sDsl[F]:
  private[routes] val prefixPath = "/brands"

  private val httpRoutes: HttpRoutes[F] = HttpRoutes.of[F] { case GET -> Root =>
    Ok(brands.findAll)
  }

  val routes: HttpRoutes[F] = Router(prefixPath -> httpRoutes)
