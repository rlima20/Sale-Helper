package com.example.mystore.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mystore.R
import com.example.mystore.TransactionType
import com.example.mystore.Type
import com.example.mystore.model.Product
import com.example.mystore.model.Transaction
import com.example.mystore.ui.components.commons.DropdownComponent
import com.example.mystore.ui.components.commons.RowComponent
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.components.commons.TextCurrencyComponent
import java.util.Date

val transaction = Transaction(
    product = Product(
        description = "Product description",
    ),
    transactionType = TransactionType.SALE,
    unitValue = 0.0,
    transactionDate = Date(),
    quantity = 0,
    transactionAmount = 0.0,
)

@Composable
fun TransactionDetailsComponent(
    transaction: Transaction,
    shouldItemBeVisible: Boolean = true,
) {
    ScreenSectionComponent(
        title = stringResource(id = R.string.my_store_transactions_details),
        body = {
            Column(modifier = Modifier.fillMaxSize()) {
                Body(
                    transaction = transaction,
                    shouldItemBeVisible = shouldItemBeVisible,
                )
            }
        },
    )
}

/*val product: Product = Product(),
val transactionType: TransactionType = TransactionType.SALE,
val unitValue: Double = 0.0,
val transactionDate: Date = Date(),
val quantity: Int = 0,
val transactionAmount: Double = 0.0,*/

@Composable
fun Body(
    transaction: Transaction,
    shouldItemBeVisible: Boolean,
) {
    Column {
        // Dropdown Produto
        DropdownComponent(
            isExpanded = false,
            modifier = Modifier.fillMaxWidth(),
            label = "Produce",
            items = listOf(transaction.product.title),
            textFieldSize = Size.Zero,
            selectedText = transaction.product.title,
        )

        // Dropdwon Type
        DropdownComponent(
            isExpanded = false,
            modifier = Modifier.fillMaxWidth(),
            label = "Produce",
            items = listOf(transaction.product.title),
            textFieldSize = Size.Zero,
            selectedText = transaction.product.title,
        )

        // Date
        RowComponent(
            leftSideText = "ABC",
            rightSide = {
                TextCurrencyComponent(
                    value = 10.toString(),
                    shouldItemBeVisible = shouldItemBeVisible,
                    type = Type.DATE,
                )
            },
        )

        Divider(
            color = colorResource(id = R.color.color_300),
            thickness = 1.dp,
            modifier = Modifier.padding(8.dp),
        )
    }
}

@Preview
@Composable
fun TransactionDetailsComponentPreview() {
    TransactionDetailsComponent(transaction = transaction)
}
