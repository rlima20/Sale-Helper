package com.example.mystore.ui.components.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mystore.R
import com.example.mystore.Screens
import com.example.mystore.TransactionType
import com.example.mystore.Type
import com.example.mystore.limitTo
import com.example.mystore.model.Product
import com.example.mystore.model.Transaction
import com.example.mystore.setItemSize
import com.example.mystore.toShortDateString
import com.example.mystore.toTransactionType
import com.example.mystore.ui.components.commons.AlertDialogComponent
import com.example.mystore.ui.components.commons.DropdownComponent
import com.example.mystore.ui.components.commons.FloatingActionButton
import com.example.mystore.ui.components.commons.Quantifier
import com.example.mystore.ui.components.commons.RowComponent
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.components.commons.SectionInfo
import com.example.mystore.ui.components.commons.TextCurrencyComponent
import com.example.mystore.ui.components.commons.ToastComponent
import com.example.mystore.ui.components.commons.ValidateSection
import com.example.mystore.viewmodel.screen.RegisterTransactionViewModel
import java.util.Calendar

@Composable
fun RegisterTransactionScreen(
    registerTransactionViewModel: RegisterTransactionViewModel,
    shouldItemBeVisible: Boolean,
    clearAllStates: Boolean = false,
    onClearAllStates: (Boolean) -> Unit = {},
) {
    if (clearAllStates) {
        clearStates(
            registerTransactionViewModel = registerTransactionViewModel,
            onChangeSelectedTextTransaction = {},
            onChangeSelectedTextProduct = {},
        )
    }
    onClearAllStates(false)
    registerTransactionViewModel.setScreenWidth(LocalConfiguration.current.screenWidthDp)

    val listOfProducts by registerTransactionViewModel.listOfProducts.collectAsState()
    val listOfTransactionTypes by registerTransactionViewModel.listOfTransactionType.collectAsState()
    val screenWidth by registerTransactionViewModel.screenWidth.collectAsState()
    val quantity by registerTransactionViewModel.quantity.collectAsState()
    val maxQuantity by registerTransactionViewModel.maxQuantity.collectAsState()
    val total by registerTransactionViewModel.totalValue.collectAsState()
    val transaction by registerTransactionViewModel.transactionValue.collectAsState()
    val showAlertDialog by registerTransactionViewModel.showAlertDialogOnRegisterTransaction.collectAsState()
    val showToast by registerTransactionViewModel.showToast.collectAsState()

    Column {
        ValidateSection(
            screen = Screens.REGISTER_TRANSACTION,
            sectionInfo = SectionInfo(
                section = {
                    ScreenSectionComponent(
                        title = stringResource(id = R.string.my_store_register_transaction),
                        body = {
                            RegisterTransactionBody(
                                screenWidth = screenWidth,
                                shouldItemBeVisible = shouldItemBeVisible,
                                listOfProducts = listOfProducts,
                                listOfTransactionTypes = listOfTransactionTypes,
                                quantity = quantity,
                                maxQuantity = maxQuantity,
                                total = total,
                                transaction = transaction,
                                registerTransactionViewModel = registerTransactionViewModel,
                                showAlertDialog = showAlertDialog,
                                onShowAlertDialog = {
                                    registerTransactionViewModel
                                        .setShowAlertDialogOnRegisterTransaction(it)
                                },
                                showToast = showToast,
                                onShowToast = {
                                    registerTransactionViewModel
                                        .setShowToast(it)
                                },
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
    screenWidth: Int,
    shouldItemBeVisible: Boolean = true,
    listOfProducts: List<Product>,
    listOfTransactionTypes: List<TransactionType>,
    quantity: Int,
    maxQuantity: Int,
    total: Double,
    transaction: Transaction,
    registerTransactionViewModel: RegisterTransactionViewModel,
    showAlertDialog: Boolean = false,
    onShowAlertDialog: (Boolean) -> Unit = {},
    showToast: Boolean = false,
    onShowToast: (Boolean) -> Unit = {},
) {
    if (showToast) {
        ToastComponent(stringResource(R.string.my_store_successfull_transaction_saved))
    }
    Column {
        var selectedTextTransaction: String by remember { mutableStateOf("") }
        var isExpandedTransaction: Boolean by remember { mutableStateOf(false) }
        var textFieldSizeTransaction: Size by remember { mutableStateOf(Size.Zero) }

        var selectedTextProduct: String by remember { mutableStateOf("") }
        var isExpandedProduct: Boolean by remember { mutableStateOf(false) }
        var textFieldSizeProduct: Size by remember { mutableStateOf(Size.Zero) }

        if (showAlertDialog) {
            AlertDialogComponent(
                size = null,
                title = stringResource(R.string.my_store_confirmation_transaction),
                content = {
                    Text(
                        text = stringResource(R.string.my_store_confirmation_message),
                        color = colorResource(id = R.color.color_700),
                    )
                },
                confirmButton = {
                    Button(onClick = {
                        onShowToast(true)

                        registerTransactionViewModel.saveTransaction(
                            registerTransactionViewModel
                                .transactionValue.value,
                        )

                        registerTransactionViewModel.incrementListOfTransactions(
                            registerTransactionViewModel
                                .transactionValue.value,
                        )

                        registerTransactionViewModel.updateProductQuantity(
                            registerTransactionViewModel.transactionValue.value.product,
                            quantity,
                            registerTransactionViewModel.transactionValue.value,
                        )

                        clearStates(
                            registerTransactionViewModel = registerTransactionViewModel,
                            onChangeSelectedTextTransaction = { selectedTextTransaction = it },
                            onChangeSelectedTextProduct = { selectedTextProduct = it },
                        )
                        onShowAlertDialog(false)
                    }) {
                        Text(
                            text = stringResource(R.string.my_store_ok),
                            color = colorResource(id = R.color.color_50),
                        )
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        onShowAlertDialog(false)
                    }) {
                        Text(
                            text = stringResource(R.string.my_store_cancel),
                            color = colorResource(id = R.color.color_50),
                        )
                    }
                },
                onDismissRequest = {
                    onShowAlertDialog(false)
                },
                color = colorResource(id = R.color.white),
            )
        }
        // Dropdown TransactionType
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

                    setScreenStates(
                        listOfProducts = listOfProducts,
                        onSelectedTextTransaction = { selectedTextTransaction = it },
                        selectedTextTransaction = selectedTextTransaction,
                        quantity = quantity,
                        maxQuantity = maxQuantity,
                        productName = selectedTextProduct,
                        registerTransactionViewModel = registerTransactionViewModel,
                        itemSelected = itemSelected,
                        onSetQuantity = { quantity, newTransaction ->
                            registerTransactionViewModel.setQuantity(
                                if (quantity > newTransaction.product.quantity) {
                                    if (newTransaction.product.quantity == 0) {
                                        1
                                    } else {
                                        newTransaction.product.quantity
                                    }
                                } else {
                                    quantity
                                },
                            )
                        },
                    )
                },
                transactionDetailColors = Triple(
                    R.color.color_500,
                    R.color.color_500,
                    R.color.white,
                ),
            )
        }

        // Dropdown Product
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
                modifier = Modifier.width(screenWidth.setItemSize()),
                onOutLinedTextFieldSize = { textFieldSizeProduct = it },
                onOutLinedTextFieldValueChanged = { selectedTextProduct = it },
                onTrailingIconClicked = { isExpandedProduct = !isExpandedProduct },
                onDropdownMenuDismissRequest = { isExpandedProduct = false },
                onDropdownMenuItemClicked = { itemSelected ->
                    setScreenStates(
                        listOfProducts = listOfProducts,
                        selectedTextTransaction = selectedTextTransaction,
                        quantity = quantity,
                        productName = itemSelected,
                        maxQuantity = maxQuantity,
                        registerTransactionViewModel = registerTransactionViewModel,
                        itemSelected = itemSelected,
                        onSelectedTextTransaction = { selectedTextProduct = it },
                    )
                },
                transactionDetailColors = Triple(
                    R.color.color_500,
                    R.color.color_500,
                    R.color.white,
                ),
            )

            Quantifier(
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp)
                    .width(screenWidth.setItemSize()),
                maxQuantity = setMaxValue(
                    selectedTextTransaction = selectedTextTransaction,
                    transaction = transaction,
                    maxQuantity = maxQuantity,
                ),
                quantity = quantity,
                onQuantifierChange = { quantifierQuantity ->
                    setScreenStates(
                        listOfProducts = listOfProducts,
                        selectedTextTransaction = selectedTextTransaction,
                        quantity = quantity,
                        productName = selectedTextProduct,
                        maxQuantity = maxQuantity,
                        registerTransactionViewModel = registerTransactionViewModel,
                        quantifierQuantity = quantifierQuantity,
                        onQuantifierQuantity = { registerTransactionViewModel.setQuantity(it) },
                    )
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
                        value = setText(transaction, total),
                        currencyVisibility = shouldItemBeVisible,
                        type = Type.CURRENCY_ONLY,
                    )
                },
            )
            Box(modifier = Modifier.padding(end = 22.dp)) {
                FloatingActionButton(
                    enabled = setEnabled(total, transaction),
                    modifier = Modifier.size(36.dp),
                    colorId = if (setEnabled(total, transaction)) {
                        R.color.color_800
                    } else {
                        R.color.color_100
                    },
                    onClick = {
                        onShowAlertDialog(true)
                    },
                )
            }
            onShowToast(false)
        }
    }
}

