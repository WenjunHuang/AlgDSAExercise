package shoppingCart.algebras

import squants.Money

import java.util.UUID

opaque type PaymentId = UUID

case class Card()

case class Payment(id: UserId, total: Money, card: Card)

trait PaymentClient[F[_]]:
  def process(payment: Payment): F[PaymentId]
