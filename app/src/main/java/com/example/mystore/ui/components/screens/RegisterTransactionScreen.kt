package com.example.mystore.ui.components.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mystore.R
import com.example.mystore.Screens
import com.example.mystore.TransactionType
import com.example.mystore.Type
import com.example.mystore.limitTo
import com.example.mystore.model.Product
import com.example.mystore.model.Transaction
import com.example.mystore.toTransactionType
import com.example.mystore.ui.components.commons.DropdownComponent
import com.example.mystore.ui.components.commons.FloatingActionButton
import com.example.mystore.ui.components.commons.Quantifier
import com.example.mystore.ui.components.commons.RowComponent
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.components.commons.SectionInfo
import com.example.mystore.ui.components.commons.TextCurrencyComponent
import com.example.mystore.ui.components.commons.ValidateSection
import com.example.mystore.viewmodel.screen.RegisterTransactionViewModel
import java.util.Calendar

// todo - Incluir lógica de estoque (No quantifier tbm... Ele não pode deixar eu ultrapassar a
//  quantidade de estoque disponível para aquele determinado produto.)

@Composable
fun RegisterTransactionScreen(
    registerTransactionViewModel: RegisterTransactionViewModel,
    shouldItemBeVisible: Boolean,
) {
    registerTransactionViewModel.setScreenWidth(LocalConfiguration.current.screenWidthDp)

    val listOfProducts by registerTransactionViewModel.listOfProducts.collectAsState()
    val listOfTransactionTypes by registerTransactionViewModel.listOfTransactionType.collectAsState()
    val screenWidth by registerTransactionViewModel.screenWidth.collectAsState()
    val quantity by registerTransactionViewModel.quantity.collectAsState()
    val total by registerTransactionViewModel.totalValue.collectAsState()

    Column {
        ValidateSection(
            screen = Screens.REGISTER_TRANSACTION,
            sectionInfo = SectionInfo(
                section = {
                    ScreenSectionComponent(
                        title = stringResource(id = R.string.my_store_register_transaction),
                        body = {
                            RegisterTransactionBody(
                                listOfTransactionTypes = listOfTransactionTypes,
                                listOfProducts = listOfProducts,
                                quantity = quantity,
                                total = total,
                                screenWidth = screenWidth,
                                shouldItemBeVisible = shouldItemBeVisible,
                                registerTransactionViewModel = registerTransactionViewModel,
                            )
                        },
                    )
                },
            ),
        )
    }
}

