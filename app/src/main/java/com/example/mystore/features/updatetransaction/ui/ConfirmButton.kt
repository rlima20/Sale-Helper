package com.example.mystore.features.updatetransaction.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
        modifier = Modifier.padding(end = 16.dp),
        onClick = {
            datePickerState
                .selectedDateMillis?.let { millis ->
                    onSelectedDate(millis.toBrazilianDateFormat())
                }
            onShowDatePickerDialog(false)
        }) {
        Text(
            color = colorResource(R.color.color_50),
            text = stringResource(R.string.my_store_transaction_choose_date)
        )
    }
}