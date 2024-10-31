package com.example.mystore.ui.model

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size

class MenuComponentProps(
    val modifier: Modifier = Modifier,
    val screens: List<String>,
    val isMenuExpanded: Boolean,
    val menuDropdownWidth: Size,
    val callbacks: Callbacks
)

class Callbacks(
    val onMenuIconClicked: () -> Unit = {},
    val onDismissRequest: () -> Unit = {},
    val onDropDownMenuItemClicked: (String) -> Unit = {},
    val onChangeTextFieldSize: (size: Size) -> Unit = {},
)