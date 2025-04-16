package com.tgyuu.androidmajorcomponent.ui.component.calendar

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import java.time.DayOfWeek
import java.time.LocalDate

class DateUtilTest : BehaviorSpec({
    given("날짜가 주어졌을 때") {
        val givenDate = LocalDate.of(2025, 4, 16)

        `when`("그 달의 1일의 요일을 구해서") {
            val actual = getFirstDayOfWeek(givenDate)

            then("반환할 수 있다.") {
                val expected = DayOfWeek.TUESDAY  // 2025-04-01은 화요일
                actual shouldBe expected
            }
        }
    }
})
