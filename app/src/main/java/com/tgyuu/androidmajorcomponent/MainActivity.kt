package com.tgyuu.androidmajorcomponent

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tgyuu.androidmajorcomponent.ui.component.calendar.NormalCalendar
import com.tgyuu.androidmajorcomponent.ui.component.calendar.SwipeableCalendar
import com.tgyuu.androidmajorcomponent.ui.component.calendar.core.rememberCalendarState
import com.tgyuu.androidmajorcomponent.ui.foundation.AndroidMajorComponentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidMajorComponentTheme {
                val normalCalendarState = rememberCalendarState()
                val swipeableCalendarState = rememberCalendarState()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(30.dp),
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        NormalCalendar(
                            calendarState = normalCalendarState,
                            onDateSelect = { Log.d("NormalCalendar", "selectedDate : $it") },
                        )

                        SwipeableCalendar(
                            calendarState = swipeableCalendarState,
                            onDateSelect = { Log.d("SwipeableCalendar", "selectedDate : $it") },
                        )
                    }
                }
            }
        }
    }
}
