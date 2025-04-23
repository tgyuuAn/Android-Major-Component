package com.tgyuu.androidmajorcomponent.ui.component.colorpicker

import com.tgyuu.androidmajorcomponent.ui.component.colorpicker.core.HSV
import com.tgyuu.androidmajorcomponent.ui.component.colorpicker.core.RGB
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class ColorUtilTest : BehaviorSpec({
    given("HSV를") {
        val hsv = HSV(h = 0, s = 1f, v = 1f)

        `when`("RGB로 변환하여") {
            val actual = hsv.toRGB()

            then("반환한다.") {
                val expected = RGB(r = 255, g = 0, b = 0)

                actual shouldBe expected
            }
        }
    }
})
