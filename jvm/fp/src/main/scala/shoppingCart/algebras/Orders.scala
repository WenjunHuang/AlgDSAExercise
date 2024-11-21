package shoppingCart.algebras

import cats.data.NonEmptyList
import squants.Money

import java.util.UUID

opaque type OrderId   = UUID
case class Order(id: OrderId, pid: PaymentId, items: Map[ItemId, Quantity], total: Money)
trait Orders[F[_]]:
  def get(userId: UserId, orderId: OrderId): F[Option[Order]]
  def findBy(userId: UserId): F[List[Order]]
  def create(userId: UserId, paymentId: PaymentId, items: NonEmptyList[CartItem], total: Money): F[OrderId]
