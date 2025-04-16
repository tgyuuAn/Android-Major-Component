package com.tgyuu.androidmajorcomponent.ui.component.calendar.core

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly
import java.time.LocalDate

class CalendarDateTest : BehaviorSpec({

    given("특정 날짜가 주어졌을 때") {
        val givenDate = LocalDate.of(2025, 5, 16)

        `when`("6*7 달력에 이전 달이 보이는 날짜를 구해서") {
            val actual = getPreviousMonthDatesToShow(givenDate)

            then("반환할 수 있다.") {
                val expected = listOf(
                    CalendarDate(LocalDate.of(2025, 4, 28), isCurrentMonth = false),
                    CalendarDate(LocalDate.of(2025, 4, 29), isCurrentMonth = false),
                    CalendarDate(LocalDate.of(2025, 4, 30), isCurrentMonth = false),
                )
                actual shouldContainExactly expected
            }
        }

        `when`("6*7 달력에 보이는 이번 달 날짜를 구해서") {
            val actual = getCurrentMonthDatesToShow(givenDate)

            then("반환할 수 있다.") {
                val expected = (1..31).map {
                    CalendarDate(LocalDate.of(2025, 5, it), isCurrentMonth = true)
                }
                actual shouldContainExactly expected
            }
        }

        `when`("6*7 달력에 보이는 다음 달 날짜를 구해서") {
            val actual = getNextMonthDatesToShow(givenDate)

            then("반환할 수 있다.") {
                val expected = (1..8).map {
                    CalendarDate(LocalDate.of(2025, 6, it), isCurrentMonth = false)
                }
                actual shouldContainExactly expected
            }
        }

        `when`("6*7 달력에 보이는 이전, 현재, 다음달 날짜를 구해서") {
            val actual = getCalendarDates(givenDate)

            then("반환될 수 있다.") {
                val expected = buildList {
                    addAll(listOf(28, 29, 30).map {
                        CalendarDate(LocalDate.of(2025, 4, it), isCurrentMonth = false)
                    })
                    addAll((1..31).map {
                        CalendarDate(LocalDate.of(2025, 5, it), isCurrentMonth = true)
                    })
                    addAll((1..8).map {
                        CalendarDate(LocalDate.of(2025, 6, it), isCurrentMonth = false)
                    })
                }

                actual shouldContainExactly expected
            }
        }
    }
})
