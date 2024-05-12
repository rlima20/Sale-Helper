package com.example.mystore.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
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
import com.example.mystore.model.screen.Product
import com.example.mystore.model.screen.Transaction
import com.example.mystore.model.props.ColorProps
import com.example.mystore.model.props.DropdownComponentCallbackProps
import com.example.mystore.model.props.DropdownComponentVisualProps
import com.example.mystore.toShortDateString
import com.example.mystore.ui.components.commons.DividerComponent
import com.example.mystore.ui.components.commons.DropdownComponent
import com.example.mystore.ui.components.commons.RowComponent
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.components.commons.TextCurrencyComponent
import java.util.Date

@Composable
fun TransactionDetailsComponent(
    transaction: Transaction = Transaction(),
    currencyVisibility: Boolean = true,
    onCloseAlertDialogTransactionDetail: () -> Unit = {},
) {
    // Transaction details Section
    ScreenSectionComponent(
        title = stringResource(id = R.string.my_store_transactions_details),
        textColor = R.color.color_500,
        backgroundColor = R.color.white,
        body = {
            Column(modifier = Modifier.fillMaxSize()) {
                TransactionDetailsBody(
                    transaction = transaction,
                    currencyVisibility = currencyVisibility,
                    onCloseAlertDialogTransactionDetail = onCloseAlertDialogTransactionDetail,
                )
            }
        },
    )
}

@Composable
private fun TransactionDetailsBody(
    transaction: Transaction,
    currencyVisibility: Boolean,
    onCloseAlertDialogTransactionDetail: () -> Unit,
) {
    Column {
        // Dropdown Product
        DropdownComponent(
            DropdownComponentVisualProps(
                isExpanded = false,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                    .fillMaxWidth(),
                label = stringResource(R.string.my_store_product_2),
                items = listOf(transaction.product.title),
                textFieldSize = Size.Zero,
                selectedText = transaction.product.title,
                transactionDetailColors = Triple(
                    R.color.color_500,
                    R.color.color_500,
                    R.color.white,
                ),
            ),
            DropdownComponentCallbackProps()
        )

        // Dropdwon Type
        DropdownComponent(
            DropdownComponentVisualProps(
                isExpanded = false,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                    .fillMaxWidth(),
                label = stringResource(R.string.my_store_type),
                items = listOf(transaction.transactionType.toString()),
                textFieldSize = Size.Zero,
                selectedText = transaction.transactionType.toString(),
                transactionDetailColors = Triple(
                    R.color.color_500,
                    R.color.color_500,
                    R.color.white,
                ),
            ),
            DropdownComponentCallbackProps()
        )

        // Date
        RowComponent(
            leftContent = stringResource(id = R.string.my_store_date),
            rightContent = {
                TextCurrencyComponent(
                    value = Date().toShortDateString(),
                    currencyVisibility = currencyVisibility,
                    type = Type.DATE,
                    color = R.color.color_500,
                    paddings = Pair(0.dp, 0.dp),
                )
            },
        )

        DividerComponent()

        // Unit value
        RowComponent(
            leftContent = stringResource(id = R.string.my_store_unit_value),
            fontColor = ColorProps().unfocusedLabelColor,
            rightContent = {
                TextCurrencyComponent(
                    value = transaction.unitValue.toString(),
                    currencyVisibility = currencyVisibility,
                    type = Type.CURRENCY_TRANSACTION_DETAIL,
                    color = R.color.color_500,
                    paddings = Pair(0.dp, 0.dp),
                )
            },
        )

        DividerComponent()

        // Quantity
        RowComponent(
            leftContent = stringResource(id = R.string.my_store_quantity),
            fontColor = ColorProps().unfocusedLabelColor,
            rightContent = {
                TextCurrencyComponent(
                    value = transaction.quantity.toString(),
                    currencyVisibility = currencyVisibility,
                    type = Type.QUANTITY_TRANSACTION_DETAIL,
                    color = R.color.color_500,
                    paddings = Pair(0.dp, 0.dp),
                )
            },
        )

        DividerComponent()

        // Total Amount
        RowComponent(
            leftContent = stringResource(id = R.string.my_store_total),
            fontColor = ColorProps().unfocusedLabelColor,
            rightContent = {
                TextCurrencyComponent(
                    value = transaction.transactionAmount.toString(),
                    currencyVisibility = currencyVisibility,
                    type = Type.CURRENCY_TRANSACTION_DETAIL,
                    color = R.color.color_500,
                    paddings = Pair(0.dp, 0.dp),
                )
            },
        )

        DividerComponent()

        Button(
            modifier = Modifier.padding(start = 8.dp, end = 16.dp),
            onClick = {
                onCloseAlertDialogTransactionDetail()
            },
        ) {
            Text(
                text = stringResource(id = R.string.my_store_close),
                color = colorResource(id = R.color.color_50),
            )
        }
    }
}

@Preview
@Composable
fun TransactionDetailsComponentPreview() {
    val transaction = Transaction(
        product = Product(
            description = "Product description",
            purchasePrice = 10.0,
            salePrice = 20.0,
        ),
        transactionType = TransactionType.SALE,
        unitValue = 10.0,
        transactionDate = Date().toShortDateString(),
        quantity = 10,
        transactionAmount = 100.0,
    )
    TransactionDetailsComponent(transaction = transaction)
}
