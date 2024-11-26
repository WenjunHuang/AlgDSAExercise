package shoppingCart.auth

import cats.Show
import dev.profunktor.auth.jwt.JwtSymmetricAuth
import io.circe.generic.auto.*
import io.circe.syntax.*
import shoppingCart.algebras.{EncryptedPassword, UserId, UserName}

object users:
  opaque type AdminJwtAuth = JwtSymmetricAuth
  opaque type UserJwtAuth = JwtSymmetricAuth

  case class User(id:UserId,name:UserName)

  case class UserWithPassword(id: UserId, name: UserName, password: EncryptedPassword)

  opaque type CommonUser = User
  opaque type AdminUser = User
