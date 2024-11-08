package com.example.mystore.features.updatetransaction.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.mystore.features.registertransaction.model.Transaction
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTransactionScreen(
    transactionState: Transaction,
    onUpdateTransaction: (transaction: Transaction) -> Unit
) {
    val focusManager = LocalFocusManager.current
    var showDatePickerDialog by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    var selectedDate by remember { mutableStateOf("") }

    Column {
        Button(onClick = {
            onUpdateTransaction(
                Transaction(
                    id = transactionState.id,
                    transactionType = transactionState.transactionType,
                    unitValue = 1234.00,
                    transactionDate = transactionState.transactionDate,
                    quantity = transactionState.quantity,
                    transactionAmount = transactionState.transactionAmount,
                    product = transactionState.product,
                )
            )
            showDatePickerDialog = true
        }) {
            Text(text = "Alterar transação")
        }
        if (showDatePickerDialog) {
            DatePickerDialog(
                onDismissRequest = { showDatePickerDialog = false },
                confirmButton = {
                    Button(
                        onClick = {
                            datePickerState
                                .selectedDateMillis?.let { millis ->
                                    selectedDate = millis.toBrazilianDateFormat()
                                }
                            showDatePickerDialog = false
                        }) {
                        Text(text = "Escolher data")
                    }
                },
                modifier = Modifier,
                dismissButton = null,
                shape = DatePickerDefaults.shape,
                tonalElevation = DatePickerDefaults.TonalElevation,
                colors = DatePickerDefaults.colors(),
                properties = DialogProperties(usePlatformDefaultWidth = false),
            ){
                DatePicker(datePickerState)
            }
        }
        TextField(
            value = selectedDate,
            onValueChange = { selectedDate = it },
            Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .onFocusEvent {
                    if (it.isFocused) {
                        showDatePickerDialog = true
                        focusManager.clearFocus(force = true)
                    }
                },
            label = {
                Text("Date")
            },
            readOnly = true
        )
    }
}

fun Long.toBrazilianDateFormat(
    pattern: String = "dd/MM/yyyy"
): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(
        pattern, Locale("pt-br")
    ).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    return formatter.format(date)
}