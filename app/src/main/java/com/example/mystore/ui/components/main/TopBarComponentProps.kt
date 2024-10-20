package com.example.mystore.ui.components.main

import androidx.compose.ui.geometry.Size

data class TopBarComponentProps(
    var screenTitle: String,
    val isIconLined: Boolean,
    val isMenuExpanded: Boolean,
    val dropdownMenuWidth: Size,
    val isIconVisible: Boolean = true,
    val onIconVisibilityClicked: () -> Unit,
    val onMenuIconClicked: () -> Unit,
    val onDismissRequest: () -> Unit,
    val onDropDownMenuItemClicked: (screen: String) -> Unit,
    val onChangeDropdownMenuWidth: (size: Size) -> Unit,
)