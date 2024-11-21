package shoppingCart.algebras

import java.util.UUID

opaque type JwtToken = String
opaque type Password = String
case class User(id: UserId, name: UserName)
trait Auth[F[_]]:
  def findUser(token: JwtToken): F[Option[User]]
  def newUser(username: UserName, password: Password): F[JwtToken]
  def login(username:UserName,password: Password):F[JwtToken]
  def logout(token:JwtToken,userName: UserName):F[Unit]
