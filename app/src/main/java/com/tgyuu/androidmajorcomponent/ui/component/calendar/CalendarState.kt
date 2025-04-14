package com.tgyuu.androidmajorcomponent.ui.component.calendar

import java.time.LocalDate

class CalendarState(val originSelectedDate: LocalDate = LocalDate.now()) {
    var selectedDate: LocalDate? = null

    fun onSelectDate(date: LocalDate) {
        selectedDate = date
    }
}
