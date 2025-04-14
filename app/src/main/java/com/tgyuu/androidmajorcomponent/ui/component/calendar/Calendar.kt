package com.tgyuu.androidmajorcomponent.ui.component.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun NormalCalendar(
    calendarState: CalendarState,
) {
    Column {
        Text(
            text = "${calendarState.originSelectedDate.year}년 ${calendarState.originSelectedDate.monthValue}월"
        )
    }
}
