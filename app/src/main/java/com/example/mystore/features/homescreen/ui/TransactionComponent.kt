package com.example.mystore.features.homescreen.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mystore.R
import com.example.mystore.colorTransactionType
import com.example.mystore.enums.TransactionType
import com.example.mystore.enums.Type
import com.example.mystore.features.registertransaction.model.Transaction
import com.example.mystore.limitTo
import com.example.mystore.ui.components.commons.TextCurrencyComponent
import com.example.mystore.ui.components.commons.TextFormattedComponent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TransactionComponent(
    transaction: Transaction,
    shouldItemBeVisible: Boolean,
    onClick: (Transaction) -> Unit = {},
    onLongClick: (Transaction) -> Unit = {},
    onEditTransactionIconClick: (Transaction) -> Unit
) {
    Surface(
        elevation = 4.dp,
        shape = RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 10.dp,
            bottomStart = 10.dp,
            bottomEnd = 10.dp,
        ),
        color = colorResource(id = R.color.color_50),
    ) {
        Column(
            modifier = Modifier.combinedClickable(
                enabled = true,
                onClick = { onClick(transaction) },
                onLongClick = { onLongClick(transaction) },
            ),
        ) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .padding(4.dp)
                    .background(color = colorResource(id = R.color.color_50)),
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TextTransactionType(transaction.transactionType)
                }
                Row(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TextFormattedComponent(
                        modifier = Modifier.padding(start = 24.dp),
                        leftSideText = transaction.product.title.limitTo(20),
                        fontSize = 16.sp,
                    )
                    IconButton(
                        onClick = { onEditTransactionIconClick(transaction) },
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
                    TextCurrencyComponent(
                        value = transaction.transactionAmount.toString(),
                        shouldItemBeVisible = shouldItemBeVisible,
                        type = Type.STRING,
                    )
                }
            }
        }
    }
}

@Composable
private fun TextTransactionType(transactionType: TransactionType) {
    Text(
        text = setText(transactionType),
        color = transactionType.colorTransactionType(),
        fontSize = 24.sp,
        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
    )
}

@Composable
private fun setText(transactionType: TransactionType) =
    if (transactionType == TransactionType.SALE) "+" else "-"
