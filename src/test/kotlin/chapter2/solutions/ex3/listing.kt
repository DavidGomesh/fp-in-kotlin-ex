package chapter2.solutions.ex3

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

class Solution3 : WordSpec({
    // tag::init[]
    fun <A, B, C> curry(f: (A, B) -> C): (A) -> (B) -> C =
        { a: A -> { b: B -> f(a, b) } }
    // end::init[]

    "curry" should {
        """break down a function that takes multiple arguments
            into a series of functions that each take only
            one argument""" {

                val f: (Int) -> (Int) -> String =
                    curry { a: Int, b: Int -> "$a:$b" }
                val y = f(1)(2)
                val z = f(1)(3)
                y shouldBe "1:2"
                z shouldBe "1:3"
            }
    }
})
