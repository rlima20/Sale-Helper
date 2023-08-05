package com.example.mystore.ui.components.commons

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable

@Composable
fun RowComponent(
    textCurrency: Double,
    shouldItemBeVisible: Boolean,
    stringId: Int,
) {
    Row {
        TextFormattedComponent(stringId)
        TextCurrencyComponent(textCurrency, shouldItemBeVisible)
    }
}
