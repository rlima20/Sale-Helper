package com.example.mystore.ui.components.commons

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mystore.Type

@Composable
fun RowComponent(
    textCurrency: Double,
    shouldItemBeVisible: Boolean,
    stringId: Int,
    type: Type = Type.CURRENCY,
) {
    Row(modifier = Modifier.padding(8.dp)) {
        TextFormattedComponent(stringId)
        TextCurrencyComponent(textCurrency, shouldItemBeVisible, type)
    }
}
