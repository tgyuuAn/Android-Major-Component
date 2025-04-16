package com.tgyuu.androidmajorcomponent.ui.component.calendar.core.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import com.tgyuu.androidmajorcomponent.ui.component.calendar.core.isWeekend
import com.tgyuu.androidmajorcomponent.ui.component.calendar.core.toKorean
import java.time.DayOfWeek

@Composable
internal fun CalendarHeader(modifier: Modifier = Modifier) {
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
