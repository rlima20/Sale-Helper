package com.example.mystore.ui.components.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.mystore.R
import com.example.mystore.ui.model.OutLinedTextFieldComponentProps

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OutLinedTextFieldComponent(
    outLinedTextFieldComponentProps: OutLinedTextFieldComponentProps
) {
    with(outLinedTextFieldComponentProps) {
        OutlinedTextField(
            enabled = appearance.enabled,
            value = selectedText,
            onValueChange = { callbacks.onValueChanged(it) },
            singleLine = true,
            modifier = appearance.modifier
                .background(colorResource(id = appearance.transactionDetailColors.third))
                .fillMaxWidth(),
            label = { SetLabel() },
            trailingIcon = { callbacks.trailingIcon() },
            keyboardActions = createKeyboardActions(),
            keyboardOptions = appearance.keyboardOptions,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.color_50),
                unfocusedBorderColor = colorResource(id = R.color.color_100),
                disabledBorderColor = colorResource(id = R.color.color_200),
                focusedLabelColor = colorResource(id = R.color.color_50),
                unfocusedLabelColor = colorResource(id = R.color.color_500),
                disabledLabelColor = colorResource(id = R.color.color_50),
                cursorColor = colorResource(id = R.color.color_50),
                textColor = colorResource(id = appearance.transactionDetailColors.first),
                disabledTextColor = colorResource(id = appearance.transactionDetailColors.first),
                placeholderColor = colorResource(id = R.color.color_50),
            ),
            shape = RoundedCornerShape(15.dp),
            maxLines = 1,
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun OutLinedTextFieldComponentProps.createKeyboardActions() =
    KeyboardActions(
        onDone = {
            keyboardController?.hide()
            focusManager.clearFocus()
            callbacks.onDone()
        },
    )

@Composable
private fun OutLinedTextFieldComponentProps.SetLabel() {
    Text(
        color = colorResource(id = appearance.transactionDetailColors.first),
        text = appearance.label,
    )
}
