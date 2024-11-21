package shoppingCart.algebras

import squants.Money

opaque type Quantity = Int
opaque type Cart     = Map[ItemId, Quantity]

case class CartItem(item: Item, quantity: Quantity)
case class CartTotal(items: List[CartItem], total: Money)
trait ShoppingCart[F[_]]:
  def add(userId: UserId, itemId: ItemId, quantity: Quantity): F[Unit]
  def get(userId: UserId): F[CartTotal]
  def delete(userId: UserId): F[Unit]
  def removeItem(userId: UserId, itemId: ItemId): F[Unit]
  def update(userId: UserId, cart: Cart): F[Unit]
