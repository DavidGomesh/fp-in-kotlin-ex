package chapter5.solutions.ex4

import chapter5.Stream
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.WordSpec

//tag::init[]
fun <A> Stream<A>.forAll(p: (A) -> Boolean): Boolean =
    foldRight({ true }, { a, b -> p(a) && b() })
//end::init[]

class Solution4 : WordSpec({

    "Stream.forAll" should {
        "ensure that all elements match the predicate" {
            val s = Stream.of(1, 2, 3, 4, 5)
            s.forAll { it < 6 } shouldBe true
        }
        """stop evaluating if one element does not satisfy
            the predicate""" {
                val s = Stream.of(1, 2, 3, 4, 5)
                s.forAll { it != 3 } shouldBe false
            }
    }
})
