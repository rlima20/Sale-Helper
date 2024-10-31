package com.example.mystore.features.registerproduct.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.mystore.commons.usecase.CommonUseCase
import com.example.mystore.commons.viewmodel.CommonViewModel
import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registerproduct.viewstate.RegisterProductViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterProductViewModel(
    commonUseCase: CommonUseCase,
    private val dispatcherProvider: Dispatchers
) : CommonViewModel(
    commonUseCase,
    dispatcherProvider
) {

    init{
        getAllProducts()
    }

    val registerProductViewState = RegisterProductViewState()

    fun setProduct(product: Product) {
        registerProductViewState.product.value = product
    }

    fun setImageUrl(imageUrl: String) {
        with(registerProductViewState.product.value) {
            val innerProduct = Product(
                productId = productId,
                title = title,
                description = description,
                purchasePrice = purchasePrice,
                salePrice = salePrice,
                quantity = quantity,
                maxQuantityToBuy = maxQuantityToBuy,
                imageUrl = imageUrl,
            )
            registerProductViewState.product.value = innerProduct
        }
    }

    fun setScreenWidth(screenWidth: Int) {
        registerProductViewState.screenWidth.value = screenWidth
    }

    fun setIsEditMode(isEditMode: Boolean) {
        registerProductViewState.isEditMode.value = isEditMode
    }

    fun clearAllStates() {
        registerProductViewState.titleSelectedText.value = EMPTY_STRING
        registerProductViewState.descriptionSelectedText.value = EMPTY_STRING
        registerProductViewState.purchasePriceSelectedText.value = EMPTY_STRING
        registerProductViewState.salePriceSelectedText.value = EMPTY_STRING
        registerProductViewState.quantity.value = ZERO
        registerProductViewState.maxQuantityToBuy.value = ZERO
    }

    fun saveProduct(product: Product, isEditMode: Boolean) {
        viewModelScope.launch(dispatcherProvider.IO) {
            if (isEditMode) updateProduct(product) else createProduct(product)
            getAllProducts()
            clearAllStates()
        }
    }

    fun setTitleSelectedText(titleSelectedText: String) {
        registerProductViewState.titleSelectedText.value = titleSelectedText
    }

    fun setDescriptionSelectedText(descriptionSelectedText: String) {
        registerProductViewState.descriptionSelectedText.value = descriptionSelectedText
    }

    fun setPurchasePriceSelectedText(purchasePriceSelectedText: String) {
        registerProductViewState.purchasePriceSelectedText.value = purchasePriceSelectedText
    }

    fun setSalePriceSelectedText(salePriceSelectedText: String) {
        registerProductViewState.salePriceSelectedText.value = salePriceSelectedText
    }

    fun setQuantity(quantity: Int) {
        registerProductViewState.quantity.value = quantity
    }

    fun setMaxQuantityToBuy(maxQuantityToBuy: Int) {
        registerProductViewState.maxQuantityToBuy.value = maxQuantityToBuy
    }

    fun setShowAlertDialogProductScreen(showAlertDialogProductScreen: Boolean) {
        registerProductViewState.showAlertDialogProductScreen.value = showAlertDialogProductScreen
    }

    fun setShowToastProductScreen(showToastProductScreen: Boolean) {
        registerProductViewState.showToastProductScreen.value = showToastProductScreen
    }

    fun setShowAlertDialogImageUrl(showAlertDialogImageUrl: Boolean) {
        registerProductViewState.showAlertDialogImageUrl.value = showAlertDialogImageUrl
    }

    companion object{
        private const val EMPTY_STRING = ""
        private const val ZERO = 0
    }
}
