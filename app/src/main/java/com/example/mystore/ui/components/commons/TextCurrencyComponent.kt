package com.example.mystore.ui.components.commons

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mystore.R
import com.example.mystore.enums.Type
import com.example.mystore.limitTo
import com.example.mystore.setTextColor

@Composable
internal fun TextCurrencyComponent(
    value: String,
    type: Type,
    shouldItemBeVisible: Boolean,
    fontSize: TextUnit = 18.sp,
    color: Int = R.color.color_800,
    paddings: Pair<Dp, Dp> = Pair(8.dp, 8.dp),
) {
    Text(
        modifier = Modifier.padding(start = paddings.first, end = paddings.second),
        fontSize = fontSize,
        fontWeight = MaterialTheme.typography.h5.fontWeight,
        color = colorResource(setColorBasedType(type, color, value)),
        text = setUnit(value, type, shouldItemBeVisible).limitTo(14),
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )
}

@Composable
private fun setColorBasedType(type: Type, color: Int, value: String) =
    when (type) {
        Type.CURRENCY_DEBIT_ONLY -> R.color.color_red_A1000
        Type.STRING -> color
        Type.CURRENCY_TRANSACTION_DETAIL -> R.color.color_900
        Type.QUANTITY_TRANSACTION_DETAIL -> R.color.color_900
        Type.DATE -> color
        Type.STRING_ONLY -> color
        else -> setTextColor(value.toDouble())
    }
