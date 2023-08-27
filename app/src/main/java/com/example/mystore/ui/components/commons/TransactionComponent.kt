package com.example.mystore.ui.components.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mystore.R
import com.example.mystore.TransactionType
import com.example.mystore.Type
import com.example.mystore.colorTransactionType
import com.example.mystore.limitTo
import com.example.mystore.model.Transaction

@Composable
fun TransactionComponent(
    transaction: Transaction,
    shouldItemBeVisible: Boolean,
) {
    Surface(
        elevation = 4.dp,
        shape = RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 10.dp,
            bottomStart = 10.dp,
            bottomEnd = 10.dp,
        ),
        color = colorResource(id = R.color.color_800),
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .padding(4.dp)
                .background(color = colorResource(id = R.color.color_800)),
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) { TextTransactionType(transaction.transactionType) }
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
                TextCurrencyComponent(
                    value = transaction.transactionAmount.toString(),
                    shouldItemBeVisible = shouldItemBeVisible,
                    type = Type.CURRENCY,
                )
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
