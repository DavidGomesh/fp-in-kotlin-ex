package chapter6.exercises.ex3

import chapter6.RNG
import chapter6.solutions.ex2.double
// import chapter6.solutions.ex2.double
// import chapter6.solutions.ex5.doubleR
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.WordSpec
import utils.SOLUTION_HERE

//TODO: Enable tests by removing `!` prefix
class Exercise3 : WordSpec({

    //tag::init[]
    fun intDouble(rng: RNG): Pair<Pair<Int, Double>, RNG> =
        rng.nextInt().let { (i, rng) ->
            double(rng).let { (d, rng) -> (i to d) to rng }
        }

    fun doubleInt(rng: RNG): Pair<Pair<Double, Int>, RNG> =
         intDouble(rng).let { (t, rng) ->
             (t.second to t.first) to rng
         }

    fun double3(rng: RNG): Pair<Triple<Double, Double, Double>, RNG> =
        double(rng).let { (d1, rng) ->
            double(rng).let { (d2, rng) ->
                double(rng).let { (d3, rng) ->
                    Triple(d1, d2, d3) to rng
                }
            }
        }
    //end::init[]

    "intDouble" should {

        val doubleBelowOne =
            Int.MAX_VALUE.toDouble() / (Int.MAX_VALUE.toDouble() + 1)

        val unusedRng = object : RNG {
            override fun nextInt(): Pair<Int, RNG> = TODO()
        }

        val rng3 = object : RNG {
            override fun nextInt(): Pair<Int, RNG> =
                Int.MAX_VALUE to unusedRng
        }

        val rng2 = object : RNG {
            override fun nextInt(): Pair<Int, RNG> =
                Int.MAX_VALUE to rng3
        }

        val rng = object : RNG {
            override fun nextInt(): Pair<Int, RNG> =
                Int.MAX_VALUE to rng2
        }

        "generate a pair of int and double" {
            val (id, _) = intDouble(rng)
            val (i, d) = id
            i shouldBe Int.MAX_VALUE
            d shouldBe doubleBelowOne
        }

        "generate a pair of double and int" {
            val (di, _) = doubleInt(rng)
            val (d, i) = di
            d shouldBe doubleBelowOne
            i shouldBe Int.MAX_VALUE
        }

        "generate a triple of double, double, double" {
            val (ddd, _) = double3(rng)
            val (d1, d2, d3) = ddd
            d1 shouldBe doubleBelowOne
            d2 shouldBe doubleBelowOne
            d3 shouldBe doubleBelowOne
        }
    }
})
