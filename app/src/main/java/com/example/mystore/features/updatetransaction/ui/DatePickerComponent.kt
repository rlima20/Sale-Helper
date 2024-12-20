package com.example.mystore.features.updatetransaction.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.mystore.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerComponent(
    modifier: Modifier = Modifier,
    showDatePickerDialog: Boolean,
    selectedDate: String,
    datePickerState: DatePickerState,
    onShowDatePickerDialog: (boolean: Boolean) -> Unit,
    onSelectedDate: (selectedDate: String) -> Unit,
    onValueChange: (selectedDate: String) -> Unit,
    onClearFocus: () -> Unit
) {
    if (showDatePickerDialog) {
        DatePickerDialog(
            onDismissRequest = { onShowDatePickerDialog(false) },
            confirmButton = {
                ConfirmButton(
                    datePickerState,
                    onSelectedDate,
                    onShowDatePickerDialog
                )
            },
            dismissButton = null,
            shape = DatePickerDefaults.shape,
            tonalElevation = DatePickerDefaults.TonalElevation,
            colors = DatePickerDefaults.colors(),
            properties = DialogProperties(usePlatformDefaultWidth = false),
            content = { DatePicker(datePickerState) }
        )
    }
    TextField(
        value = selectedDate,
        onValueChange = { onValueChange(it) },
        modifier = modifier.setTextFieldModifier(onShowDatePickerDialog, onClearFocus),
        label = { Text(stringResource(R.string.my_store_date_picker_date)) },
        readOnly = true,
        colors = TextFieldDefaults.textFieldColors(
            disabledLabelColor = colorResource(R.color.color_900),
            unfocusedLabelColor = colorResource(R.color.color_900),
            unfocusedIndicatorColor = colorResource(R.color.color_900),
            focusedIndicatorColor = colorResource(R.color.color_900),
            textColor = colorResource(R.color.color_900),
            backgroundColor = colorResource(R.color.white)
        )
    )
}

@SuppressLint("ModifierFactoryUnreferencedReceiver", "UnnecessaryComposedModifier")
private fun Modifier.setTextFieldModifier(
    onShowDatePickerDialog: (boolean: Boolean) -> Unit,
    onClearFocus: () -> Unit
) = composed {
    Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .onFocusEvent {
            if (it.isFocused) {
                onShowDatePickerDialog(true)
                onClearFocus()
            }
        }
}