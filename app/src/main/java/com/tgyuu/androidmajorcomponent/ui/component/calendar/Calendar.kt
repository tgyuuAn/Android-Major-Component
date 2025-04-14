package com.tgyuu.androidmajorcomponent.ui.component.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import java.time.LocalDate

@Composable
fun NormalCalendar(
    calendarState: CalendarState,
) {
    Column {
        CalendarHeader(currentDate = calendarState.currentMonthDate)
    }
}

@Composable
private fun CalendarHeader(
    currentDate: LocalDate,
) {
    Text(
        text = "${currentDate.year}년 ${currentDate.monthValue}월"
    )
}
