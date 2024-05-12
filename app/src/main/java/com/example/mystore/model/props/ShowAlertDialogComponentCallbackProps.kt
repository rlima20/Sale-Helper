package com.example.mystore.model.props

class ShowAlertDialogComponentCallbackProps(
    val onDismissButtonClicked: () -> Unit = {},
    val onConfirmButtonClicked: () -> Unit = {},
    val onDismissRequest: () -> Unit = {},
)