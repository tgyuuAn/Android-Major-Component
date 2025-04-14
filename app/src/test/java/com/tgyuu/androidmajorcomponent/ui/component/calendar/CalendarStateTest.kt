package com.tgyuu.androidmajorcomponent.ui.component.calendar

import io.kotest.core.spec.style.BehaviorSpec
import java.time.LocalDate

class CalendarStateTest : BehaviorSpec({
    given("달력은") {
        `when`("생성될 때") {
            val calendarState = CalendarState(LocalDate.of(2025, 4, 1))

            then("기존에 선택된 날짜로 되돌아오기 위해 기존에 선택된 날짜를 저장한다.") {
                val expected = LocalDate.of(2025, 4, 1)
                val actual = calendarState.originSelectedDate
                actual shouldBe expected
            }
        }
    }
})
