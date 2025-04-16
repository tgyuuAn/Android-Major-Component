package com.tgyuu.androidmajorcomponent.ui.component

import com.tgyuu.androidmajorcomponent.ui.component.calendar.CalendarState
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class CalendarStateTest {

    private lateinit var calendarState: CalendarState

    @Before
    fun setUp() {
        calendarState = CalendarState(LocalDate.of(2025, 4, 1))
    }

    @Test
    fun `특정한_날짜를_선택했을_경우_선택한_날짜를_저장한다`() {
        // given
        val selectedDates = listOf(
            LocalDate.of(2025, 4, 1),
            LocalDate.of(2024, 12, 31),
        )

        for (selectedDate in selectedDates) {
            // when
            calendarState.onDateSelect(selectedDate)

            // then
            assertThat(calendarState.selectedDate).isEqualTo(selectedDate)
        }
    }

    @Test
    fun `다른_달_날짜를_선택했을_경우_선택한_달의_달력을_보여준다`() {
        // given
        val selectedDates = listOf(
            LocalDate.of(2025, 5, 1),
            LocalDate.of(2025, 3, 1)
        )

        for (selectedDate in selectedDates) {
            // when
            calendarState.onDateSelect(selectedDate)

            // then
            val actual = calendarState.currentDisplayDate
            val expected = selectedDate
            assertThat(actual).isEqualTo(expected)
        }
    }

    @Test
    fun `다음_달로_이동하였을_경우_다음_달_달력을_보여준다`() {
        // when
        calendarState.onNextMonthClick()

        // then
        val expected = 5
        val actual = calendarState.currentDisplayDate.monthValue
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `이전_달로_이동하였을_경우_이전_달_달력을_보여준다`() {
        // when
        calendarState.onPreviousMonthClick()

        // then
        val expected = 3
        val actual = calendarState.currentDisplayDate.monthValue
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `선택을_완료하였을_경우_선택한_날짜를_저장한다`() {
        // given
        val appliedDates = listOf(
            LocalDate.of(2025, 4, 1),
            LocalDate.of(2024, 12, 31),
        )

        for (date in appliedDates) {
            // when
            calendarState.onApplyDate(date)

            // then
            assertThat(calendarState.appliedDate).isEqualTo(date)
        }
    }
}
