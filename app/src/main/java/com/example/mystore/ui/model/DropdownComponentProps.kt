package com.example.mystore.ui.model

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import com.example.mystore.R

data class DropdownComponentProps(
    val appearance: DropdownAppearance,
    val state: DropdownState,
    val items: DropdownItem,
    val callbacks: DropdownCallbacks = DropdownCallbacks()
)

data class DropdownAppearance(
    val textFieldSize: Size,
    val label: String,
    val modifier: Modifier = Modifier,
    val colors: DropdownColors = DropdownColors()
)

data class DropdownState(
    val isExpanded: Boolean,
    val selectedText: String
)

data class DropdownItem(
    val items: List<String>
)

data class DropdownColors(
    val transactionDetailColors: Triple<Int, Int, Int> = Triple(
        first = R.color.color_50,
        second = R.color.color_900,
        third = R.color.color_50,
    )
)

data class DropdownCallbacks(
    val onDropdownMenuDismissRequest: () -> Unit = {},
    val onDropdownMenuItemClicked: (String) -> Unit = {}
)

