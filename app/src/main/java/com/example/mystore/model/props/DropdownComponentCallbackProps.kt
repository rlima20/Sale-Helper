package com.example.mystore.model.props

import androidx.compose.ui.geometry.Size

class DropdownComponentCallbackProps(
    val onOutLinedTextFieldSize: (size: Size) -> Unit = {},
    val onOutLinedTextFieldValueChanged: (String) -> Unit = {},
    val onTrailingIconClicked: () -> Unit = {},
    val onDropdownMenuDismissRequest: () -> Unit = {},
    val onDropdownMenuItemClicked: (String) -> Unit = {},
)