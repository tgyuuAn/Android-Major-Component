package com.tgyuu.androidmajorcomponent.ui.component.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tgyuu.androidmajorcomponent.ui.component.calendar.core.CalendarState
import com.tgyuu.androidmajorcomponent.ui.component.calendar.core.ui.CalendarBody
import com.tgyuu.androidmajorcomponent.ui.component.calendar.core.ui.CalendarController
import com.tgyuu.androidmajorcomponent.ui.component.calendar.core.ui.CalendarHeader
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
