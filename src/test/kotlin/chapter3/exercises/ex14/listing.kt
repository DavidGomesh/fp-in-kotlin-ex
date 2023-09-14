package chapter3.exercises.ex14

import chapter3.*
import chapter3.List
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.WordSpec
import utils.SOLUTION_HERE

// tag::init[]
fun <A> concat(lla: List<List<A>>): List<A> =
    foldLeft(lla, List.empty()) { la1, la2 -> append(la1, la2) }

fun <A> concat2(lla: List<List<A>>): List<A> =
    foldRight(lla, List.empty()) {
        la1, la2 -> foldRight(la1, la2) { a, la -> Cons(a, la) }
    }
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise14 : WordSpec({
    "list concat" should {
        "concatenate a list of lists into a single list" {
            concat(
                List.of(
                    List.of(1, 2, 3),
                    List.of(4, 5, 6)
                )
            ) shouldBe List.of(1, 2, 3, 4, 5, 6)

            concat2(
                List.of(
                    List.of(1, 2, 3),
                    List.of(4, 5, 6)
                )
            ) shouldBe List.of(1, 2, 3, 4, 5, 6)
        }
    }
})
