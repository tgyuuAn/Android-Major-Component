package com.tgyuu.androidmajorcomponent.ui.component.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.TextStyle.FULL
import java.util.Locale

@Composable
fun Calendar(
    year: Int,
    month: Int,
    startDate: LocalDate,
    onMonthChanged: (Int) -> Unit,
    onDayClick: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    selectedDate: LocalDate? = null,
) {
    var currentMonth by remember { mutableIntStateOf(month) }
    var currentYear by remember { mutableIntStateOf(year) }
    val startMonth = startDate.monthValue

    Column(
        verticalArrangement = Arrangement.spacedBy(26.dp),
        modifier = modifier,
    ) {
        CalendarHeader(
            year = currentYear,
            month = currentMonth,
            startMonth = startMonth,
            onMonthChanged = { newMonth ->
                if (newMonth < 1) {
                    currentMonth = 12
                    currentYear--
                } else if (newMonth > 12) {
                    currentMonth = 1
                    currentYear++
                } else {
                    currentMonth = newMonth
                }
            },
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(26.dp),
        ) {
            DayOfWeekLabel()

            CalendarBody(
                year = currentYear,
                month = currentMonth,
                selectedDate = selectedDate,
                startDate = startDate,
                onDayClick = {
                    onDayClick(it)
                    onMonthChanged(currentMonth)
                },
            )
        }
    }
}

@Composable
private fun CalendarHeader(
    year: Int,
    month: Int,
    startMonth: Int,
    onMonthChanged: (Int) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp),
    ) {
        Text(
            text = "${year}년 ${month}월",
            color = Color.Black,
        )
    }
}

@Composable
private fun DayOfWeekLabel() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterHorizontally,
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        DayOfWeek.entries.forEach { dayOfWeek ->
            Text(
                text = dayOfWeek.displayName,
                textAlign = TextAlign.Center,
                color = Color.LightGray,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun CalendarBody(
    year: Int,
    month: Int,
    selectedDate: LocalDate?,
    startDate: LocalDate,
    onDayClick: (LocalDate) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        horizontalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterHorizontally,
        ),
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        val calendarDate = LocalDate.of(year, month, 1)
        val visibleDaysCount = countVisibleDaysFromLastMonth(calendarDate)
        val beforeMonthDaysToShow = generateBeforeMonthDaysToShow(
            visibleDaysCount = visibleDaysCount,
            currentDate = calendarDate,
        )
        items(beforeMonthDaysToShow) { day ->
            CalendarDayText(
                day = day,
                color = Color.LightGray,
                isSelected = (selectedDate?.monthValue == calendarDate.monthValue.minus(1)) &&
                        (selectedDate.dayOfMonth == day)
            )
        }

        val thisMonthLastDate = calendarDate.lengthOfMonth()
        val thisMonthDaysToShow: List<Int> = (1..thisMonthLastDate).toList()
        items(thisMonthDaysToShow) { day ->
            val currentDate = calendarDate.withDayOfMonth(day)
            val isBeforeStartDate = currentDate.isBefore(startDate)

            CalendarDayText(
                day = day,
                color = if (isBeforeStartDate) Color.LightGray else Color.DarkGray,
                isSelected = (selectedDate?.monthValue == calendarDate.monthValue) &&
                        (selectedDate.dayOfMonth == day),
                onClick = {
                    if (!isBeforeStartDate) {
                        onDayClick(currentDate)
                    }
                }
            )
        }

        val remainingDays = 42 - (visibleDaysCount + thisMonthDaysToShow.size)
        val nextMonthDaysToShow = IntRange(1, remainingDays).toList()
        items(nextMonthDaysToShow) { day ->
            CalendarDayText(
                day = day,
                color = Color.LightGray,
                isSelected = (selectedDate?.monthValue == calendarDate.monthValue.plus(1)) &&
                        (selectedDate.dayOfMonth == day)
            )
        }
    }
}

@Composable
private fun CalendarDayText(
    day: Int,
    color: Color,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(32.dp)
            .clickable { onClick() }
    ) {
        if (isSelected) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            )
        }

        Text(
            text = day.toString(),
            color = if (isSelected) Color.White else color,
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun countVisibleDaysFromLastMonth(currentDate: LocalDate): Int {
    val firstDayOfWeek = currentDate.withDayOfMonth(1).dayOfWeek
    val firstDayDisplayName = firstDayOfWeek.getDisplayName(FULL, Locale.US).uppercase()
    var count = 0
    for (day in DayOfWeek.entries) {
        if (day.name == firstDayDisplayName) break
        count += 1
    }
    return count
}

@RequiresApi(Build.VERSION_CODES.O)
private fun generateBeforeMonthDaysToShow(
    visibleDaysCount: Int,
    currentDate: LocalDate,
): List<Int> {
    val beforeMonth = currentDate.minusMonths(1)
    val beforeMonthLastDay = beforeMonth.lengthOfMonth()
    return IntRange(beforeMonthLastDay - visibleDaysCount + 1, beforeMonthLastDay).toList()
}

enum class DayOfWeek(val displayName: String) {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일"),
}
