package com.tgyuu.androidmajorcomponent.ui.component.calendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
fun NormalCalendar(
    calendarState: CalendarState,
    modifier: Modifier = Modifier,
    onDateSelect: (LocalDate) -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        CalendarController(
            currentDate = calendarState.currentDisplayDate,
            onPrevMonthClick = calendarState::onPreviousMonthClick,
            onNextMonthClick = calendarState::onNextMonthClick,
        )
        CalendarHeader()
        CalendarBody(
            currentDate = calendarState.currentDisplayDate,
            selectedDate = calendarState.selectedDate,
            onDateSelect = { selectedDate ->
                calendarState.onDateSelect(selectedDate)
                onDateSelect(selectedDate)
            },
        )
    }
}

@Composable
private fun CalendarController(
    currentDate: LocalDate,
    onPrevMonthClick: () -> Unit,
    onNextMonthClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .semantics { contentDescription = "달력 컨트롤러" },
    ) {
        IconButton(onClick = onPrevMonthClick) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "이전 달"
            )
        }

        Text(
            text = "${currentDate.year}년 ${currentDate.monthValue}월",
            textAlign = TextAlign.Center,
            color = Color.DarkGray,
        )

        IconButton(onClick = onNextMonthClick) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "다음 달"
            )
        }
    }
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
                color = if (weekday.isWeekend()) Color.Red else Color.DarkGray,
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
    selectedDate: LocalDate?,
    onDateSelect: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.semantics { contentDescription = "달력 바디" },
    ) {
        items(items = getCalendarDates(currentDate)) {
            CalendarDayItem(
                calendarDate = it,
                selectedDate = selectedDate,
                onDateSelect = onDateSelect,
            )
        }
    }
}

@Composable
private fun CalendarDayItem(
    calendarDate: CalendarDate,
    selectedDate: LocalDate?,
    onDateSelect: (LocalDate) -> Unit,
) {
    val textColor = when {
        !calendarDate.isCurrentMonth -> Color.LightGray
        calendarDate.date == selectedDate -> Color.White
        calendarDate.dayOfWeek.isWeekend() -> Color.Red
        else -> Color.DarkGray
    }

    Surface(
        shape = RoundedCornerShape(8.dp),
        color = if (calendarDate.date == selectedDate) Color.DarkGray else Color.Transparent,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clickable { onDateSelect(calendarDate.date) },
    ) {
        Text(
            text = calendarDate.dayOfMonth.toString(),
            textAlign = TextAlign.Center,
            color = textColor,
        )
    }
}
