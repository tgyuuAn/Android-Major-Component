package com.tgyuu.androidmajorcomponent.ui.component.calendar

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

/**
 * 해당 날짜의 달에 1일의 요일을 구합니다.
 */
private fun getFirstDayOfWeek(date: LocalDate): DayOfWeek {
    return date.withDayOfMonth(1).dayOfWeek
}

/**
 * 해당 월의 달력을 6줄 7칸 기준으로 그릴 때,
 * 1일 전에 보여야 할 이전 달의 요일 목록을 반환합니다.
 */
fun getPreviousMonthDaysToShow(date: LocalDate): List<DayOfWeek> {
    val firstDayOfMonth = date.withDayOfMonth(1).dayOfWeek
    val count = if (firstDayOfMonth == DayOfWeek.SUNDAY) {
        7
    } else {
        firstDayOfMonth.value - 1
    }

    return (1..count).map { DayOfWeek.of(it) }
}