@Composable
private fun RegisterTransactionBody(
    shouldItemBeVisible: Boolean = true,
    screenWidth: Int,
    listOfTransactionTypes: List<TransactionType>,
    total: Double,
    quantity: Int,
    registerTransactionViewModel: RegisterTransactionViewModel,
    listOfProducts: List<Product>,
) {
    Column {
        var selectedTextTransaction: String by remember { mutableStateOf("") }
        var isExpandedTransaction: Boolean by remember { mutableStateOf(false) }
        var textFieldSizeTransaction: Size by remember { mutableStateOf(Size.Zero) }

        var selectedTextProduct: String by remember { mutableStateOf("") }
        var isExpandedProduct: Boolean by remember { mutableStateOf(false) }
        var textFieldSizeProduct: Size by remember { mutableStateOf(Size.Zero) }

        Row(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
        ) {
            DropdownComponent(
                isExpanded = isExpandedTransaction,
                items = listOfTransactionTypes.map { it.name },
                selectedText = selectedTextTransaction,
                textFieldSize = textFieldSizeTransaction,
                label = stringResource(id = R.string.my_store_transaction_type),
                modifier = Modifier.fillMaxWidth(),
                onOutLinedTextFieldSize = { textFieldSizeTransaction = it },
                onOutLinedTextFieldValueChanged = { selectedTextTransaction = it },
                onTrailingIconClicked = { isExpandedTransaction = !isExpandedTransaction },
                onDropdownMenuDismissRequest = { isExpandedTransaction = false },
                onDropdownMenuItemClicked = { itemSelected ->
                    selectedTextTransaction = itemSelected

                    val transaction = createTransaction(
                        product = stringToProduct(
                            listOfProducts = listOfProducts,
                            productName = selectedTextProduct,
                        ),
                        transactionType = selectedTextTransaction.toTransactionType(),
                        quantity = quantity,
                    )
                    registerTransactionViewModel.setTotalValue(transaction.transactionAmount)
                    registerTransactionViewModel.setTransactionValue(transaction)
                },
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
        ) {
            DropdownComponent(

                isExpanded = isExpandedProduct,
                items = listOfProducts.map { it.title.limitTo(20) },
                selectedText = selectedTextProduct,
                textFieldSize = textFieldSizeProduct,
                label = stringResource(id = R.string.my_store_product_2),
                modifier = Modifier.width(setItemSize(screenWidth)),
                onOutLinedTextFieldSize = { textFieldSizeProduct = it },
                onOutLinedTextFieldValueChanged = { selectedTextProduct = it },
                onTrailingIconClicked = { isExpandedProduct = !isExpandedProduct },
                onDropdownMenuDismissRequest = { isExpandedProduct = false },
                onDropdownMenuItemClicked = { itemSelected ->
                    selectedTextProduct = itemSelected
                    val transaction = createTransaction(
                        product = stringToProduct(
                            listOfProducts = listOfProducts,
                            productName = itemSelected,
                        ),
                        transactionType = selectedTextTransaction.toTransactionType(),
                        quantity = quantity,
                    )

                    registerTransactionViewModel.setTotalValue(transaction.transactionAmount)
                    registerTransactionViewModel.setTransactionValue(transaction)
                },
            )

            Quantifier(
                maxValue = 100,
                width = setItemSize(screenWidth),
                quantifier = quantity,
                onQuantifierChange = {
                    registerTransactionViewModel.setQuantity(it)
                    val transaction = createTransaction(
                        product = stringToProduct(
                            listOfProducts = listOfProducts,
                            productName = selectedTextProduct,
                        ),
                        transactionType = selectedTextTransaction.toTransactionType(),
                        quantity = it,
                    )
                    registerTransactionViewModel.setTotalValue(transaction.transactionAmount)
                    registerTransactionViewModel.setTransactionValue(transaction)
                },
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            RowComponent(
                leftSideText = stringResource(id = R.string.my_store_total),
                rightSide = {
                    TextCurrencyComponent(
                        value = total.toString(),
                        shouldItemBeVisible = shouldItemBeVisible,
                        type = Type.CURRENCY,
                    )
                },
            )
            Box(modifier = Modifier.padding(end = 22.dp)) {
                FloatingActionButton(
                    enabled = total > 0.0,
                    modifier = Modifier.size(36.dp),
                    colorId = if (total > 0.0) R.color.color_50 else R.color.color_400,
                    onClick = {
                        registerTransactionViewModel.saveTransaction(
                            registerTransactionViewModel
                                .transactionValue.value,
                        )

                        registerTransactionViewModel.updateProductQuantity(
                            registerTransactionViewModel.transactionValue.value.product,
                            quantity,
                            registerTransactionViewModel.transactionValue.value,
                        )

                        // Clear all fields
                        registerTransactionViewModel.clearAll()
                        selectedTextTransaction = ""
                        selectedTextProduct = ""
                    },
                )
            }
        }
    }
}

private fun stringToProduct(
    listOfProducts: List<Product>,
    productName: String,
): Product {
    return listOfProducts.find {
        it.title.limitTo(20) == productName
    } ?: Product()
}

private fun createTransaction(
    product: Product,
    transactionType: TransactionType,
    quantity: Int,
): Transaction {
    val unitValue =
        if (transactionType == TransactionType.SALE) product.salePrice else product.purchasePrice
    return Transaction(
        product = product,
        transactionType = transactionType,
        unitValue = unitValue,
        transactionDate = Calendar.getInstance().time,
        quantity = quantity,
        transactionAmount = quantity * unitValue,
    )
}

private fun setItemSize(screenWidth: Int): Dp = ((screenWidth - 16) / 2).dp
