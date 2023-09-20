package chapter4.exercises.ex7

import chapter3.Cons
import chapter3.List
import chapter4.*
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.WordSpec
import utils.SOLUTION_HERE

//TODO: Enable tests by removing `!` prefix
class Exercise7 : WordSpec({

    //tag::init[]
    fun <E, A, B> traverse(xs: List<A>, f: (A) -> Either<E, B>): Either<E, List<B>> =
        xs.foldRight(Right(List.empty())) {
            a, elb -> map2(f(a), elb) {
                b, lb -> Cons(b, lb)
            }
        }

    fun <E, A> sequence(es: List<Either<E, A>>): Either<E, List<A>> =
        traverse(es) { it }

    //end::init[]

    fun <A> catches(a: () -> A): Either<String, A> =
        try {
            Right(a())
        } catch (e: Throwable) {
            Left(e.message!!)
        }

    "traverse" should {
        """return a right either of a transformed list if all
            transformations succeed""" {
            val xa = List.of("1", "2", "3", "4", "5")

            traverse(xa) { a ->
                catches { Integer.parseInt(a) }
            } shouldBe Right(List.of(1, 2, 3, 4, 5))
        }

        "return a left either if any transformations fail" {
            val xa = List.of("1", "2", "x", "4", "5")

            traverse(xa) { a ->
                catches { Integer.parseInt(a) }
            } shouldBe Left(
                """For input string: "x""""
            )
        }
    }
    "sequence" should {
        "turn a list of right eithers into a right either of list" {
            val xe: List<Either<String, Int>> =
                List.of(Right(1), Right(2), Right(3))

            sequence(xe) shouldBe Right(List.of(1, 2, 3))
        }

        """convert a list containing any left eithers into a
            left either""" {
            val xe: List<Either<String, Int>> =
                List.of(Right(1), Left("boom"), Right(3))

            sequence(xe) shouldBe Left("boom")
        }
    }
})
