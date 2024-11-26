package shoppingCart.algebras

import java.util.UUID

opaque type UserId            = UUID
opaque type UserName          = String
opaque type EncryptedPassword = String

trait Users[F[_]]:
  def find(username: UserName): F[Option[UserWithPassword]]
  def create(username: UserName, password: EncryptedPassword): F[UserId]
