package com.example.mystore.viewmodel.screen

import kotlinx.coroutines.flow.MutableStateFlow

class RegisterProductViewModel : CommonViewModel() {

    private val _screenWidth: MutableStateFlow<Int> = MutableStateFlow(0)
    val screenWidth: MutableStateFlow<Int> = _screenWidth

    fun getScreenWidth(): Int {
        return screenWidth.value
    }

    fun setScreenWidth(screenWidth: Int) {
        _screenWidth.value = screenWidth
    }
}
