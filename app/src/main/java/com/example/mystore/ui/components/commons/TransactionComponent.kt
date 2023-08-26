package com.example.mystore.ui.components.commons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mystore.Type
import com.example.mystore.limitTo
import com.example.mystore.model.Transaction

@Composable
fun TransactionComponent(
    transaction: Transaction,
) {
    Box {
        Row(
            modifier = Modifier.padding(start = 8.dp),
        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable(onClick = {}),
                imageVector = Icons.Rounded.Home,
                contentDescription = null,
                tint = Color.White,
            )
        }
        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 8.dp)
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
                shouldItemBeVisible = true,
                type = Type.CURRENCY,
            )
        }
    }
}
