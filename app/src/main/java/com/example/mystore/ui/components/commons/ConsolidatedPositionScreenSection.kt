package com.example.mystore.ui.components.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mystore.R
import com.example.mystore.enums.TransactionType
import com.example.mystore.enums.Type
import com.example.mystore.features.registertransaction.model.Transaction
import com.example.mystore.ui.model.ColorProps

@Composable
fun ConsolidatedPositionScreenSection(
    title: String,
    transactions: List<Transaction>,
    shouldItemBeVisible: Boolean,
    openTransactionEditScreen: Boolean,
    colorProps: ColorProps,
    onEditTransaction: (transaction: Transaction) -> Unit
) {
    Column(
        modifier = Modifier.padding(top = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = 4.dp,
        ) {
            Box(modifier = Modifier.background(colorResource(colorProps.backgroundColor))) {
                ScreenSectionBody(
                    colorProps = colorProps,
                    title = title,
                    transactions = transactions,
                    shouldItemBeVisible = shouldItemBeVisible,
                    onEditTransaction = { onEditTransaction(it) }
                )
            }
        }
    }
}

@Composable
private fun ScreenSectionBody(
    colorProps: ColorProps,
    title: String,
    transactions: List<Transaction>,
    shouldItemBeVisible: Boolean,
    onEditTransaction: (transition: Transaction) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp, bottom = 8.dp, end = 8.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    top = 8.dp,
                    end = 8.dp,
                ),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = colorResource(colorProps.textColor),
            text = title,
        )
        ConsolidatedPositionBody(
            transactions = transactions,
            isItemVisible = shouldItemBeVisible,
            onEditTransaction = { onEditTransaction(it) }
        )
    }
}


@Composable
fun ConsolidatedPositionBody(
    transactions: List<Transaction>,
    isItemVisible: Boolean,
    onEditTransaction: (transition: Transaction) -> Unit
) {
    LazyRow(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(transactions) { transaction ->
            Surface(
                modifier = Modifier.padding(8.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = 2.dp
            ) {
                Column(
                    modifier = Modifier
                        .background(colorResource(id = R.color.white)),
                ) {
                    TransactionSection(
                        transaction,
                        isItemVisible,
                        onEditTransaction = { onEditTransaction(transaction) }
                    )
                }
            }
        }
    }
}

@Composable
private fun TransactionSection(
    transaction: Transaction,
    isItemVisible: Boolean,
    onEditTransaction: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TransactionSectionText(text = transaction.product.title)
        IconButton(
            onClick = { onEditTransaction() },
            modifier = Modifier,
            enabled = true,
        ) {
            Icon(
                modifier = Modifier.size(18.dp),
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                tint = colorResource(R.color.color_900)
            )
        }
    }
    RowComponent(
        leftSideText = stringResource(id = R.string.my_store_date),
        rightSide = {
            TextCurrencyComponent(
                value = transaction.transactionDate,
                shouldItemBeVisible = isItemVisible,
                type = Type.DATE,
                fontSize = 16.sp
            )
        },
        fontSize = 16.sp
    )
    RowComponent(
        leftSideText = stringResource(id = R.string.my_store_quantity),
        rightSide = {
            TextCurrencyComponent(
                value = transaction.quantity.toString(),
                shouldItemBeVisible = isItemVisible,
                type = Type.QUANTITY,
                fontSize = 16.sp
            )
        },
        fontSize = 16.sp
    )
    RowComponent(
        leftSideText = stringResource(id = R.string.my_store_unit_value),
        rightSide = {
            TextCurrencyComponent(
                value = transaction.unitValue.toString(),
                shouldItemBeVisible = isItemVisible,
                type = Type.CURRENCY_ONLY,
                fontSize = 16.sp
            )
        },
        fontSize = 16.sp
    )
    RowComponent(
        leftSideText = setLeftSideText(transaction),
        rightSide = {
            TextCurrencyComponent(
                value = transaction.transactionAmount.toString(),
                shouldItemBeVisible = isItemVisible,
                type = Type.CURRENCY_ONLY,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        },
        fontSize = 16.sp
    )
}

@Composable
internal fun TransactionSectionText(
    text: String,
    fontSize: TextUnit = 18.sp,
    paddings: Pair<Dp, Dp> = Pair(8.dp, 8.dp),
) {
    Text(
        modifier = Modifier.padding(start = paddings.first, end = paddings.second),
        fontSize = fontSize,
        fontWeight = FontWeight.ExtraBold,
        color = colorResource(R.color.color_500),
        text = text,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )
}

@Composable
private fun setLeftSideText(transaction: Transaction) =
    if (transaction.transactionType == TransactionType.PURCHASE) stringResource(id = R.string.my_store_purchase_value)
    else stringResource(id = R.string.my_store_sale_value)

