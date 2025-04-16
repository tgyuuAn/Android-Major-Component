package com.tgyuu.androidmajorcomponent.ui.component.calendar

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class DateUtilTest : BehaviorSpec({
    given("특정 날짜가 주어졌을 때") {
        val givenDate = LocalDate.of(2025, 5, 16)

        `when`("달력에서 이전 달이 보이는 날짜를 구해서") {
            val actual = getPreviousMonthDatesToShow(givenDate)

            then("반환할 수 있다.") {
                // 2025-05-01일 달력에서 4월 28일, 29일, 30일이 보임
                val expected = listOf(28, 29, 30)
                actual shouldBe expected
            }
        }

        `when`("달력에 보이는 이번 달 날짜를 구해서") {
            val actual = getCurrentMonthDatesToShow(givenDate)

            then("반환할 수 있다.") {
                val expected = (1..31).toList()
                actual shouldBe expected
            }
        }
    }
})
