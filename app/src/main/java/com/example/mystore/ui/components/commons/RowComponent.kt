package com.example.mystore.ui.components.commons

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.mystore.Type
import com.example.mystore.model.props.ColorProps

@Composable
fun RowComponent(
    leftContent: String,
    rightContent: @Composable () -> Unit,
    fontSize: TextUnit = 18.sp,
    fontColor: Int = ColorProps().rowComponentRightContentColor,
) {
    Row {
        TextFormattedComponent(
            leftContent = leftContent,
            fontSize = fontSize,
            fontColor = fontColor,
        )
        rightContent()
    }
}


@Preview
@Composable
fun Preview() {
    RowComponent(
        leftContent = "Left side text",
        rightContent = {
            TextCurrencyComponent(
                value = 0.0.toString(),
                currencyVisibility = true,
                type = Type.CURRENCY_DEBIT_ONLY,
            )
        }
    )
}
