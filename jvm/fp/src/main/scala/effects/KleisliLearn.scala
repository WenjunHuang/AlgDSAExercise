package effects

import cats.data.Kleisli
import cats.syntax.all.*

val parse: Kleisli[Option, String, Int] =
  Kleisli((s: String) => if s.matches("-?[0-9]+") then Some(s.toInt) else None)

val reciprocal: Kleisli[Option, Int, Double] =
  Kleisli((i: Int) => if i != 0 then Some(1.0 / i) else None)

val parseAndReciprocal:Kleisli[Option,String,Double] =
  reciprocal.compose(parse)
