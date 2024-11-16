package com.example.mystore.features.homescreen.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mystore.R
import com.example.mystore.enums.TransactionType
import com.example.mystore.enums.Type
import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registertransaction.model.Transaction
import com.example.mystore.toShortDateString
import com.example.mystore.ui.components.commons.DividerComponent
import com.example.mystore.ui.components.commons.DropdownComponent
import com.example.mystore.ui.components.commons.RowComponent
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.components.commons.TextCurrencyComponent
import com.example.mystore.ui.components.commons.setIcon
import com.example.mystore.ui.model.DropdownAppearance
import com.example.mystore.ui.model.DropdownCallbacks
import com.example.mystore.ui.model.DropdownColors
import com.example.mystore.ui.model.DropdownComponentProps
import com.example.mystore.ui.model.DropdownItem
import com.example.mystore.ui.model.DropdownState
import com.example.mystore.ui.model.OutLinedTextFieldComponentProps
import com.example.mystore.ui.model.TextFieldAppearance
import com.example.mystore.ui.model.TextFieldCallbacks
import java.util.Date

@Composable
fun TransactionDetailsComponent(
    transaction: Transaction = Transaction(),
    shouldItemBeVisible: Boolean = true,
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
                    shouldItemBeVisible = shouldItemBeVisible,
                    onCloseAlertDialogTransactionDetail = onCloseAlertDialogTransactionDetail,
                )
            }
        },
    )
}

@Composable
private fun TransactionDetailsBody(
    transaction: Transaction,
    shouldItemBeVisible: Boolean,
    onCloseAlertDialogTransactionDetail: () -> Unit,
) {
    Column {
        // Dropdown Produto
        DropdownComponent(
            dropdownComponentProps = DropdownComponentProps(
                appearance = DropdownAppearance(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                        .fillMaxWidth(),
                    label = stringResource(R.string.my_store_product_2),
                    textFieldSize = Size.Zero,
                    colors = DropdownColors(
                        transactionDetailColors = Triple(
                            R.color.color_500,
                            R.color.color_500,
                            R.color.white
                        )
                    )
                ),
                state = DropdownState(
                    isExpanded = false,
                    selectedText = transaction.product.title
                ),
                items = DropdownItem(
                    items = listOf(transaction.product.title)
                ),
                callbacks = DropdownCallbacks()
            ),
            outLinedTextFieldComponentProps = OutLinedTextFieldComponentProps(
                appearance = TextFieldAppearance(
                    label = transaction.product.title,
                    transactionDetailColors = Triple(
                        R.color.color_500,
                        R.color.color_500,
                        R.color.white
                    ),
                    modifier = Modifier.padding(8.dp),
                    trailingIconTintColor = R.color.white
                ),
                focusManager = LocalFocusManager.current,
                callbacks = TextFieldCallbacks(
                    trailingIcon = {
                        Icon(
                            setIcon(false),
                            "contentDescription",
                            Modifier.clickable { },
                            tint = colorResource(id = R.color.color_50),
                        )
                    }
                )
            )
        )

        // Dropdwon Type
        DropdownComponent(
            dropdownComponentProps = DropdownComponentProps(
                appearance = DropdownAppearance(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                        .fillMaxWidth(),
                    label = stringResource(R.string.my_store_type),
                    textFieldSize = Size.Zero,
                    colors = DropdownColors(
                        transactionDetailColors = Triple(
                            R.color.color_500,
                            R.color.color_500,
                            R.color.white
                        )
                    ),
                ),
                state = DropdownState(
                    isExpanded = false,
                    selectedText = transaction.transactionType.toString()
                ),
                items = DropdownItem(
                    items = listOf(transaction.transactionType.toString())
                ),
                callbacks = DropdownCallbacks()
            ),
            outLinedTextFieldComponentProps = OutLinedTextFieldComponentProps(
                appearance = TextFieldAppearance(
                    label = transaction.transactionType.toString(),
                    transactionDetailColors = Triple(
                        R.color.color_500,
                        R.color.color_500,
                        R.color.white
                    ),
                    modifier = Modifier.padding(8.dp),
                    trailingIconTintColor = R.color.white
                ),
                focusManager = LocalFocusManager.current,
                callbacks = TextFieldCallbacks()
            )
        )

        // Date
        RowComponent(
            leftSideText = stringResource(id = R.string.my_store_date),
            transactionDetailColors = Pair(R.color.color_500, R.color.color_50),
            rightSide = {
                TextCurrencyComponent(
                    value = transaction.transactionDate,
                    shouldItemBeVisible = shouldItemBeVisible,
                    type = Type.DATE,
                    color = R.color.color_500,
                    paddings = Pair(0.dp, 0.dp),
                )
            },
        )

        DividerComponent()

        // Unit value
        RowComponent(
            leftSideText = stringResource(id = R.string.my_store_unit_value),
            transactionDetailColors = Pair(R.color.color_500, R.color.color_50),
            rightSide = {
                TextCurrencyComponent(
                    value = transaction.unitValue.toString(),
                    shouldItemBeVisible = shouldItemBeVisible,
                    type = Type.CURRENCY_TRANSACTION_DETAIL,
                    color = R.color.color_500,
                    paddings = Pair(0.dp, 0.dp),
                )
            },
        )

        DividerComponent()

        // Quantity
        RowComponent(
            leftSideText = stringResource(id = R.string.my_store_quantity),
            transactionDetailColors = Pair(R.color.color_500, R.color.color_50),
            rightSide = {
                TextCurrencyComponent(
                    value = transaction.quantity.toString(),
                    shouldItemBeVisible = shouldItemBeVisible,
                    type = Type.QUANTITY_TRANSACTION_DETAIL,
                    color = R.color.color_500,
                    paddings = Pair(0.dp, 0.dp),
                )
            },
        )

        DividerComponent()

        // Total Amount
        RowComponent(
            leftSideText = stringResource(id = R.string.my_store_total),
            transactionDetailColors = Pair(R.color.color_500, R.color.color_50),
            rightSide = {
                TextCurrencyComponent(
                    value = transaction.transactionAmount.toString(),
                    shouldItemBeVisible = shouldItemBeVisible,
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
