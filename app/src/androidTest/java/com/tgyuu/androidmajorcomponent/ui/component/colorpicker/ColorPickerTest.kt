package com.tgyuu.androidmajorcomponent.ui.component.colorpicker

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.tgyuu.androidmajorcomponent.ui.foundation.AndroidMajorComponentTheme
import org.junit.Rule
import org.junit.Test

class NormalCalendarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun laidOutColorPicker() {
        composeTestRule.setContent {
            AndroidMajorComponentTheme {
                ColorPicker()
            }
        }
    }

    @Test
    fun ColorPicker는_HuePicker와_SVPicker로_이루어진다() {
        // when
        laidOutColorPicker()

        // then
        composeTestRule.onNodeWithContentDescription("HuePicker")
            .assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("SVPicker")
            .assertIsDisplayed()
    }
}
