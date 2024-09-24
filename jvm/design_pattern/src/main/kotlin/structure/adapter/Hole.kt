package structure.adapter

import kotlin.math.sqrt

open class RoundPeg(val radius: Double) {
}

class RoundHole(val radius: Double) {

    fun fits(roundPeg: RoundPeg): Boolean {
        return radius >= roundPeg.radius
    }
}

class SquarePeg(val width: Double) {}

class SquarePegAdapter(val peg: SquarePeg) : RoundPeg(peg.width * sqrt(2.0) / 2) {

}

fun main() {
    val hole = RoundHole(5.0)
    val rpeg = RoundPeg(5.0)
    println(hole.fits(rpeg))

    val smallSqpeg = SquarePeg(5.0)
    val largeSqpeg = SquarePeg(10.0)
    val smallSqpegAdapter = SquarePegAdapter(smallSqpeg)
    val largeSqpegAdapter = SquarePegAdapter(largeSqpeg)

    println(hole.fits(smallSqpegAdapter))
    println(hole.fits(largeSqpegAdapter))
}
