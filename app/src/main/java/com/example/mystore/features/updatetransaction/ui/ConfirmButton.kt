package com.example.mystore.features.updatetransaction.ui

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.mystore.R
import com.example.mystore.toBrazilianDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmButton(
    datePickerState: DatePickerState,
    onSelectedDate: (selectedDate: String) -> Unit,
    onShowDatePickerDialog: (boolean: Boolean) -> Unit
) {
    Button(
        onClick = {
            datePickerState
                .selectedDateMillis?.let { millis ->
                    onSelectedDate(millis.toBrazilianDateFormat())
                }
            onShowDatePickerDialog(false)
        }) {
        Text(text = stringResource(R.string.my_store_transaction_choose_date))
    }
}