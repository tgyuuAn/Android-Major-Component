package com.tgyuu.androidmajorcomponent.ui.component.calendar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.time.LocalDate

class CalendarState(
    val originSelectedDate: LocalDate = LocalDate.now(),
    val onDateSelectCallBack: (LocalDate) -> Unit = {},
) {
    var currentDisplayDate by mutableStateOf(originSelectedDate)
    var selectedDate by mutableStateOf<LocalDate?>(null)

    fun onDateSelect(date: LocalDate) {
        selectedDate = date
        currentDisplayDate = date
        onDateSelectCallBack(date)
    }

    fun onNextMonthClick() {
        currentDisplayDate = currentDisplayDate.plusMonths(1)
    }

    fun onPreviousMonthClick() {
        currentDisplayDate = currentDisplayDate.minusMonths(1)
    }
}
