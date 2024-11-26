package shoppingCart.routes.secured

import cats.Monad
import org.http4s.AuthedRoutes
import org.http4s.circe.JsonDecoder
import org.http4s.dsl.Http4sDsl
import shoppingCart.algebras.ShoppingCart

final case class CartRoutes[F[_]:JsonDecoder:Monad](shoppingCart: ShoppingCart[F]) extends Http4sDsl[F]:
  private[routes] val prefixPath = "/cart"
