package com.tgyuu.androidmajorcomponent.ui.component.calendar

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import java.time.DayOfWeek
import java.time.LocalDate

class DateUtilTest : BehaviorSpec({
    given("특정 날짜가 주어졌을 때") {
        val givenDate = LocalDate.of(2025, 5, 16)

        `when`("달력에서 이전 달이 보이는 요일을 구해서") {
            val actual = getPreviousMonthDayOfWeeksToShow(givenDate)

            then("반환할 수 있다.") {
                // 2025-05-01은 목요일, 이전 달 요일에 월 화, 수가 보임
                val expected = listOf(
                    DayOfWeek.MONDAY,
                    DayOfWeek.TUESDAY,
                    DayOfWeek.WEDNESDAY,
                )
                actual shouldBe expected
            }
        }

        `when`("달력에서 이전 달이 보이는 날짜를 구해서") {
            val actual = getPreviousMonthDatesToShow(givenDate)

            then("반환할 수 있다.") {
                // 2025-05-01일 달력에서 4월 28일, 29일, 30일이 보임
                val expected = listOf(28, 29, 30)
                actual shouldBe expected
            }
        }
    }
})
