package com.example.mystore.features.registerproduct.viewstate

import com.example.mystore.features.registerproduct.model.Product
import kotlinx.coroutines.flow.MutableStateFlow

class RegisterProductViewState {
    val screenWidth: MutableStateFlow<Int> = MutableStateFlow(0)
    val isEditMode: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val titleSelectedText: MutableStateFlow<String> = MutableStateFlow("")
    val descriptionSelectedText: MutableStateFlow<String> = MutableStateFlow("")
    val purchasePriceSelectedText: MutableStateFlow<String> = MutableStateFlow("")
    val salePriceSelectedText: MutableStateFlow<String> = MutableStateFlow("")
    val quantity: MutableStateFlow<Int> = MutableStateFlow(0)
    val maxQuantityToBuy: MutableStateFlow<Int> = MutableStateFlow(0)
    val showAlertDialogProductScreen: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showAlertDialogImageUrl: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showToastProductScreen: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val product: MutableStateFlow<Product> = MutableStateFlow(Product())
}