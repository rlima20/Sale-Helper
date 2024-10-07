package com.example.mystore.features.registerproduct.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.mystore.commons.usecase.CommonUseCase
import com.example.mystore.commons.viewmodel.CommonViewModel
import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registerproduct.viewstate.RegisterProductViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterProductViewModel(
    private val commonUseCase: CommonUseCase,
    private val dispatcherProvider: Dispatchers
) : CommonViewModel(
    commonUseCase,
    dispatcherProvider
) {

    val registerProductViewState = RegisterProductViewState()

    fun getProduct(): Product {
        return registerProductViewState.product.value
    }

    fun setProduct(product: Product) {
        registerProductViewState.product.value = product
    }

    fun setImageUrl(product: Product, imageUrl: String) {
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

    fun getScreenWidth(): Int {
        return registerProductViewState.screenWidth.value
    }

    fun setScreenWidth(screenWidth: Int) {
        registerProductViewState.screenWidth.value = screenWidth
    }

    fun getIsEditMode(): Boolean {
        return registerProductViewState.isEditMode.value
    }

    fun setIsEditMode(isEditMode: Boolean) {
        registerProductViewState.isEditMode.value = isEditMode
    }

    private fun clearAllStates() {
        registerProductViewState.titleSelectedText.value = ""
        registerProductViewState.descriptionSelectedText.value = ""
        registerProductViewState.purchasePriceSelectedText.value = ""
        registerProductViewState.salePriceSelectedText.value = ""
        registerProductViewState.quantity.value = 0
        registerProductViewState.maxQuantityToBuy.value = 0
    }

    fun saveProduct(product: Product, isEditMode: Boolean) {
        viewModelScope.launch(dispatcherProvider.IO) {
            if (isEditMode) updateProduct(product) else createProduct(product)
            getListOfProducts()
            clearAllStates()
        }
    }

    fun getTitleSelectedText(): String {
        return registerProductViewState.titleSelectedText.value
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

    fun getShowAlertDialogProductScreen(): Boolean {
        return registerProductViewState.showAlertDialogProductScreen.value
    }

    fun setShowAlertDialogProductScreen(showAlertDialogProductScreen: Boolean) {
        registerProductViewState.showAlertDialogProductScreen.value = showAlertDialogProductScreen
    }

    fun getShowToastProductScreen(): Boolean {
        return registerProductViewState.showToastProductScreen.value
    }

    fun setShowToastProductScreen(showToastProductScreen: Boolean) {
        registerProductViewState.showToastProductScreen.value = showToastProductScreen
    }

    fun getShowAlertDialogImageUrl(): Boolean {
        return registerProductViewState.showAlertDialogImageUrl.value
    }

    fun setShowAlertDialogImageUrl(showAlertDialogImageUrl: Boolean) {
        registerProductViewState.showAlertDialogImageUrl.value = showAlertDialogImageUrl
    }
}
