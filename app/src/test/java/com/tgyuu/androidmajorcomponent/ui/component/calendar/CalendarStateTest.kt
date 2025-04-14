package com.tgyuu.androidmajorcomponent.ui.component.calendar

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class CalendarStateTest : BehaviorSpec(body = {
    lateinit var calendarState: CalendarState

    beforeEach {
        calendarState = CalendarState(LocalDate.of(2025, 4, 1))
    }

    given("달력은") {
        `when`("특정한 날짜를 선택했을 경우") {
            val selectedDates = listOf(
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2024, 12, 31),
            )
            selectedDates.forEach { selectedDate ->
                then("선택한 날짜를 저장한다.") {
                    calendarState.onSelectDate(selectedDate)

                    val expected = selectedDate
                    val actual = calendarState.selectedDate
                    actual shouldBe expected
                }
            }
        }

        `when`("다음 달로 이동하였을 경우") {
            then("다음 달 달력을 보여준다.") {
                calendarState.onNextMonthClick()

                val expected = 5
                val actual = calendarState.currentMonthDate.monthValue
                actual shouldBe expected
            }
        }

        `when`("선택을 완료하였을 경우") {
            val appliedDates = listOf(
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2024, 12, 31),
            )

            appliedDates.forEach { date ->
                then("선택 완료한 날짜를 저장한다.") {
                    calendarState.onApplyDate(date)

                    val expected = date
                    val actual = calendarState.appliedDate
                    actual shouldBe expected
                }
            }
        }
    }
})
