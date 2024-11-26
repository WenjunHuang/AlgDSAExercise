package shoppingCart.algebras
import io.circe.{ Decoder, Encoder }
import monocle.Iso
import shoppingCart.algebras.Brands.*
import shoppingCart.optics.IsUUID

import java.util.UUID
object Brands:
  opaque type BrandName = String
  object BrandName:
    def apply(name: String): BrandName = name
    given Decoder[BrandName]           = Decoder.decodeString
    given Encoder[BrandName]           = Encoder.encodeString

  opaque type BrandId = UUID
  object BrandId:
    def apply(id: UUID): BrandId            = id
    extension (bid: BrandId) def uuid: UUID = bid
    given Decoder[BrandId]                  = Decoder.decodeUUID
    given Encoder[BrandId]                  = Encoder.encodeUUID
    given IsUUID[BrandId] = IsUUID.derived
//    inline given IsUUID[BrandId] = new IsUUID[BrandId]:
//      override def _UUID: Iso[BrandId, UUID] = Iso[BrandId, UUID](identity)(identity)

case class Brand(uuid: BrandId, name: BrandName)

trait Brands[F[_]]:
  def findAll: F[List[Brand]]
  def create(name: BrandName): F[BrandId]
