package com.tgyuu.androidmajorcomponent.ui.component.calendar

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class CalendarStateTest : BehaviorSpec(body = {
    given("달력은") {
        `when`("생성될 때") {
            val dateDatas = listOf(
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2024, 12, 31),
            )
            dateDatas.forEach { date ->
                val calendarState = CalendarState(date)

                then("기존에 선택된 날짜로 되돌아오기 위해 기존에 선택된 날짜를 저장한다.") {
                    val expected = date
                    val actual = calendarState.originSelectedDate
                    actual shouldBe expected
                }
            }
        }

        `when`("생성될 때 아무런 날짜도 선택하지 않았을 경우"){
            val calendarState = CalendarState()

            then("오늘 날짜를 저장한다.") {
                val expected = LocalDate.now()
                val actual = calendarState.originSelectedDate
                actual shouldBe expected
            }
        }
    }
})
