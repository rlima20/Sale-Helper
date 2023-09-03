package com.example.mystore.viewmodel.screen

import kotlinx.coroutines.flow.MutableStateFlow

class RegisterProductViewModel : CommonViewModel() {

    private val _screenWidth: MutableStateFlow<Int> = MutableStateFlow(0)
    val screenWidth: MutableStateFlow<Int> = _screenWidth

    private val _isEditMode: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isEditMode: MutableStateFlow<Boolean> = _isEditMode

    fun getScreenWidth(): Int {
        return screenWidth.value
    }

    fun setScreenWidth(screenWidth: Int) {
        _screenWidth.value = screenWidth
    }

    fun getIsEditMode(): Boolean {
        return isEditMode.value
    }

    fun setIsEditMode(isEditMode: Boolean) {
        _isEditMode.value = isEditMode
    }
}
