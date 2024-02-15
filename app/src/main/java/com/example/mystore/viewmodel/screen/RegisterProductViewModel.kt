package com.example.mystore.viewmodel.screen

import com.example.mystore.model.Product
import com.example.mystore.repository.ProductRepository
import com.example.mystore.repository.TransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterProductViewModel(
    transactionRepository: TransactionRepository,
    productRepository: ProductRepository,
) : CommonViewModel(
    transactionRepository,
    productRepository,
) {

    private val _screenWidth: MutableStateFlow<Int> = MutableStateFlow(0)
    val screenWidth: StateFlow<Int> = _screenWidth

    private val _isEditMode: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val isEditMode: StateFlow<Boolean> = _isEditMode

    private val _titleSelectedText: MutableStateFlow<String> = MutableStateFlow("")
    val titleSelectedText: StateFlow<String> = _titleSelectedText

    private val _descriptionSelectedText: MutableStateFlow<String> = MutableStateFlow("")
    val descriptionSelectedText: StateFlow<String> = _descriptionSelectedText

    private val _purchasePriceSelectedText: MutableStateFlow<String> = MutableStateFlow("")
    val purchasePriceSelectedText: StateFlow<String> = _purchasePriceSelectedText

    private val _salePriceSelectedText: MutableStateFlow<String> = MutableStateFlow("")
    val salePriceSelectedText: StateFlow<String> = _salePriceSelectedText

    private val _quantity: MutableStateFlow<Int> = MutableStateFlow(0)
    val quantity: StateFlow<Int> = _quantity

    private val _maxQuantityToBuy: MutableStateFlow<Int> = MutableStateFlow(0)
    val maxQuantityToBuy: StateFlow<Int> = _maxQuantityToBuy

    private val _showAlertDialogProductScreen: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showAlertDialogProductScreen: StateFlow<Boolean> = _showAlertDialogProductScreen

    private val _showAlertDialogImageUrl: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showAlertDialogImageUrl: StateFlow<Boolean> = _showAlertDialogImageUrl

    private val _showToastProductScreen: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showToastProductScreen: StateFlow<Boolean> = _showToastProductScreen

    private val _product: MutableStateFlow<Product> = MutableStateFlow(Product())
    val product: StateFlow<Product> = _product

    fun getProduct(): Product {
        return product.value
    }

    fun setProduct(product: Product) {
        _product.value = product
    }

    fun setImageUrl(product: Product, imageUrl: String) {
        val innerProduct = Product(
            productId = _product.value.productId,
            title = _product.value.title,
            description = _product.value.description,
            purchasePrice = _product.value.purchasePrice,
            salePrice = _product.value.salePrice,
            quantity = _product.value.quantity,
            maxQuantityToBuy = _product.value.maxQuantityToBuy,
            imageUrl = imageUrl,
        )
        _product.value = innerProduct
    }

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

    private fun clearAllStates() {
        _titleSelectedText.value = ""
        _descriptionSelectedText.value = ""
        _purchasePriceSelectedText.value = ""
        _salePriceSelectedText.value = ""
        _quantity.value = 0
        _maxQuantityToBuy.value = 0
    }

    fun saveProduct(product: Product, isEditMode: Boolean) {
        if (isEditMode) updateProduct(product) else createProduct(product)
        clearAllStates()
    }

    fun getTitleSelectedText(): String {
        return titleSelectedText.value
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

    fun getShowAlertDialogProductScreen(): Boolean {
        return showAlertDialogProductScreen.value
    }

    fun setShowAlertDialogProductScreen(showAlertDialogProductScreen: Boolean) {
        _showAlertDialogProductScreen.value = showAlertDialogProductScreen
    }

    fun getShowToastProductScreen(): Boolean {
        return showToastProductScreen.value
    }

    fun setShowToastProductScreen(showToastProductScreen: Boolean) {
        _showToastProductScreen.value = showToastProductScreen
    }

    fun getShowAlertDialogImageUrl(): Boolean {
        return showAlertDialogImageUrl.value
    }

    fun setShowAlertDialogImageUrl(showAlertDialogImageUrl: Boolean) {
        _showAlertDialogImageUrl.value = showAlertDialogImageUrl
    }
}
