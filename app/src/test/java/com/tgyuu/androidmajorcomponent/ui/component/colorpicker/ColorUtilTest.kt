package com.tgyuu.androidmajorcomponent.ui.component.colorpicker

import com.tgyuu.androidmajorcomponent.ui.component.colorpicker.core.HSV
import com.tgyuu.androidmajorcomponent.ui.component.colorpicker.core.RGB
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class ColorUtilTest : BehaviorSpec({
    given("HSV를") {
        val cases = listOf(
            HSV(h = 0f, s = 1f, v = 1f) to RGB(r = 255, g = 0, b = 0),  // 빨강
            HSV(h = 120f, s = 1f, v = 1f) to RGB(r = 0, g = 255, b = 0),  // 초록
            HSV(h = 240f, s = 1f, v = 1f) to RGB(r = 0, g = 0, b = 255),  // 파랑
        )

        cases.forEach { (hsv, expected) ->
            `when`("RGB로 변환하여") {
                val actual = hsv.toRGB()

                then("반환한다.") {
                    actual shouldBe expected
                }
            }
        }
    }
})
