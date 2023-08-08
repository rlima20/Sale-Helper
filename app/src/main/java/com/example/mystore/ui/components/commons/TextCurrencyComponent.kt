package com.example.mystore.ui.components.commons

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mystore.Type
import com.example.mystore.setTextColor
import com.example.mystore.toCurrency
import com.example.mystore.toUnity

@Composable
internal fun TextCurrencyComponent(
    value: Double,
    shouldItemBeVisible: Boolean,
    type: Type,
) {
    Text(
        modifier = Modifier.padding(
            start = 8.dp,
            end = 8.dp,
        ),
        fontSize = 18.sp,
        fontWeight = MaterialTheme.typography.h5.fontWeight,
        color = colorResource(setTextColor(value)),
        text = setUnit(type, value, shouldItemBeVisible),
    )
}

@Composable
fun setUnit(
    type: Type,
    value: Double,
    shouldItemBeVisible: Boolean,
) = if (type == Type.CURRENCY) {
    value.toCurrency(shouldItemBeVisible)
} else {
    value.toInt().toUnity(shouldItemBeVisible)
}
