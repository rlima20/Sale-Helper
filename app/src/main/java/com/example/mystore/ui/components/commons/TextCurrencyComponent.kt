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
import com.example.mystore.Type
import com.example.mystore.interfaces.ColorBasedTypeImpl
import com.example.mystore.limitTo

@Composable
internal fun TextCurrencyComponent(
    value: String,
    type: Type,
    currencyVisibility: Boolean,
    fontSize: TextUnit = 18.sp,
    color: Int = R.color.color_800,
    paddings: Pair<Dp, Dp> = Pair(8.dp, 8.dp),
) {
    Text(
        modifier = Modifier.padding(start = paddings.first, end = paddings.second),
        fontSize = fontSize,
        fontWeight = MaterialTheme.typography.h5.fontWeight,
        color = colorResource(
            ColorBasedTypeImpl().setColorBasedType(
                type = type,
                color = color,
                value = value
            )
        ),
        text = setUnit(value, type, currencyVisibility).limitTo(14),
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )
}

fun setTextColor(value: Double): Int =
    if (value > 0) R.color.color_green_A900 else R.color.color_red_A1000

