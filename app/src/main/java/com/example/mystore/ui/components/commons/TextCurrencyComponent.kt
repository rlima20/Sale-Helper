package com.example.mystore.ui.components.commons

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mystore.R
import com.example.mystore.setTextColor
import com.example.mystore.toCurrency

@Composable
internal fun TextCurrencyComponent(
    value: Double,
    shouldItemBeVisible: Boolean,
) {
    Text(
        modifier = Modifier
            .padding(
                start = 8.dp,
                top = 8.dp,
                bottom = 8.dp,
                end = 8.dp,
            ),
        fontSize = 18.sp,
        fontWeight = MaterialTheme.typography.h5.fontWeight,
        color = colorResource(setTextColor(value)),
        text = value.toCurrency(shouldItemBeVisible),
    )
}
