package com.tgyuu.androidmajorcomponent.ui.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.tgyuu.androidmajorcomponent.ui.component.calendar.core.CalendarState
import com.tgyuu.androidmajorcomponent.ui.component.calendar.NormalCalendar
import com.tgyuu.androidmajorcomponent.ui.component.calendar.core.toKorean
import com.tgyuu.androidmajorcomponent.ui.foundation.AndroidMajorComponentTheme
import org.junit.Rule
import org.junit.Test
import java.time.DayOfWeek
import java.time.LocalDate

class NormalCalendarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun laidOutNormalCalendar(calendarState: CalendarState) {
        composeTestRule.setContent {
            AndroidMajorComponentTheme {
                NormalCalendar(calendarState)
            }
        }
    }

    @Test
    fun 달력은_초기에_넣어준_날짜의_달을_보여준다() {
        //given
        val calendarState = CalendarState(LocalDate.of(2025, 4, 1))

        // when
        laidOutNormalCalendar(calendarState)

        // then
        composeTestRule.onNodeWithText("2025년 4월")
            .assertIsDisplayed()
    }

    @Test
    fun 달력은_컨트롤러_헤더_바디로_구성된다() {
        //given
        val calendarState = CalendarState(LocalDate.of(2025, 4, 1))

        // when
        laidOutNormalCalendar(calendarState)

        // then
        composeTestRule.onNodeWithContentDescription("달력 컨트롤러")
            .assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("달력 헤더")
            .assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("달력 바디")
            .assertIsDisplayed()
    }

    @Test
    fun 달력_헤더는_월화수목금토일_순서로_배치된다() {
        //given
        val calendarState = CalendarState(LocalDate.of(2025, 4, 1))

        // when
        laidOutNormalCalendar(calendarState)

        // then
        DayOfWeek.entries.map { it.toKorean() }.forEachIndexed { index, day ->
            composeTestRule
                .onNodeWithContentDescription("${day}_$index")
                .assertTextEquals(day)
        }
    }
}
