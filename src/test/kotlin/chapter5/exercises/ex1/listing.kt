package chapter5.exercises.ex1

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import chapter5.Empty
import chapter5.Stream
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.WordSpec
import utils.SOLUTION_HERE

//TODO: Enable tests by removing `!` prefix
class Exercise1 : WordSpec({
    //tag::init[]
    fun <A> Stream<A>.toList(): List<A> =
        this.foldRight({ List.empty() }) { a, z -> Cons(a, z()) }
    //end::init[]

    "Stream.toList" should {
        "force the stream into an evaluated list" {
            val s = Stream.of(1, 2, 3, 4, 5)
            s.toList() shouldBe List.of(1, 2, 3, 4, 5)
        }
    }
})
