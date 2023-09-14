package chapter3.exercises.ex21

import chapter3.*
import chapter3.List
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.WordSpec
import utils.SOLUTION_HERE

// tag::init[]
fun add(xa: List<Int>, xb: List<Int>): List<Int> = when(xa) {
    is Nil -> Nil
    is Cons -> when(xb) {
        is Nil -> Nil
        is Cons -> Cons(xa.head + xb.head, add(xa.tail, xb.tail))
    }
}
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise21 : WordSpec({
    "list add" should {
        "!add elements of two corresponding lists" {
            add(List.of(1, 2, 3), List.of(4, 5, 6)) shouldBe
                List.of(5, 7, 9)
        }
    }
})
