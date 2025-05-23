package com.tgyuu.androidmajorcomponent.ui.component.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tgyuu.androidmajorcomponent.ui.component.calendar.core.CalendarState
import com.tgyuu.androidmajorcomponent.ui.component.calendar.core.ui.CalendarBody
import com.tgyuu.androidmajorcomponent.ui.component.calendar.core.ui.CalendarController
import com.tgyuu.androidmajorcomponent.ui.component.calendar.core.ui.CalendarHeader
import com.tgyuu.androidmajorcomponent.ui.component.calendar.core.yearMonthDiff
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun SwipeableCalendar(
    calendarState: CalendarState,
    modifier: Modifier = Modifier,
    onDateSelect: (LocalDate) -> Unit = {},
) {
    val initialPage = Int.MAX_VALUE / 2
    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { Int.MAX_VALUE },
    )
    val currentOffset = pagerState.currentPage - initialPage

    LaunchedEffect(pagerState.currentPage) {
        val newDate = calendarState.originSelectedDate.plusMonths(currentOffset.toLong())
        calendarState.currentDisplayDate = newDate
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        val scope = rememberCoroutineScope()

        CalendarController(
            currentDate = calendarState.currentDisplayDate,
            onPrevMonthClick = {
                scope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) }
            },
            onNextMonthClick = {
                scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
            },
        )
        CalendarHeader()

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
        ) { _ ->
            CalendarBody(
                currentDate = calendarState.currentDisplayDate,
                selectedDate = calendarState.selectedDate,
                onDateSelect = { selectedDate ->
                    val selectedOffset =
                        yearMonthDiff(calendarState.originSelectedDate, selectedDate)

                    if (selectedOffset != currentOffset) {
                        scope.launch {
                            pagerState.animateScrollToPage(initialPage + selectedOffset)
                            calendarState.onDateSelect(selectedDate)
                            onDateSelect(selectedDate)
                        }
                    } else {
                        calendarState.onDateSelect(selectedDate)
                        onDateSelect(selectedDate)
                    }
                },
            )
        }
    }
}
