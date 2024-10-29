package com.example.mystore.features.registertransaction.ui

import android.annotation.SuppressLint
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mystore.R
import com.example.mystore.enums.Screens
import com.example.mystore.enums.Section
import com.example.mystore.enums.TransactionType
import com.example.mystore.enums.Type
import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registertransaction.model.RegisterTransactionBodyProps
import com.example.mystore.features.registertransaction.model.RegisterTransactionScreenProps
import com.example.mystore.features.registertransaction.model.Transaction
import com.example.mystore.features.registertransaction.viewmodel.RegisterTransactionViewModel
import com.example.mystore.limitTo
import com.example.mystore.setItemSize
import com.example.mystore.toShortDateString
import com.example.mystore.toTransactionString
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
import com.example.mystore.ui.model.DropdownAppearance
import com.example.mystore.ui.model.DropdownCallbacks
import com.example.mystore.ui.model.DropdownColors
import com.example.mystore.ui.model.DropdownComponentProps
import com.example.mystore.ui.model.DropdownItem
import com.example.mystore.ui.model.DropdownState
import com.example.mystore.ui.model.OutLinedTextFieldComponentProps
import com.example.mystore.ui.model.TextFieldAppearance
import com.example.mystore.ui.model.TextFieldCallbacks
import java.util.Calendar

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RegisterTransactionScreen(
    registerTransactionScreenProps: RegisterTransactionScreenProps
) {
    with(registerTransactionScreenProps) {
        registerTransactionViewModel.getListOfTransactions()
        ClearAllStates()

        registerTransactionViewModel.setScreenWidth(LocalConfiguration.current.screenWidthDp)
        val listOfProducts by registerTransactionViewModel.commonViewState.listOfProducts.collectAsState()
        val listOfTransactionTypes =
            registerTransactionViewModel.registerTransactionViewState.listOfTransactionType
        val screenWidth by registerTransactionViewModel.registerTransactionViewState.screenWidth.collectAsState()
        val quantity by registerTransactionViewModel.registerTransactionViewState.quantity.collectAsState()
        val maxQuantity by registerTransactionViewModel.registerTransactionViewState.maxQuantity.collectAsState()
        val total by registerTransactionViewModel.registerTransactionViewState.totalValue.collectAsState()
        val transaction by registerTransactionViewModel.registerTransactionViewState.transactionValue.collectAsState()
        val showAlertDialog by registerTransactionViewModel.registerTransactionViewState.showAlertDialogOnRegisterTransaction.collectAsState()
        val showToast by registerTransactionViewModel.registerTransactionViewState.showToast.collectAsState()

        Column {
            ValidateSection(
                screen = Screens.REGISTER_TRANSACTION,
                sectionInfo = SectionInfo(
                    sectionName = Section.TRANSACTIONS,
                    section = {
                        ScreenSectionComponent(
                            title = stringResource(id = R.string.my_store_register_transaction),
                            body = {
                                RegisterTransactionBody(
                                    registerTransactionBodyProps = RegisterTransactionBodyProps(
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
                                    ),
                                )
                            },
                        )
                    },
                ),
            )
        }
    }
}