@Composable
private fun setEnabled(
    total: Double,
    transaction: Transaction,
) = (
    (total > 0.0) && (
        if (transaction.transactionType == TransactionType.SALE) {
            transaction.product.quantity > 0
        } else {
            true
        }
        )
    )

@Composable
private fun setText(
    transaction: Transaction,
    total: Double,
) = if ((transaction.product.quantity == 0) &&
    (transaction.transactionType == TransactionType.SALE)
) {
    "0"
} else {
    total.toString()
}

private fun clearStates(
    registerTransactionViewModel: RegisterTransactionViewModel,
    onChangeSelectedTextTransaction: (String) -> Unit = {},
    onChangeSelectedTextProduct: (String) -> Unit = {},
) {
    registerTransactionViewModel.clearAll()
    onChangeSelectedTextTransaction("")
    onChangeSelectedTextProduct("")
}

@Composable
private fun setMaxValue(
    selectedTextTransaction: String,
    transaction: Transaction,
    maxQuantity: Int,
) = if (selectedTextTransaction.toTransactionType() == TransactionType.SALE) {
    transaction.product.quantity
} else {
    maxQuantity
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
        transactionDate = Calendar.getInstance().time.toShortDateString(),
        quantity = quantity,
        transactionAmount = quantity * unitValue,
    )
}

