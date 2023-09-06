package com.example.mystore.model

data class RegisterProductProps(
    val screenWidth: Int,
    val titleSelectedText: String,
    val descriptionSelectedText: String,
    val purchasePriceSelectedText: String,
    val salePriceSelectedText: String,
    val quantity: Int,
    val maxQuantityToBuy: Int,
    val showAlertDialogProductScreen: Boolean,
    val showToastProductScreen: Boolean,
)
