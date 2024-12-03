package com.example.mystore.features.updatetransaction.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.mystore.R
import com.example.mystore.enums.Type
import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registertransaction.model.Transaction
import com.example.mystore.features.registertransaction.ui.setText
import com.example.mystore.features.updatetransaction.viewmodel.UpdateTransactionViewModel
import com.example.mystore.toFormattedType
import com.example.mystore.toTransactionType
import com.example.mystore.ui.components.commons.FloatingActionButton
import com.example.mystore.ui.components.commons.OutLinedTextFieldComponent
import com.example.mystore.ui.components.commons.Quantifier
import com.example.mystore.ui.components.commons.RowComponent
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.components.commons.ShowAlertDialogComponent
import com.example.mystore.ui.components.commons.TextCurrencyComponent
import com.example.mystore.ui.components.commons.ToastComponent
import com.example.mystore.ui.components.commons.setUnit
import com.example.mystore.ui.components.commons.toDoubleTransaction
import com.example.mystore.ui.model.OutLinedTextFieldComponentProps
import com.example.mystore.ui.model.TextFieldAppearance
import com.example.mystore.ui.model.TextFieldCallbacks

@Composable
fun UpdateTransactionScreen(
    transaction: Transaction,
    updateTransactionViewModel: UpdateTransactionViewModel,
    onUpdateTransaction: (transaction: Transaction) -> Unit,
) {
    updateTransactionViewModel.viewState.transaction.value = transaction
    Column {
        ScreenSectionComponent(
            title = TRANSACTION_UPDATE,
            body = {
                UpdateTransactionComponent(
                    transaction = transaction,
                    updateTransactionViewModel = updateTransactionViewModel,
                    onUpdateTransaction = onUpdateTransaction,
                )
            }
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun UpdateTransactionComponent(
    transaction: Transaction,
    updateTransactionViewModel: UpdateTransactionViewModel,
    onUpdateTransaction: (transaction: Transaction) -> Unit,
) {
    // Update Transaction ViewState
    val viewState = updateTransactionViewModel.viewState.transaction

    // Transaction Product title vars
    val titleKeyboardController = LocalSoftwareKeyboardController.current
    val titleFocusManager = LocalFocusManager.current

    // DropdownType vars
    var transactionTypeSelected: String by remember { mutableStateOf(transaction.transactionType.toString()) }
    var textFieldSizeTransaction: Size by remember { mutableStateOf(Size.Zero) }
    var isExpandedTransaction: Boolean by remember { mutableStateOf(false) }
    val listOfTransactionTypes = updateTransactionViewModel.viewState.listOfTransactionType

    // DatePicker vars
    val focusManager = LocalFocusManager.current
    var showDatePickerDialog by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    var selectedDate by remember { mutableStateOf(viewState.value.transactionDate) }

    // Transaction UnitValue vars
    val unitValueKeyboardController = LocalSoftwareKeyboardController.current
    val unitValueFocusManager = LocalFocusManager.current
    val keyboardType = KeyboardType.Number
    val initialValue = setUnit(
        transaction.unitValue.toString(),
        Type.CURRENCY_ONLY,
        true
    )
    var unitValue: String by remember { mutableStateOf(initialValue) }

    // Quantifier vars
    var quantity by remember { mutableIntStateOf(viewState.value.quantity) }

    // Total var
    var total by remember { mutableDoubleStateOf(viewState.value.transactionAmount) }

    SetupUpdateTransactionSuccessDialog(
        updateTransactionViewModel = updateTransactionViewModel,
        onUpdateTransaction = {
            onUpdateTransaction(
                createTransaction(
                    transaction,
                    transactionTypeSelected,
                    unitValue,
                    selectedDate,
                    quantity
                )
            )
        })

    SetupSuccessToast(updateTransactionViewModel = updateTransactionViewModel)

    //Product Title
    OutLinedTextFieldComponent(
        outLinedTextFieldComponentProps = OutLinedTextFieldComponentProps(
            appearance = TextFieldAppearance(
                enabled = false,
                label = stringResource(R.string.my_store_product_title),
                modifier = Modifier.padding(8.dp),
                transactionDetailColors = Triple(
                    R.color.color_900,
                    R.color.white,
                    R.color.white
                )
            ),
            selectedText = transaction.product.description,
            keyboardController = titleKeyboardController,
            focusManager = titleFocusManager,
            callbacks = TextFieldCallbacks()
        )
    )

    // Transaction Type
    DropdownTransactionTypeComponent(
        transactionTypeSelected = transactionTypeSelected,
        textFieldSizeTransaction = textFieldSizeTransaction,
        isDropdownExpanded = isExpandedTransaction,
        listOfTransactionTypes = listOfTransactionTypes,
        onSetTextFieldSizeTransaction = { textFieldSizeTransaction = it },
        onSetIsDropdownExpanded = { isExpandedTransaction = it },
        onDropdownMenuItemClicked = { transactionTypeSelected = it }
    )

    Row(verticalAlignment = Alignment.CenterVertically) {

        // Transaction Date
        DatePickerComponent(
            modifier = Modifier.width(setTextFieldSize()),
            showDatePickerDialog = showDatePickerDialog,
            selectedDate = selectedDate,
            datePickerState = datePickerState,
            onShowDatePickerDialog = { showDatePickerDialog = it },
            onSelectedDate = { selectedDate = it },
            onValueChange = { selectedDate = it },
            onClearFocus = { focusManager.clearFocus(force = true) }
        )

        // Transaction UnitValue
        OutLinedTextFieldComponent(
            outLinedTextFieldComponentProps = OutLinedTextFieldComponentProps(
                appearance = TextFieldAppearance(
                    enabled = true,
                    label = stringResource(R.string.my_store_unit_value_update_transaction),
                    modifier = Modifier
                        .padding(8.dp)
                        .width(170.dp)
                        .onFocusEvent {
                            setActionWhenFocused(it) {
                                unitValue = unitValue.toClearDouble()
                            }
                        },
                    transactionDetailColors = Triple(
                        R.color.color_900,
                        R.color.white,
                        R.color.white
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
                ),
                selectedText = unitValue,
                keyboardController = unitValueKeyboardController,
                focusManager = unitValueFocusManager,
                callbacks = TextFieldCallbacks(
                    onValueChanged = { unitValue = it },
                    onDone = {
                        unitValue = toDoubleTransaction(
                            unitValue.removeCurrencyFormat(),
                            Type.CURRENCY_ONLY,
                            true
                        )
                    },
                )
            ),
        )
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        // Quantifier
        Quantifier(
            modifier = Modifier
                .width(LocalConfiguration.current.screenWidthDp.dp / 2)
                .padding(start = 8.dp, end = 4.dp),
            shouldStartWithZero = true,
            quantity = quantity,
            onQuantifierChange = {
                total = it.toDouble() * unitValue.removeCurrencyFormat().toDouble()
                quantity = it
            },
            maxQuantity = transaction.product.maxQuantityToBuy
        )

        // Save button
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
                .padding(top = 8.dp),
        ) {
            FloatingActionButton(
                enabled = true,
                modifier = Modifier.size(36.dp),
                colorId = R.color.color_800,
                onClick = { updateTransactionViewModel.setConfirmationAlert(true) },
            )
        }
    }

    // Total
    RowComponent(
        leftSideText = stringResource(id = R.string.my_store_total),
        rightSide = {
            TextCurrencyComponent(
                value = setText(transaction, total),
                shouldItemBeVisible = true,
                type = Type.CURRENCY_ONLY,
            )
        },
    )
}

private fun createTransaction(
    transaction: Transaction,
    transactionTypeSelected: String,
    unitValue: String,
    selectedDate: String,
    quantity: Int
) = Transaction(
    id = transaction.id,
    transactionType = transactionTypeSelected.toFormattedType(),
    unitValue = unitValue.removeCurrencyFormat().toDouble(),
    transactionDate = selectedDate,
    quantity = quantity,
    transactionAmount = (unitValue.removeCurrencyFormat().toDouble() * quantity),
    product = Product(
        productId = transaction.product.productId,
        title = transaction.product.title,
        description = transaction.product.description,
        quantity = transaction.product.quantity,
        maxQuantityToBuy = transaction.product.maxQuantityToBuy,
        purchasePrice = transaction.product.purchasePrice,
        salePrice = transaction.product.salePrice,
        imageUrl = transaction.product.imageUrl
    )
)

// Show success update transaction toast
@Composable
private fun SetupSuccessToast(
    updateTransactionViewModel: UpdateTransactionViewModel,
) {
    with(updateTransactionViewModel.viewState) {
        if (showSuccessUpdateToast.value) {
            ToastComponent(stringResource(R.string.my_store_successful_edit_transaction))
        }
    }
}

@Composable
private fun SetupUpdateTransactionSuccessDialog(
    updateTransactionViewModel: UpdateTransactionViewModel,
    onUpdateTransaction: () -> Unit
) {
    with(updateTransactionViewModel.viewState) {
        if (showConfirmationAlert.value) {
            ShowAlertDialogComponent(
                showAlert = true,
                title = stringResource(R.string.my_store_registry_update),
                alertDialogMessage = stringResource(R.string.my_store_edition_confirmation),
                onDismissRequest = { updateTransactionViewModel.setConfirmationAlert(false) },
                onDismissButtonClicked = { updateTransactionViewModel.setConfirmationAlert(false) },
                onConfirmButtonClicked = {
                    onUpdateTransaction()
                    updateTransactionViewModel.setToast(true)
                    updateTransactionViewModel.setConfirmationAlert(false)
                },
            )
        }
    }
}


@Composable
private fun setTextFieldSize() = (LocalConfiguration.current.screenWidthDp.dp.value / 2).dp

private fun setActionWhenFocused(
    textComponent: FocusState,
    onValueChange: () -> Unit
) {
    if (textComponent.isFocused) {
        onValueChange()
    }
}

private fun String.toClearDouble() =
    this.replace("R\\$\\s*".toRegex(), "").replace(" ", "")

private fun String.removeCurrencyFormat() = this
    .replace("R\\$\\s*".toRegex(), "")
    .replace(" ", "")
    .replace(",", ".")

private const val TRANSACTION_UPDATE = "Transação | EDIÇÃO"
