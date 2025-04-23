package com.tgyuu.androidmajorcomponent.ui.component.colorpicker

import android.graphics.Color.HSVToColor
import com.tgyuu.androidmajorcomponent.ui.component.colorpicker.core.HSV
import com.tgyuu.androidmajorcomponent.ui.component.colorpicker.core.RGB

/**
 * @see <a href="https://www.rapidtables.org/ko/convert/color/hsv-to-rgb.html">
 * HSV to RGB 곧식 레퍼런스
 * </a>
 */

fun HSV.toRGB(): RGB {
    val c = v * s
    val x = c * (1 - kotlin.math.abs((h / 60f) % 2 - 1))
    val m = v - c
    val (rp, gp, bp) = when {
        h < 60f -> Triple(c, x, 0f)
        h < 120f -> Triple(x, c, 0f)
        h < 180f -> Triple(0f, c, x)
        h < 240f -> Triple(0f, x, c)
        h < 300f -> Triple(x, 0f, c)
        else -> Triple(c, 0f, x)
    }
    val r = ((rp + m) * 255).toInt().coerceIn(0, 255)
    val g = ((gp + m) * 255).toInt().coerceIn(0, 255)
    val b = ((bp + m) * 255).toInt().coerceIn(0, 255)
    return RGB(r, g, b)
}

/**
 * @see <a href="https://developer.android.com/reference/android/graphics/Color#HSVToColor(float[])">
 *     Android 곧식문서 HSVToColor
 *     </a>
 */
fun HSV.toAndroidRGB(): Int {
    return HSVToColor(floatArrayOf(h, s, v))
}
