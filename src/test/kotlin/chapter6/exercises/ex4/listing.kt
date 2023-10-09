package chapter6.exercises.ex4

// import chapter3.Cons
import chapter3.Cons
import chapter3.List
import chapter3.Nil
// import chapter3.Nil
import chapter6.RNG
import chapter6.rng1
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.WordSpec
import utils.SOLUTION_HERE

//TODO: Enable tests by removing `!` prefix
class Exercise4 : WordSpec({

    //tag::init[]
    fun ints(count: Int, rng: RNG): Pair<List<Int>, RNG> =
        if (count == 0) Nil to rng else rng.nextInt().let {
            (n, rng) -> ints(count - 1, rng).let {
                (xs, rng) -> Cons(n, xs) to rng
            }
        }
    //end::init[]

    "ints" should {
        "generate a list of ints of a specified length" {

            ints(5, rng1) shouldBe (List.of(1, 1, 1, 1, 1) to rng1)
        }
    }
})
