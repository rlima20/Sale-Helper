package com.example.mystore.viewmodel.global

import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import com.example.mystore.AppApplication
import com.example.mystore.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyStoreViewModel : ViewModel() {

    private val application = AppApplication.instance

    // DropdownMenu variables
    private var _screenTitle: MutableStateFlow<String> = MutableStateFlow(
        application.getString(R.string.my_store_home),
    )
    val screenTitle: StateFlow<String> = _screenTitle

    private val _isMenuExpanded: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isMenuExpanded: StateFlow<Boolean> = _isMenuExpanded

    private val _textFieldSize: MutableStateFlow<Size> = MutableStateFlow(Size.Zero)
    val textFieldSize: StateFlow<Size> = _textFieldSize

    private val _shouldItemBeVisible: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val shouldItemBeVisible: StateFlow<Boolean> = _shouldItemBeVisible

    fun setScreenTitle(title: String) {
        _screenTitle.value = title
    }

    fun expandMenu(isExpanded: Boolean) {
        _isMenuExpanded.value = isExpanded
    }

    fun setTextFieldSize(size: Size) {
        _textFieldSize.value = size
    }

    fun setValueVisibility(shouldItemBeVisible: Boolean) {
        _shouldItemBeVisible.value = shouldItemBeVisible
    }
}
