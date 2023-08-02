package com.example.salehelper.viewmodel

import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class SaleHelperViewModel : ViewModel() {

    // DropdownMenu variables
    private var _screenTitle: MutableStateFlow<String> = MutableStateFlow("Home")
    val screenTitle: MutableStateFlow<String> = _screenTitle

    private val _isMenuExpanded: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isMenuExpanded: MutableStateFlow<Boolean> = _isMenuExpanded

    private val _textFieldSize: MutableStateFlow<Size> = MutableStateFlow(Size.Zero)
    val textFieldSize: MutableStateFlow<Size> = _textFieldSize

    private val _shouldItemBeVisible: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val shouldItemBeVisible: MutableStateFlow<Boolean> = _shouldItemBeVisible

    fun setScreenTitle(title: String) {
        _screenTitle.value = title
    }

    fun setIsMenuExpanded(isExpanded: Boolean) {
        _isMenuExpanded.value = isExpanded
    }

    fun setTextFieldSize(size: Size) {
        _textFieldSize.value = size
    }

    fun setShouldItemBeVisible(shouldItemBeVisible: Boolean) {
        _shouldItemBeVisible.value = shouldItemBeVisible
    }
}
