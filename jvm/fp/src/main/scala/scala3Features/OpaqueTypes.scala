package scala3Features

import scala.annotation.targetName

object OpaqueTypes:
  opaque type Logarithm = Double
  opaque type Username  = String
  opaque type Email     = String

  object Username:
    def apply(v: String): Username = v

  object Email:
    def apply(v: String): Email = if !v.contains('@') then throw Exception("not a valid email format") else v

  object Logarithm:
    def apply(d: Double): Logarithm = math.log(d)

  extension (x: Logarithm)
    def toDouble: Double = math.exp(x)
    @targetName("plus")
    def +(y: Logarithm): Logarithm = Logarithm(math.exp(x) + math.exp(y))
    @targetName("mul")
    def *(y: Logarithm): Logarithm = x + y

@main
def useLogarithms(): Unit =
  import OpaqueTypes.*
  val log2 = Logarithm(2.0)
  val log3 = Logarithm(3.0)
  println((log2 * log3).toDouble)
  println((log2 + log3).toDouble)

  Email("www@example.com")
