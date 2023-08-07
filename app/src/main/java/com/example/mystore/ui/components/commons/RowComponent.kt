package com.example.mystore.ui.components.commons

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mystore.Type

@Composable
fun RowComponent(
    textCurrency: Double,
    shouldItemBeVisible: Boolean,
    stringId: Int,
    type: Type = Type.CURRENCY,
    modifier: Modifier = Modifier,
) {
    Row {
        TextFormattedComponent(stringId, type, modifier)
        TextCurrencyComponent(textCurrency, shouldItemBeVisible, type)
    }
}
