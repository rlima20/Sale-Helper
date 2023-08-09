package com.example.mystore.ui.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mystore.R
import com.example.mystore.Type
import com.example.mystore.ui.components.commons.RowComponent
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.components.commons.TextCurrencyComponent
import com.example.mystore.viewmodel.ConsolidatedPosViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ConsolidatedPositionScreen(
    consolidatedPosViewModel: ConsolidatedPosViewModel,
    shouldItemBeVisible: Boolean,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {},
) {
    Column {
        ScreenSectionComponent(
            title = "Vendas",
            body = {
                ConsolidatedPosBody(
                    consolidatedPosViewModel.getTransactions(),
                    shouldItemBeVisible,
                )
            },
        )
    }
}

@Composable
fun ConsolidatedPosBody(
    transactions: List<ConsolidatedPosViewModel.Transaction>,
    shouldItemBeVisible: Boolean,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(transactions) { transaction ->
            Column(
                modifier = Modifier
                    .background(colorResource(id = R.color.color_600)),
            ) {
                RowComponent(
                    leftSideText = stringResource(id = R.string.my_store_product),
                    rightSide = {
                        TextCurrencyComponent(
                            value = transaction.product.title,
                            shouldItemBeVisible = shouldItemBeVisible,
                            type = Type.STRING,
                        )
                    },
                )
                RowComponent(
                    leftSideText = stringResource(id = R.string.my_store_date),
                    rightSide = {
                        TextCurrencyComponent(
                            value = transaction.transactionDate.toShortString(),
                            shouldItemBeVisible = shouldItemBeVisible,
                            type = Type.STRING,
                        )
                    },
                )
                RowComponent(
                    leftSideText = stringResource(id = R.string.my_store_quantity),
                    rightSide = {
                        TextCurrencyComponent(
                            value = transaction.quantity.toString(),
                            shouldItemBeVisible = shouldItemBeVisible,
                            type = Type.QUANTITY,
                        )
                    },
                )
                RowComponent(
                    leftSideText = stringResource(id = R.string.my_store_unit_value),
                    rightSide = {
                        TextCurrencyComponent(
                            value = transaction.unitValue.toString(),
                            shouldItemBeVisible = shouldItemBeVisible,
                            type = Type.CURRENCY,
                        )
                    },
                )
                RowComponent(
                    leftSideText = stringResource(id = R.string.my_store_gross_revenue),
                    rightSide = {
                        TextCurrencyComponent(
                            value = transaction.transactionAmount.toString(),
                            shouldItemBeVisible = shouldItemBeVisible,
                            type = Type.CURRENCY,
                        )
                    },
                )
            }
        }
    }
}

fun Date.toShortString(): String {
    val formatter = SimpleDateFormat("EEE MMM dd", Locale.getDefault())
    return formatter.format(this)
}

// A fun that transforms a Date into a String
// The return must be like this Tue Aug 08
// fun Date.toShortString(): String {
//     val formatter = SimpleDateFormat("EEE MMM dd", Locale.getDefault())
//     return formatter.format(this)
// }

/* RowComponent(
   textCurrency = resume.debits,
    shouldItemBeVisible = shouldItemBeVisible,
    stringId = R.string.my_store_debits,
    type = Type.CURRENCY,
)

RowComponent(
    textCurrency = resume.grossRevenue,
    shouldItemBeVisible = shouldItemBeVisible,
    stringId = R.string.my_store_gross_revenue,
    type = Type.CURRENCY,
)

RowComponent(
    textCurrency = resume.netRevenue,
    shouldItemBeVisible = shouldItemBeVisible,
    stringId = R.string.my_store_net_revenue,
    type = Type.CURRENCY,
)

RowComponent(
    textCurrency = resume.stockValue,
    shouldItemBeVisible = shouldItemBeVisible,
    stringId = R.string.my_store_stock_value,
    type = Type.CURRENCY,
)*/
