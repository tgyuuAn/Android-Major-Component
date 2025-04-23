package com.tgyuu.androidmajorcomponent.ui.component.colorpicker

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@Composable
fun ColorPicker(modifier: Modifier = Modifier) {
    Text(
        text = "HuePicker",
        modifier = Modifier.semantics { contentDescription = "HuePicker" }
    )

    Text(
        text = "SVPicker",
        modifier = Modifier.semantics { contentDescription = "SVPicker" }
    )
}
