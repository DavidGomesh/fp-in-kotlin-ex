package chapter3.solutions.ex16

import chapter3.Cons
import chapter3.List
import chapter3.foldRight
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.WordSpec

// tag::init[]
fun doubleToString(xs: List<Double>): List<String> =
    foldRight(
        xs,
        List.empty(),
        { d, ds ->
            Cons(d.toString(), ds)
        })
// end::init[]

class Solution16 : WordSpec({
    "list doubleToString" should {
        "convert every double element to a string" {
            doubleToString(
                List.of(1.1, 1.2, 1.3, 1.4)
            ) shouldBe List.of("1.1", "1.2", "1.3", "1.4")
        }
    }
})
