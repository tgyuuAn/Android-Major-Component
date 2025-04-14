package com.tgyuu.androidmajorcomponent.ui.component.calendar

import java.time.LocalDate

class CalendarState(val originSelectedDate: LocalDate = LocalDate.now()) {
    var selectedDate: LocalDate? = null
    var currentMonthDate: LocalDate = originSelectedDate

    fun onSelectDate(date: LocalDate) {
        selectedDate = date
    }

    fun onNextMonthClick() {
        currentMonthDate = currentMonthDate.plusMonths(1)
    }
}
