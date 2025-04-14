package com.tgyuu.androidmajorcomponent.ui.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.tgyuu.androidmajorcomponent.ui.component.calendar.CalendarState
import com.tgyuu.androidmajorcomponent.ui.foundation.AndroidMajorComponentTheme
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate

class MatchingScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 달력은_초기에_넣어준_날짜의_달을_보여준다() {
        //given
        val calendarState = CalendarState(LocalDate.of(2025, 4, 1))

        // when
        composeTestRule.setContent {
            AndroidMajorComponentTheme {
                Calendar(calendarState)
            }
        }

        // then
        composeTestRule.onNodeWithText("2025년 4월")
            .assertIsDisplayed()
    }
}
