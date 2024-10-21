package com.example.mystore.features.registerproduct.model

import com.example.mystore.features.registerproduct.viewmodel.RegisterProductViewModel

data class RegisterProductScreenBodyProps(
    val product: Product,
    val screenWidth: Int,
    val isEditMode: Boolean = false,
    val registerProductViewModel: RegisterProductViewModel,
    val titleSelectedText: String,
    val descriptionSelectedText: String,
    val purchasePriceSelectedText: String,
    val salePriceSelectedText: String,
    val quantity: Int,
    val maxQuantityToBuy: Int,
    val showAlertDialogProductScreen: Boolean,
    val showAlertDialogImageUrl: Boolean,
    val showToastProductScreen: Boolean,
    val onNavigateToHome: () -> Unit,
    val listOfProducts: List<Product>
)