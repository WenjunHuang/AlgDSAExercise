package shoppingCart.algebras

import io.circe.{Decoder, Encoder}
import shoppingCart.algebras.Brands.{BrandId, BrandName}

import java.util.UUID
import shoppingCart.algebras.{Category, CategoryId}
import squants.Money

opaque type ItemId = UUID
object ItemId:
  def apply(uuid: UUID): ItemId         = uuid
  implicit val decoder: Decoder[ItemId] = Decoder.decodeUUID
  implicit val encoder: Encoder[ItemId] = Encoder.encodeUUID

opaque type ItemName = String
object ItemName:
  def apply(name: String): ItemName       = name
  implicit val decoder: Decoder[ItemName] = Decoder.decodeString
  implicit val encoder: Encoder[ItemName] = Encoder.encodeString

opaque type ItemDescription = String
object ItemDescription:
  def apply(desc: String): ItemDescription = desc
  implicit val decoder: Decoder[ItemDescription] = Decoder.decodeString
  implicit val encoder: Encoder[ItemDescription] = Encoder.encodeString

case class Item(uuid: ItemId, name: ItemName, description: ItemDescription, price: Money, brand: Brand, category: Category)
case class CreateItem(
    name: ItemName,
    description: ItemDescription,
    price: Money,
    brandId: BrandId,
    categoryId: CategoryId
)
case class UpdateItem(id: ItemId, price: Money)
trait Items[F[_]]:
  def findAll: F[List[Item]]
  def findBy(brand: BrandName): F[List[Item]]
  def create(item: CreateItem): F[ItemId]
  def update(item: UpdateItem): F[Unit]
