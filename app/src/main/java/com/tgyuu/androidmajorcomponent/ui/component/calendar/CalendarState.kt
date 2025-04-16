package com.tgyuu.androidmajorcomponent.ui.component.calendar

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.time.LocalDate

class CalendarState(val originSelectedDate: LocalDate = LocalDate.now()) {
    var currentMonthDate by mutableStateOf(originSelectedDate)
    var selectedDate by mutableStateOf<LocalDate?>(null)
    var appliedDate by mutableStateOf<LocalDate?>(null)

    fun onDateSelect(date: LocalDate) {
        selectedDate = date
        currentMonthDate = date
        Log.d("test", "onDateSelect 호출 $selectedDate")
    }

    fun onNextMonthClick() {
        currentMonthDate = currentMonthDate.plusMonths(1)
        Log.d("test", "onNextMonthClick 호출 $currentMonthDate")
    }

    fun onPreviousMonthClick() {
        currentMonthDate = currentMonthDate.minusMonths(1)
        Log.d("test", "onPreviousMonthClick 호출 $currentMonthDate")
    }

    fun onApplyDate(date: LocalDate) {
        appliedDate = date
        Log.d("test", "onApplyDate 호출 $appliedDate")
    }
}
