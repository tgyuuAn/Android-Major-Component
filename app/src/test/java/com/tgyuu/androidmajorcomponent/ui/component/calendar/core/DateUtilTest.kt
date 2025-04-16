package com.tgyuu.androidmajorcomponent.ui.component.calendar.core

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import java.time.DayOfWeek
import java.time.LocalDate

class DateUtilTest : BehaviorSpec({
    given("특정 날짜가 주어졌을 때") {
        `when`("해당 날짜가 주말이 아니라면") {
            val givenDate = LocalDate.of(2025, 4, 16)
            val actual = givenDate.isWeekend()

            then("true를 반환한다.") {
                // 2025-04-16은 수요일
                val expected = false

                actual shouldBe expected
            }
        }

        `when`("해당 날짜가 주말이라면") {
            val givenDate = LocalDate.of(2025, 4, 19)
            val actual = givenDate.isWeekend()

            then("true를 반환한다.") {
                // 2025-04-19은 토요일
                val expected = true

                actual shouldBe expected
            }
        }
    }

    given("특정 요일이 주어졌을 때") {
        `when`("해당 요일이 주말이 아니라면") {
            val givenDayOfWeek = DayOfWeek.MONDAY
            val actual = givenDayOfWeek.isWeekend()

            then("true를 반환한다.") {
                val expected = false

                actual shouldBe expected
            }
        }


        `when`("해당 요일이 주말이라면") {
            val givenDayOfWeek = DayOfWeek.SATURDAY
            val actual = givenDayOfWeek.isWeekend()

            then("true를 반환한다.") {
                val expected = true

                actual shouldBe expected
            }
        }
    }

    given("두 날짜가 주어졌을 때") {
        val from = LocalDate.of(2025,4,1)
        val to = LocalDate.of(2025,5,1)

        `when`("두 날짜의 달의 차이를 구해서") {
            val actual = yearMonthDiff(from, to)

            then("반환한다.") {
                val expected = 1

                actual shouldBe expected
            }
        }
    }
})
