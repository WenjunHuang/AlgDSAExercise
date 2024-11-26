package tofuLearn

import cats.Monad
import tofu.*
import tofu.syntax.monadic.*
import tofu.syntax.raise.*

case class ArithmeticError()
case class ParseError()

//def divide[F[_]: Monad](x: String, y: String)(implicit F1: Raise[F, ArithmeticError], F2: Raise[F, ParseError]): F[String] =
//  (
//    x.toIntOption.orRaise(ParseError()),
//    y.toIntOption
//      .orRaise(ParseError())
//      .verified(_ != 0)(ArithmeticError())
//  ).mapN(_ / _).map(_.toString)
