package com.example.mystore.features.homescreen.model

import com.example.mystore.features.registerproduct.model.Product

data class DisplayAlertDialogProductDeleteConfirmationProps(
    val showAlertDialogHomeScreenProduct: Boolean,
    val productToastMessage: String,
    val product: Product
)