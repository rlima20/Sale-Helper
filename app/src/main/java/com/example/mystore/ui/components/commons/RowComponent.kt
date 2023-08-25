package com.example.mystore.ui.components.commons

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RowComponent(
    leftSideText: String,
    fontSize: TextUnit = 18.sp,
    rightSide: @Composable () -> Unit,
) {
    Row(modifier = Modifier.padding(start = 8.dp)) {
        TextFormattedComponent(leftSideText, fontSize)
        rightSide()
    }
}
