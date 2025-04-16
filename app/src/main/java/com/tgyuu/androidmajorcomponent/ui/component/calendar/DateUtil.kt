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

fun getNextMonthDatesToShow(date: LocalDate): List<LocalDate> {
    val totalDayCountUntilNextMonth =
        getPreviousMonthDatesToShow(date).size + getCurrentMonthDatesToShow(date).size
    val remainCount = 42 - totalDayCountUntilNextMonth

    val nextMonth = date.withDayOfMonth(1).plusMonths(1)
    return (1..remainCount).map { nextMonth.withDayOfMonth(it) }
}

fun getCurrentMonthDatesToShow(date: LocalDate): List<LocalDate> {
    val yearMonth = date.withDayOfMonth(1)
    val lastDay = yearMonth.lengthOfMonth()
    return (1..lastDay).map { day -> yearMonth.withDayOfMonth(day) }
}

fun getPreviousMonthDatesToShow(date: LocalDate): List<LocalDate> {
    val previousMonth = date.withDayOfMonth(1).minusMonths(1)
    val lastDayOfPreviousMonth = previousMonth.lengthOfMonth()

    val count = getPreviousMonthDayOfWeeksToShow(date).size

    return ((lastDayOfPreviousMonth - count + 1)..lastDayOfPreviousMonth).map {
        previousMonth.withDayOfMonth(it)
    }
}

/**
 * 해당 월의 달력을 6줄 7칸 기준으로 그릴 때,
 * 1일 전에 보여야 할 이전 달의 요일 목록을 반환합니다.
 */
private fun getPreviousMonthDayOfWeeksToShow(date: LocalDate): List<DayOfWeek> {
    val firstDayOfMonth = getFirstDayOfWeek(date)
    val count = if (firstDayOfMonth == DayOfWeek.SUNDAY) 7 else firstDayOfMonth.value - 1
    return (1..count).map { DayOfWeek.of(it) }
}

/**
 * 해당 날짜의 달에 1일의 요일을 구합니다.
 */
private fun getFirstDayOfWeek(date: LocalDate): DayOfWeek {
    return date.withDayOfMonth(1).dayOfWeek
}