@Composable
private fun RegisterTransactionBody(
    registerTransactionBodyProps: RegisterTransactionBodyProps
) {
    with(registerTransactionBodyProps) {
        ShowToastComponent()
        Column {
            var selectedTextTransaction: String by remember { mutableStateOf("") }
            var isExpandedTransaction: Boolean by remember { mutableStateOf(false) }
            var textFieldSizeTransaction: Size by remember { mutableStateOf(Size.Zero) }

            var selectedTextProduct: String by remember { mutableStateOf("") }
            var isExpandedProduct: Boolean by remember { mutableStateOf(false) }
            var textFieldSizeProduct: Size by remember { mutableStateOf(Size.Zero) }

            ShowAlertDialog(
                onChangeSelectedTextTransaction = { selectedTextTransaction = it },
                onChangeSelectedTextProduct = { selectedTextProduct = it }
            )

            DropdownTransactionType(
                textFieldSizeTransaction = textFieldSizeTransaction,
                isExpandedTransaction = isExpandedTransaction,
                selectedTextTransaction = selectedTextTransaction,
                selectedTextProduct = selectedTextProduct,
                onSetIsExpandedTransaction = { isExpandedTransaction = it },
                onSetSelectedTextTransaction = { selectedTextTransaction = it },
                onSetTextFieldSizeTransaction = { textFieldSizeTransaction = it }
            )

            DropdownProduct(
                textFieldSizeProduct = textFieldSizeProduct,
                isExpandedProduct = isExpandedProduct,
                selectedTextProduct = selectedTextProduct,
                selectedTextTransaction = selectedTextTransaction,
                onSetIsExpandedProduct = { isExpandedProduct = it },
                onSetSelectedTextProduct = { selectedTextProduct = it },
                onSetTextFieldSizeProduct = { textFieldSizeProduct = it }
            )

            Quantifier(
                selectedTextTransaction,
                selectedTextProduct
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
                RowComponent()
                SaveFloatingActionButton()
                onShowToast(false)
            }
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

// Todo - Refatorar essa função - está muito grande
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


@Composable
private fun RegisterTransactionScreenProps.ClearAllStates() {
    if (clearAllStates) {
        clearStates(
            registerTransactionViewModel = registerTransactionViewModel,
            onChangeSelectedTextTransaction = {},
            onChangeSelectedTextProduct = {},
        )
    }
    onClearAllStates(false)
}

@Composable
private fun RegisterTransactionBodyProps.ShowToastComponent() {
    if (showToast) {
        ToastComponent(stringResource(R.string.my_store_successfull_transaction_saved))
    }
}

@Composable
private fun RegisterTransactionBodyProps.SetDismissButton() {
    Button(onClick = {
        onShowAlertDialog(false)
    }) {
        Text(
            text = stringResource(R.string.my_store_cancel),
            color = colorResource(id = R.color.color_50),
        )
    }
}

@Composable
private fun SetConfirmButtonText() {
    Text(
        text = stringResource(R.string.my_store_ok),
        color = colorResource(id = R.color.color_50),
    )
}

@Composable
private fun SetAlertDialogContent() {
    Text(
        text = stringResource(R.string.my_store_confirmation_message),
        color = colorResource(id = R.color.color_700),
    )
}

@Composable
private fun RegisterTransactionBodyProps.RowComponent() {
    RowComponent(
        leftSideText = stringResource(id = R.string.my_store_total),
        rightSide = {
            TextCurrencyComponent(
                value = setText(transaction, total),
                shouldItemBeVisible = shouldItemBeVisible,
                type = Type.CURRENCY_ONLY,
            )
        },
    )
}

@Composable
private fun RegisterTransactionBodyProps.SaveFloatingActionButton() {
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
}

@Composable
private fun RegisterTransactionBodyProps.Quantifier(
    selectedTextTransaction: String,
    selectedTextProduct: String
) {
    Quantifier(
        modifier = Modifier
            .padding(start = 8.dp, top = 8.dp, end = 16.dp, bottom = 16.dp)
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

@Composable
private fun RegisterTransactionBodyProps.DropdownProduct(
    textFieldSizeProduct: Size,
    isExpandedProduct: Boolean,
    selectedTextProduct: String,
    selectedTextTransaction: String,
    onSetTextFieldSizeProduct: (textFieldSize: Size) -> Unit,
    onSetIsExpandedProduct: (isExpandedProduct: Boolean) -> Unit,
    onSetSelectedTextProduct: (selectedTextProduct: String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
    ) {
        DropdownComponent(
            dropdownComponentProps = DropdownComponentProps(
                appearance = DropdownAppearance(
                    textFieldSize = textFieldSizeProduct,
                    label = stringResource(id = R.string.my_store_product_2),
                    modifier = Modifier.width(screenWidth.setItemSize()),
                    DropdownColors(
                        transactionDetailColors = Triple(
                            R.color.color_500,
                            R.color.color_500,
                            R.color.white,
                        ),
                    )
                ),
                state = DropdownState(
                    isExpanded = isExpandedProduct,
                    selectedText = selectedTextProduct,
                ),
                items = DropdownItem(
                    items = listOfProducts.map { it.title.limitTo(20) },
                ),
                callbacks = DropdownCallbacks(
                    onDropdownMenuDismissRequest = { onSetIsExpandedProduct(false) },
                    onDropdownMenuItemClicked = { itemSelected ->
                        setScreenStates(
                            listOfProducts = listOfProducts,
                            selectedTextTransaction = selectedTextTransaction,
                            quantity = quantity,
                            productName = itemSelected,
                            maxQuantity = maxQuantity,
                            registerTransactionViewModel = registerTransactionViewModel,
                            itemSelected = itemSelected,
                            onSelectedTextTransaction = { onSetSelectedTextProduct(it) },
                        )
                    },
                )
            ),
            outLinedTextFieldComponentProps = OutLinedTextFieldComponentProps(
                appearance = TextFieldAppearance(
                    label = stringResource(id = R.string.my_store_product_2),
                ),
                selectedText = selectedTextProduct,
                focusManager = LocalFocusManager.current,
                callbacks = TextFieldCallbacks(
                    onOutLinedTextFieldSize = { onSetTextFieldSizeProduct(it) },
                    onOutLinedTextFieldValueChanged = { onSetSelectedTextProduct(it) },
                    onTrailingIconClicked = { onSetIsExpandedProduct(!isExpandedProduct) }
                )
            ),
        )
    }
}

@Composable
private fun RegisterTransactionBodyProps.DropdownTransactionType(
    textFieldSizeTransaction: Size,
    isExpandedTransaction: Boolean,
    selectedTextTransaction: String,
    selectedTextProduct: String,
    onSetTextFieldSizeTransaction: (textFieldSize: Size) -> Unit,
    onSetIsExpandedTransaction: (isExpandedTransaction: Boolean) -> Unit,
    onSetSelectedTextTransaction: (selectedTextTransaction: String) -> Unit
) {
    Row(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
    ) {
        DropdownComponent(
            dropdownComponentProps = DropdownComponentProps(
                appearance = DropdownAppearance(
                    textFieldSize = textFieldSizeTransaction,
                    label = stringResource(id = R.string.my_store_transaction_type),
                    modifier = Modifier.fillMaxWidth(),
                    colors = DropdownColors(
                        transactionDetailColors = Triple(
                            R.color.color_500,
                            R.color.color_500,
                            R.color.white,
                        ),
                    )
                ),
                state = DropdownState(
                    isExpanded = isExpandedTransaction,
                    selectedText = selectedTextTransaction,
                ),
                items = DropdownItem(
                    items = listOfTransactionTypes.map { it.name },
                ),
                callbacks = DropdownCallbacks(
                    onDropdownMenuDismissRequest = { onSetIsExpandedTransaction(false) },
                    onDropdownMenuItemClicked = { itemSelected ->
                        onSetSelectedTextTransaction(itemSelected)
                        setScreenStates(
                            listOfProducts = listOfProducts,
                            onSelectedTextTransaction = { onSetSelectedTextTransaction(it) },
                            selectedTextTransaction = selectedTextTransaction,
                            quantity = quantity,
                            maxQuantity = maxQuantity,
                            productName = selectedTextProduct,
                            registerTransactionViewModel = registerTransactionViewModel,
                            itemSelected = itemSelected,
                            onSetQuantity = { quantity, newTransaction -> // todo - melhorar isso
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
                ),
            ),
            outLinedTextFieldComponentProps = OutLinedTextFieldComponentProps(
                appearance = TextFieldAppearance(
                    label = stringResource(id = R.string.my_store_transaction_type),
                ),
                selectedText = selectedTextTransaction.toTransactionString(),
                focusManager = LocalFocusManager.current,
                callbacks = TextFieldCallbacks(
                    onOutLinedTextFieldSize = { onSetTextFieldSizeTransaction(it) },
                    onOutLinedTextFieldValueChanged = { onSetSelectedTextTransaction(it) },
                    onTrailingIconClicked = {
                        onSetIsExpandedTransaction(!isExpandedTransaction)
                    },
                ),
            )
        )
    }
}

@Composable
private fun RegisterTransactionBodyProps.ShowAlertDialog(
    onChangeSelectedTextTransaction: (transactionSelectedTextChanged: String) -> Unit,
    onChangeSelectedTextProduct: (productSelectedTextChanged: String) -> Unit
) {
    if (showAlertDialog) {
        AlertDialogComponent(
            size = null,
            title = stringResource(R.string.my_store_confirmation_transaction),
            content = { SetAlertDialogContent() },
            confirmButton = {
                SetConfirmButton(
                    onChangeSelectedTextTransaction = { onChangeSelectedTextTransaction(it) },
                    onChangeSelectedTextProduct = { onChangeSelectedTextProduct(it) }
                )
            },
            dismissButton = { SetDismissButton() },
            onDismissRequest = { onShowAlertDialog(false) },
            color = colorResource(id = R.color.white),
        )
    }
}

@Composable
private fun RegisterTransactionBodyProps.SetConfirmButton(
    onChangeSelectedTextTransaction: (transactionSelectedTextChanged: String) -> Unit,
    onChangeSelectedTextProduct: (productSelectedTextChanged: String) -> Unit
) {
    Button(onClick = {
        onShowToast(true)

        with(registerTransactionViewModel) {
            saveTransaction(registerTransactionViewState.transactionValue.value)
            incrementListOfTransactions(registerTransactionViewState.transactionValue.value)
            updateProductQuantity(
                registerTransactionViewState.transactionValue.value.product,
                quantity,
                registerTransactionViewState.transactionValue.value,
            )
            clearStates(
                registerTransactionViewModel = this,
                onChangeSelectedTextTransaction = { onChangeSelectedTextTransaction(it) },
                onChangeSelectedTextProduct = { onChangeSelectedTextProduct(it) },
            )
        }

        onShowAlertDialog(false)
    }) { SetConfirmButtonText() }
}
