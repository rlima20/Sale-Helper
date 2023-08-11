package com.example.mystore.ui.components.commons

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mystore.R
import com.example.mystore.Type
import com.example.mystore.limitTo
import com.example.mystore.setTextColor
import com.example.mystore.toCurrency
import com.example.mystore.toUnity

@Composable
internal fun TextCurrencyComponent(
    value: String,
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
        color = colorResource(
            if (type == Type.STRING) {
                R.color.color_50
            } else {
                setTextColor(value.toDouble())
            },
        ),
        text = setUnit(type, value, shouldItemBeVisible).limitTo(14),
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )
}

@Composable
fun setUnit(
    type: Type,
    value: String,
    shouldItemBeVisible: Boolean,
) = when (type) {
    Type.CURRENCY -> value.toDouble().toCurrency(shouldItemBeVisible)
    Type.PURCHASE_CURRENCY -> value.toDouble().toCurrency(shouldItemBeVisible)
    Type.QUANTITY -> value.toInt().toUnity(shouldItemBeVisible)
    Type.STRING -> value
    Type.DATE -> value
}
