package com.example.mystore.ui.components.commons

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.mystore.R

@Composable
fun RowComponent(
    leftSideText: String,
    fontSize: TextUnit = 18.sp,
    rightSide: @Composable () -> Unit,
    transactionDetailColors: Pair<Int, Int> = Pair(
        first = R.color.color_50,
        second = R.color.color_900,
    ),
) {
    Row {
        TextFormattedComponent(
            leftSideText = leftSideText,
            fontSize = fontSize,
            color = transactionDetailColors.first,
        )
        rightSide()
    }
}
