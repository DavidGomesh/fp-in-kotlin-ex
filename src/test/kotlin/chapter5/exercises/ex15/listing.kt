package chapter5.exercises.ex15

import chapter3.List
import chapter4.None
import chapter4.Some
import chapter5.Cons
import chapter5.Empty
import chapter5.Stream
import chapter5.Stream.Companion.empty
import chapter5.Stream.Companion.unfold
import chapter5.toList
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.WordSpec
import utils.SOLUTION_HERE
import chapter3.Cons as ConsL
import chapter3.Nil as NilL

//TODO: Enable tests by removing `!` prefix
class Exercise15 : WordSpec({

    //tag::tails[]
    fun <A> Stream<A>.tails(): Stream<Stream<A>> =
        unfold(this) { s -> when(s) {
            is Empty -> None
            is Cons -> Some(s to s.tail())
        } }
    //end::tails[]

    fun <A, B> List<A>.map(f: (A) -> B): List<B> = when (this) {
        is ConsL -> ConsL(f(this.head), this.tail.map(f))
        is NilL -> NilL
    }

    "Stream.tails" should {
        "return the stream of suffixes of the input sequence" {
            Stream.of(1, 2, 3)
                .tails()
                .toList()
                .map { it.toList() } shouldBe
                List.of(
                    ConsL(1, ConsL(2, ConsL(3, NilL))),
                    ConsL(2, ConsL(3, NilL)),
                    ConsL(3, NilL),
                    NilL
                )
        }
    }
})
