package com.example.mystore.ui.components.commons

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mystore.Type

// todo - melhorar essa parte para receber uma string. primeira parte e depois segunda parte e
// deixar essa função mais reutilizável.
@Composable
fun RowComponent(
    leftSideText: String,
    rightSide: @Composable () -> Unit,
) {
    Row(modifier = Modifier.padding(8.dp)) {
        TextFormattedComponent(leftSideText)
        rightSide()
        // TextCurrencyComponent(textCurrency, shouldItemBeVisible, type)
    }
}
