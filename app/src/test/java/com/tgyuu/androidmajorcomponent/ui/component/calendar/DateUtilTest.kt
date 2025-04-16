package com.tgyuu.androidmajorcomponent.ui.component.calendar

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class DateUtilTest : BehaviorSpec({

    given("특정 날짜가 주어졌을 때") {
        val givenDate = LocalDate.of(2025, 5, 16)

        `when`("6*7 달력에 이전 달이 보이는 날짜를 구해서") {
            val actual = getPreviousMonthDatesToShow(givenDate)

            then("반환할 수 있다.") {
                val expected = listOf(
                    LocalDate.of(2025, 4, 28),
                    LocalDate.of(2025, 4, 29),
                    LocalDate.of(2025, 4, 30),
                )
                actual shouldBe expected
            }
        }

        `when`("6*7 달력에 보이는 이번 달 날짜를 구해서") {
            val actual = getCurrentMonthDatesToShow(givenDate)

            then("반환할 수 있다.") {
                val expected = (1..31).map { LocalDate.of(2025, 5, it) }
                actual shouldBe expected
            }
        }

        `when`("6*7 달력에 보이는 다음 달 날짜를 구해서") {
            val actual = getNextMonthDatesToShow(givenDate)

            then("반환할 수 있다.") {
                val expected = (1..8).map { LocalDate.of(2025, 6, it) }
                actual shouldBe expected
            }
        }

        `when`("6*7 달력에 보이는 이전, 현재, 다음달 날짜를 구해서") {
            val actual = getDatesToShow(givenDate)

            then("반환할 수 있다.") {
                val expected = buildList {
                    addAll(
                        listOf(
                            LocalDate.of(2025, 4, 28),
                            LocalDate.of(2025, 4, 29),
                            LocalDate.of(2025, 4, 30),
                        )
                    )
                    addAll((1..31).map { LocalDate.of(2025, 5, it) })
                    addAll((1..8).map { LocalDate.of(2025, 6, it) })
                }

                actual shouldBe expected
            }
        }
    }
})
