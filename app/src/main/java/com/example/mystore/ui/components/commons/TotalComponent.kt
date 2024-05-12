package com.example.mystore.ui.components.commons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mystore.R
import com.example.mystore.Type

@Composable
fun TotalComponent(
    salesValue: Double,
    purchasesValue: Double,
    isItemVisible: Boolean,
) {
    Column(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
        RowComponent(
            leftContent = stringResource(id = R.string.my_store_total_purchases),
            rightContent = {
                TextCurrencyComponent(
                    value = purchasesValue.toString(),
                    currencyVisibility = isItemVisible,
                    type = Type.CURRENCY_ONLY,
                )
            },
        )
        RowComponent(
            leftContent = stringResource(id = R.string.my_store_total_sales),
            rightContent = {
                TextCurrencyComponent(
                    value = salesValue.toString(),
                    currencyVisibility = isItemVisible,
                    type = Type.CURRENCY_ONLY,
                )
            },
        )
        RowComponent(
            leftContent = stringResource(id = R.string.my_store_total),
            rightContent = {
                TextCurrencyComponent(
                    value = (salesValue - purchasesValue).toString(),
                    currencyVisibility = isItemVisible,
                    type = Type.CURRENCY_PURCHASE,
                )
            },
        )
    }
}