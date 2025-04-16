package com.tgyuu.androidmajorcomponent.ui.component.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
fun NormalCalendar(
    calendarState: CalendarState,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        CalendarController(currentDate = calendarState.currentMonthDate)
        CalendarHeader()
        CalendarBody(currentDate = calendarState.currentMonthDate)
    }
}

@Composable
private fun CalendarController(
    currentDate: LocalDate,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "${currentDate.year}년 ${currentDate.monthValue}월",
        textAlign = TextAlign.Center,
        color = Color.DarkGray,
        modifier = modifier
            .fillMaxWidth()
            .semantics { contentDescription = "달력 컨트롤러" },
    )
}

@Composable
private fun CalendarHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .semantics { contentDescription = "달력 헤더" },
    ) {
        DayOfWeek.entries.forEachIndexed { idx, weekday ->
            val weekDayText = weekday.toKorean()

            Text(
                text = weekDayText,
                textAlign = TextAlign.Center,
                color = Color.DarkGray,
                modifier = Modifier
                    .weight(1f)
                    .semantics { contentDescription = "${weekDayText}_${idx}" },
            )
        }
    }
}

@Composable
private fun CalendarBody(
    currentDate: LocalDate,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = modifier.semantics { contentDescription = "달력 바디" },
    ) {
        items(items = getCalendarDates(currentDate)) {
            Text(
                text = it.dayOfMonth.toString(),
                textAlign = TextAlign.Center,
                color = if (it.isCurrentMonth) Color.DarkGray else Color.LightGray,
            )
        }
    }
}
