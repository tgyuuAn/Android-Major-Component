package com.tgyuu.androidmajorcomponent.ui.component.colorpicker

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class ColorUtilTest : BehaviorSpec({
    given("HSV를") {
        val hsv = HSV(h = 0, s = 1, v = 1)

        `when`("RGB로 변환하여") {
            val actual = hsv.toRGB()

            then("반환한다.") {
                // 2025-04-16은 수요일
                val expected = RGB(r = 255, g = 0, v = 0)

                actual shouldBe expected
            }
        }
    }
})
