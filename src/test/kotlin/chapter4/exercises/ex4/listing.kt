package chapter4.exercises.ex4

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import chapter4.*
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.WordSpec
import utils.SOLUTION_HERE

//tag::init[]
fun <A> sequence(xs: List<Option<A>>): Option<List<A>> =
    xs.foldRight(Some(List.empty())) {
        oa, ola -> map2(oa, ola) {
            a, la -> Cons(a, la)
        }
    }
//end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise4 : WordSpec({

    "sequence" should {
        "turn a list of some options into an option of list" {
            val lo =
                List.of(Some(10), Some(20), Some(30))
            sequence(lo) shouldBe Some(List.of(10, 20, 30))
        }
        "turn a list of options containing none into a none" {
            val lo =
                List.of(Some(10), None, Some(30))
            sequence(lo) shouldBe None
        }
    }
})
