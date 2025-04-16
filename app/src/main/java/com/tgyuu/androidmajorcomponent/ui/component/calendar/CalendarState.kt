package com.tgyuu.androidmajorcomponent.ui.component.calendar

import java.time.DayOfWeek
import java.time.LocalDate

class CalendarState(val originSelectedDate: LocalDate = LocalDate.now()) {
    var selectedDate: LocalDate? = null
    var currentMonthDate: LocalDate = originSelectedDate
    var appliedDate: LocalDate? = null

    fun onSelectDate(date: LocalDate) {
        selectedDate = date
    }

    fun onNextMonthClick() {
        currentMonthDate = currentMonthDate.plusMonths(1)
    }

    fun onPreviousMonthClick() {
        currentMonthDate = currentMonthDate.minusMonths(1)
    }

    fun onApplyDate(date: LocalDate) {
        appliedDate = date
    }
}
