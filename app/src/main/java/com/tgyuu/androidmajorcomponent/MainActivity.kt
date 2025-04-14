package com.tgyuu.androidmajorcomponent

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.tgyuu.androidmajorcomponent.ui.component.calendar.Calendar
import com.tgyuu.androidmajorcomponent.ui.foundation.AndroidMajorComponentTheme
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidMajorComponentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Calendar(
                            year = 2025,
                            month = 4,
                            startDate = LocalDate.now(),
                            onMonthChanged = {},
                            onDayClick = {},
                        )
                    }
                }
            }
        }
    }
}
