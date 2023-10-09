package chapter6.exercises.ex6

// import chapter6.RNG
import chapter6.Rand
import chapter6.rng1
import chapter6.unit
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.WordSpec
import utils.SOLUTION_HERE

//TODO: Enable tests by removing `!` prefix
class Exercise6 : WordSpec({

    //tag::init[]
    fun <A, B, C> map2(ra: Rand<A>, rb: Rand<B>, f: (A, B) -> C): Rand<C> =
        { rng0 ->
            val (n1, rng1) = ra(rng0)
            val (n2, rng2) = rb(rng1)
            f(n1, n2) to rng2
        }
    //end::init[]

    "map2" should {
        "combine the results of two actions" {

            val combined: Rand<String> =
                map2(
                    unit(1.0),
                    unit(1), { d, i ->
                        ">>> $d double; $i int"
                    })

            combined(rng1).first shouldBe ">>> 1.0 double; 1 int"
        }
    }
})
