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
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.mystore.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OutLinedTextFieldComponent(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    selectedText: String = "",
    label: String = "",
    transactionDetailColors: Triple<Int, Int, Int>,
    keyboardController: SoftwareKeyboardController? = null,
    focusManager: FocusManager,
    onValueChanged: (String) -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
) {
    OutlinedTextField(
        enabled = enabled,
        value = selectedText,
        onValueChange = { onValueChanged(it) },
        singleLine = true,
        modifier = modifier
            .background(colorResource(id = transactionDetailColors.second))
            .fillMaxWidth(),
        label = {
            Text(
                color = colorResource(id = transactionDetailColors.first),
                text = label,
            )
        },
        trailingIcon = { trailingIcon() },
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            },
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.color_50),
            unfocusedBorderColor = colorResource(id = R.color.color_100),
            disabledBorderColor = colorResource(id = R.color.color_200),
            focusedLabelColor = colorResource(id = R.color.color_50),
            unfocusedLabelColor = colorResource(id = R.color.color_500),
            disabledLabelColor = colorResource(id = R.color.color_50),
            cursorColor = colorResource(id = R.color.color_50),
            textColor = colorResource(id = R.color.color_50),
            disabledTextColor = colorResource(id = transactionDetailColors.third),
            placeholderColor = colorResource(id = R.color.color_50),
        ),
        shape = RoundedCornerShape(15.dp),
        maxLines = 1,
    )
}
