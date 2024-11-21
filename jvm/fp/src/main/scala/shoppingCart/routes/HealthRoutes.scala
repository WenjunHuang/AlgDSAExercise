package shoppingCart.routes

import cats.Monad
import shoppingCart.algebras.HealthCheck

final case class HealthRoutes[F[_]:Monad] (healthCheck:HealthCheck[F])
