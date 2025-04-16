package com.tgyuu.androidmajorcomponent.ui.component.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import java.time.LocalDate

class CalendarState(val originSelectedDate: LocalDate = LocalDate.now()) {
    var currentDisplayDate by mutableStateOf(originSelectedDate)
    var selectedDate by mutableStateOf<LocalDate?>(null)

    fun onDateSelect(date: LocalDate) {
        selectedDate = date
        currentDisplayDate = date
    }

    fun onNextMonthClick() {
        currentDisplayDate = currentDisplayDate.plusMonths(1)
    }

    fun onPreviousMonthClick() {
        currentDisplayDate = currentDisplayDate.minusMonths(1)
    }

    companion object {
        val Saver: Saver<CalendarState, *> = listSaver(
            save = { listOf(it.originSelectedDate) },
            restore = {
                CalendarState(originSelectedDate = it[0])
            }
        )
    }
}

@Composable
fun rememberCalendarState(originSelectedDate: LocalDate = LocalDate.now()): CalendarState {
    return rememberSaveable(saver = CalendarState.Saver) {
        CalendarState(originSelectedDate)
    }
}
