package com.tgyuu.androidmajorcomponent.ui.component.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@Composable
fun NormalCalendar(
    calendarState: CalendarState,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        CalendarHeader(currentDate = calendarState.currentMonthDate)
        CalendarBody()
    }
}

@Composable
private fun CalendarHeader(
    currentDate: LocalDate,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "${currentDate.year}년 ${currentDate.monthValue}월",
        modifier = modifier.semantics { contentDescription = "달력 헤더" },
    )
}

@Composable
private fun CalendarBody(
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = modifier.semantics { contentDescription = "달력 바디" },
    ) {
        item {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp),
            )
        }
    }
}
