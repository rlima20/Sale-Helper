package com.example.mystore.ui.model

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import com.example.mystore.R

data class DropdownComponentProps(
    val items: List<String>,
    val isExpanded: Boolean,
    val selectedText: String,
    val textFieldSize: Size,
    val label: String,
    val modifier: Modifier = Modifier,
    val transactionDetailColors: Triple<Int, Int, Int> = Triple(
        first = R.color.color_50,
        second = R.color.color_900,
        third = R.color.color_50,
    ),
    val onOutLinedTextFieldSize: (size: Size) -> Unit = {},
    val onOutLinedTextFieldValueChanged: (String) -> Unit = {},
    val onTrailingIconClicked: () -> Unit = {},
    val onDropdownMenuDismissRequest: () -> Unit = {},
    val onDropdownMenuItemClicked: (String) -> Unit = {},
)