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
import com.example.mystore.model.props.ColorProps
import com.example.mystore.model.props.OutLinedTextFieldComponentCallbackProps
import com.example.mystore.model.props.OutLinedTextFieldComponentVisualProps

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OutLinedTextFieldComponent(
    visualProps: OutLinedTextFieldComponentVisualProps,
    callback: OutLinedTextFieldComponentCallbackProps
) {
    OutlinedTextField(
        enabled = visualProps.enabled,
        value = visualProps.selectedText,
        onValueChange = { callback.onValueChanged(it) },
        singleLine = true,
        modifier = visualProps.modifier
            .background(colorResource(id = visualProps.transactionDetailColors.third))
            .fillMaxWidth(),
        label = {
            Text(
                color = colorResource(id = visualProps.transactionDetailColors.first),
                text = visualProps.label,
            )
        },
        trailingIcon = { callback.trailingIcon() },
        keyboardActions = KeyboardActions(
            onDone = {
                visualProps.keyboardController?.hide()
                visualProps.focusManager.clearFocus()
                callback.onDone()
            },
        ),
        keyboardOptions = visualProps.keyboardOptions,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(ColorProps().focusedBorderColor),
            unfocusedBorderColor = colorResource(ColorProps().unfocusedBorderColor),
            disabledBorderColor = colorResource(ColorProps().disabledBorderColor),
            focusedLabelColor = colorResource(ColorProps().focusedLabelColor),
            unfocusedLabelColor = colorResource(ColorProps().unfocusedLabelColor),
            disabledLabelColor = colorResource(ColorProps().disabledLabelColor),
            cursorColor = colorResource(ColorProps().cursorColor),
            textColor = colorResource(id = visualProps.transactionDetailColors.first),
            disabledTextColor = colorResource(id = visualProps.transactionDetailColors.first),
            placeholderColor = colorResource(ColorProps().placeholderColor),
        ),
        shape = RoundedCornerShape(15.dp),
        maxLines = 1,
    )
}


