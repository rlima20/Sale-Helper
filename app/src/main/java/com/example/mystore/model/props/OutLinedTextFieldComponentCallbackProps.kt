package com.example.mystore.model.props

import androidx.compose.runtime.Composable

class OutLinedTextFieldComponentCallbackProps(
    val onDone: () -> Unit = {},
    val onValueChanged: (String) -> Unit = {},
    val trailingIcon: @Composable () -> Unit = {},
)