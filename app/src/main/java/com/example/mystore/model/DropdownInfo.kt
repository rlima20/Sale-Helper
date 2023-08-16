package com.example.mystore.model

import androidx.compose.ui.geometry.Size

data class DropdownInfo(
    val selectedText: String = "",
    val isExpanded: Boolean = false,
    val textFieldSize: Size = Size.Zero,
) {
    fun setExpanded(isExpanded: Boolean): DropdownInfo {
        return this.copy(isExpanded = isExpanded)
    }

    fun setSelectedText(selectedText: String): DropdownInfo {
        return this.copy(selectedText = selectedText)
    }

    fun setTextFieldSize(textFieldSize: Size): DropdownInfo {
        return this.copy(textFieldSize = textFieldSize)
    }
}
