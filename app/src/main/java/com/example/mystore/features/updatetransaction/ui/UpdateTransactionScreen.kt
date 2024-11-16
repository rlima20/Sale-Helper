package com.example.mystore.features.updatetransaction.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import com.example.mystore.R
import com.example.mystore.features.registertransaction.model.Transaction
import com.example.mystore.features.updatetransaction.viewmodel.UpdateTransactionViewModel
import com.example.mystore.ui.components.commons.OutLinedTextFieldComponent
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.model.OutLinedTextFieldComponentProps
import com.example.mystore.ui.model.TextFieldAppearance
import com.example.mystore.ui.model.TextFieldCallbacks

/* todo - update screen here */
/*
* Transação | Edição
* nome - Adidas
* tipo - Venda
* data - 10/10/2023
* valor unitário - 15,00
* Quantifier/ botão salvar
* */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTransactionScreen(
    transaction: Transaction,
    updateTransactionViewModel: UpdateTransactionViewModel,
    onUpdateTransaction: (transaction: Transaction) -> Unit
) {
    updateTransactionViewModel.viewState.transaction.value = transaction

    val focusManager = LocalFocusManager.current
    var showDatePickerDialog by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    var selectedDate by remember { mutableStateOf(transaction.transactionDate) }
    val transactionViewState = updateTransactionViewModel.viewState.transaction

    Column {
        ScreenSectionComponent(
            title = TRANSACTION_UPDATE,
            body = {
                UpdateTransactionComponent(transaction = transactionViewState.value)

//                Button(onClick = {
//                    onUpdateTransaction(
//                        Transaction(
//                            id = transactionViewState.value.id,
//                            transactionType = transactionViewState.value.transactionType,
//                            unitValue = 1234.00,
//                            transactionDate = selectedDate,
//                            quantity = transactionViewState.value.quantity,
//                            transactionAmount = transactionViewState.value.transactionAmount,
//                            product = transactionViewState.value.product,
//                        )
//                    )
//                    showDatePickerDialog = true
//                }, content = { Text(text = "Alterar transação") })
//                if (showDatePickerDialog) {
//                    DatePickerComponent(
//                        selectedDate = selectedDate,
//                        datePickerState = datePickerState,
//                        onShowDatePickerDialog = { showDatePickerDialog = it },
//                        onSelectedDate = { selectedDate = it },
//                        onValueChange = { selectedDate = it },
//                        onClearFocus = { focusManager.clearFocus(force = true) }
//                    )
//                }
            }
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UpdateTransactionComponent(
    transaction: Transaction
) {

    // Transaction Product title
    val titleKeyboardController = LocalSoftwareKeyboardController.current
    val titleFocusManager = LocalFocusManager.current

    OutLinedTextFieldComponent(
        outLinedTextFieldComponentProps = OutLinedTextFieldComponentProps(
            appearance = TextFieldAppearance(
                enabled = false,
                label = stringResource(R.string.my_store_product_title),
                modifier = Modifier,
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
}

private const val TRANSACTION_UPDATE = "Transação | EDIÇÃO"

