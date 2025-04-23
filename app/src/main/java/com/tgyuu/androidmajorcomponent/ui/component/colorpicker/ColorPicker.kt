package com.tgyuu.androidmajorcomponent.ui.component.colorpicker

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@Composable
fun ColorPicker(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        HuePicker()
        SVPicker()
    }
}

@Composable
private fun HuePicker(modifier: Modifier = Modifier) {
    Text(
        text = "HuePicker",
        modifier = modifier.semantics { contentDescription = "HuePicker" }
    )
}

@Composable
private fun SVPicker(modifier: Modifier = Modifier) {
    Text(
        text = "SVPicker",
        modifier = modifier.semantics { contentDescription = "SVPicker" }
    )
}
