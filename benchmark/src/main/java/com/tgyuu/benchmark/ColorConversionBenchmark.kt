package com.tgyuu.benchmark

import org.junit.Test
import kotlin.math.abs
import kotlin.random.Random
import kotlin.system.measureNanoTime
import android.graphics.Color as AndroidColor

class ColorConversionBenchmark {

    private val testCases = List(1_000_000) {
        val hue = Random.nextFloat() * 360f
        val sat = Random.nextFloat()
        val value = Random.nextFloat()
        floatArrayOf(hue, sat, value)
    }

    private fun hsvToRgb(h: Float, s: Float, v: Float): Triple<Int, Int, Int> {
        val c = v * s
        val x = c * (1 - abs((h / 60f) % 2 - 1))
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
        return Triple(r, g, b)
    }

    @Test
    fun benchmarkAndroidHSVToColor() {
        val time = measureNanoTime {
            for (hsv in testCases) {
                AndroidColor.HSVToColor(hsv)
            }
        }
        println("AndroidColor.HSVToColor(): ${time / 1_000_000} ms")
    }

    @Test
    fun benchmarkPureKotlinHsvToRgb() {
        val time = measureNanoTime {
            for ((h, s, v) in testCases) {
                hsvToRgb(h, s, v)
            }
        }
        println("pure hsvToRgb(): ${time / 1_000_000} ms")
    }
}
