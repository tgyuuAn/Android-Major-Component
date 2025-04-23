package com.tgyuu.androidmajorcomponent.ui.component.colorpicker

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
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
    fun ColorPicker는_Hue선택기와_SV선택기로_이루어진다() {
        // when
        laidOutColorPicker()

        // then
        composeTestRule.onNodeWithText("HuePicker")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("SVPicker")
            .assertIsDisplayed()
    }
}
