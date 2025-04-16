package com.tgyuu.androidmajorcomponent.ui.component.calendar.core

import java.time.DayOfWeek
import java.time.LocalDate

fun DayOfWeek.toKorean(): String = when (this) {
    DayOfWeek.MONDAY -> "월"
    DayOfWeek.TUESDAY -> "화"
    DayOfWeek.WEDNESDAY -> "수"
    DayOfWeek.THURSDAY -> "목"
    DayOfWeek.FRIDAY -> "금"
    DayOfWeek.SATURDAY -> "토"
    DayOfWeek.SUNDAY -> "일"
}

fun LocalDate.isWeekend(): Boolean = this.dayOfWeek.isWeekend()

fun DayOfWeek.isWeekend(): Boolean = when (this) {
    DayOfWeek.SATURDAY, DayOfWeek.SUNDAY -> true
    else -> false
}
