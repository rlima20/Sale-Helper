package com.example.mystore.features.updatetransaction.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.mystore.R
import com.example.mystore.enums.Type
import com.example.mystore.features.registertransaction.model.Transaction
import com.example.mystore.features.updatetransaction.viewmodel.UpdateTransactionViewModel
import com.example.mystore.ui.components.commons.OutLinedTextFieldComponent
import com.example.mystore.ui.components.commons.ScreenSectionComponent
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
    shouldItemBeVisible: Boolean,
) {
    updateTransactionViewModel.viewState.transaction.value = transaction
    Column {
        ScreenSectionComponent(
            title = TRANSACTION_UPDATE,
            body = {
                UpdateTransactionComponent(
                    transaction,
                    updateTransactionViewModel,
                    shouldItemBeVisible
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
    shouldItemBeVisible: Boolean, // Todo - Fixme.
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
            selectedText = transaction.product.title,
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

    // Transaction Date
    DatePickerComponent(
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
