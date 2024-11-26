package shoppingCart.domain

import cats.Functor
import cats.syntax.all.*
import shoppingCart.algebras.GenUUID
import shoppingCart.optics.IsUUID

object ID:
  def make[F[_]: Functor: GenUUID, A: IsUUID]: F[A] =
    GenUUID[F].make.map(IsUUID[A]._UUID.get)

  def read[F[_]: Functor: GenUUID, A: IsUUID](str: String): F[A] =
    GenUUID[F].read(str).map(IsUUID[A]._UUID.get)
