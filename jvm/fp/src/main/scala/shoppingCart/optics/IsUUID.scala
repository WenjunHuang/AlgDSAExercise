package shoppingCart.optics

import magnolia1.*
import monocle.Iso
import shoppingCart.optics

import java.util.UUID
import scala.deriving.Mirror

trait IsUUID[A]:
  def _UUID: Iso[UUID, A]

object IsUUID extends AutoDerivation[IsUUID]:
  override def split[T](sealedTrait: SealedTrait[IsUUID, T]):IsUUID[T] = new IsUUID[T]:
    override def _UUID: Iso[UUID, T] = Iso[UUID,T]()

  def apply[A: IsUUID]: IsUUID[A] = implicitly
  implicit val identityUUID: IsUUID[UUID] = new IsUUID[UUID]:
    val _UUID: Iso[UUID, UUID] = Iso[UUID, UUID](identity)(identity)
