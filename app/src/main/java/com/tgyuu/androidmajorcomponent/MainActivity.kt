package com.tgyuu.androidmajorcomponent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.tgyuu.androidmajorcomponent.ui.component.calendar.CalendarState
import com.tgyuu.androidmajorcomponent.ui.component.calendar.NormalCalendar
import com.tgyuu.androidmajorcomponent.ui.foundation.AndroidMajorComponentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidMajorComponentTheme {
                val calendarState = remember { CalendarState() }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        NormalCalendar(calendarState)
                    }
                }
            }
        }
    }
}
