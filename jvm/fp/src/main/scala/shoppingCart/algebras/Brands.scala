package shoppingCart.algebras
import Brands.*
import io.circe.{ Decoder, Encoder }
import io.circe.generic.auto.*
import io.circe.syntax.*

import java.util.UUID
object Brands:
  opaque type BrandName = String
  object BrandName:
    def apply(name: String): BrandName = name
    implicit val decoder: Decoder[BrandName] = Decoder.decodeString
    implicit val encoder: Encoder[BrandName] = Encoder.encodeString

  opaque type BrandId = UUID
  object BrandId:
    def apply(id: UUID): BrandId            = id
    extension (bid: BrandId) def uuid: UUID = bid
    implicit val decoder: Decoder[BrandId]  = Decoder.decodeUUID
    implicit val encoder: Encoder[BrandId]  = Encoder.encodeUUID

case class Brand(uuid: BrandId, name: BrandName)

trait Brands[F[_]]:
  def findAll: F[List[Brand]]
  def create(name: BrandName): F[BrandId]