private fun setScreenStates(
    listOfProducts: List<Product>,
    selectedTextTransaction: String,
    quantity: Int,
    maxQuantity: Int,
    productName: String,
    registerTransactionViewModel: RegisterTransactionViewModel,
    itemSelected: String = "",
    onSelectedTextTransaction: (String) -> Unit = {},
    onSetQuantity: (Int, Transaction) -> Unit = { _, _ -> },
    quantifierQuantity: Int? = null,
    onQuantifierQuantity: (Int) -> Unit = {},
) {
    onQuantifierQuantity(quantifierQuantity ?: 0)
    onSelectedTextTransaction(itemSelected)

    val newTransaction = createTransaction(
        product = stringToProduct(
            listOfProducts = listOfProducts,
            productName = productName,
        ),
        transactionType = selectedTextTransaction.toTransactionType(),
        quantity = quantifierQuantity ?: quantity,
    )
    setStates(
        registerTransactionViewModel = registerTransactionViewModel,
        selectedTextTransaction = selectedTextTransaction,
        newTransaction = newTransaction,
        maxQuantity = maxQuantity,
    )

    onSetQuantity(quantity, newTransaction)
}

private fun setStates(
    registerTransactionViewModel: RegisterTransactionViewModel,
    selectedTextTransaction: String,
    newTransaction: Transaction,
    maxQuantity: Int,
) {
    registerTransactionViewModel.setTotalValue(newTransaction.transactionAmount)
    registerTransactionViewModel.setTransactionValue(newTransaction)
    registerTransactionViewModel.setMaxQuantity(
        setMaxQuantityByTransactionType(
            selectedTextTransaction = selectedTextTransaction,
            maxQuantity = maxQuantity,
            newTransaction = newTransaction,
        ),
    )
}

private fun setMaxQuantityByTransactionType(
    selectedTextTransaction: String,
    maxQuantity: Int,
    newTransaction: Transaction,
): Int {
    return if (selectedTextTransaction.toTransactionType() == TransactionType
            .SALE && maxQuantity > newTransaction.product.quantity
    ) {
        newTransaction.product.quantity + 1
    } else if (selectedTextTransaction.toTransactionType() == TransactionType
            .PURCHASE
    ) {
        newTransaction.product.maxQuantityToBuy
    } else {
        newTransaction.product.quantity
    }
}
