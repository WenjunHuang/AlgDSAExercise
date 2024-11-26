package shoppingCart.routes

import cats.Monad
import org.http4s.{ HttpRoutes, QueryParamDecoder }
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router
import shoppingCart.algebras.Brands.BrandName
import shoppingCart.algebras.Items
import org.http4s.circe.CirceEntityEncoder.*
import io.circe.generic.auto.deriveEncoder

opaque type BrandParam = String
object BrandParam:
  implicit val paramDecoder: QueryParamDecoder[BrandParam] = QueryParamDecoder[String]
  extension (b: BrandParam) def toDomain: BrandName        = BrandName(b.toLowerCase().capitalize)

final case class ItemRoutes[F[*]: Monad](items: Items[F]) extends Http4sDsl[F]:
  private[routes] val prefixPath = "/items"

  object BrandQueryParam extends OptionalQueryParamDecoderMatcher[BrandParam]("brand")

//  private val httpRoutes: HttpRoutes[F] = HttpRoutes.of[F] { case GET -> Root :? BrandQueryParam(brand) =>
//    Ok(brand.fold(items.findAll)(b => items.findBy(b.toDomain)))
//  }
//
//  val routes: HttpRoutes[F] = Router(prefixPath -> httpRoutes)
