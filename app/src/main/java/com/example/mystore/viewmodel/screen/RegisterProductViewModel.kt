package com.example.mystore.viewmodel.screen

import kotlinx.coroutines.flow.MutableStateFlow

class RegisterProductViewModel : CommonViewModel() {

    private val _screenWidth: MutableStateFlow<Int> = MutableStateFlow(0)
    val screenWidth: MutableStateFlow<Int> = _screenWidth

    private val _isEditMode: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isEditMode: MutableStateFlow<Boolean> = _isEditMode

    private val _titleSelectedText: MutableStateFlow<String> = MutableStateFlow("")
    val titleSelectedText: MutableStateFlow<String> = _titleSelectedText

    private val _descriptionSelectedText: MutableStateFlow<String> = MutableStateFlow("")
    val descriptionSelectedText: MutableStateFlow<String> = _descriptionSelectedText

    private val _purchasePriceSelectedText: MutableStateFlow<String> = MutableStateFlow("")
    val purchasePriceSelectedText: MutableStateFlow<String> = _purchasePriceSelectedText

    private val _salePriceSelectedText: MutableStateFlow<String> = MutableStateFlow("")
    val salePriceSelectedText: MutableStateFlow<String> = _salePriceSelectedText

    private val _quantity: MutableStateFlow<Int> = MutableStateFlow(0)
    val quantity: MutableStateFlow<Int> = _quantity

    private val _maxQuantityToBuy: MutableStateFlow<Int> = MutableStateFlow(0)
    val maxQuantityToBuy: MutableStateFlow<Int> = _maxQuantityToBuy

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

    fun clearAllStates() {
        _titleSelectedText.value = ""
        _descriptionSelectedText.value = ""
        _purchasePriceSelectedText.value = ""
        _salePriceSelectedText.value = ""
        _quantity.value = 0
        _maxQuantityToBuy.value = 0
    }

    fun setTitleSelectedText(titleSelectedText: String) {
        _titleSelectedText.value = titleSelectedText
    }

    fun setDescriptionSelectedText(descriptionSelectedText: String) {
        _descriptionSelectedText.value = descriptionSelectedText
    }

    fun setPurchasePriceSelectedText(purchasePriceSelectedText: String) {
        _purchasePriceSelectedText.value = purchasePriceSelectedText
    }

    fun setSalePriceSelectedText(salePriceSelectedText: String) {
        _salePriceSelectedText.value = salePriceSelectedText
    }

    fun setQuantity(quantity: Int) {
        _quantity.value = quantity
    }

    fun setMaxQuantityToBuy(maxQuantityToBuy: Int) {
        _maxQuantityToBuy.value = maxQuantityToBuy
    }
}
