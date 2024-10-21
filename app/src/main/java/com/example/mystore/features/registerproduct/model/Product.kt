package com.example.mystore.features.registerproduct.model

data class Product(
    var productId: Int = 0,
    val title: String = "",
    val description: String = "",
    var quantity: Int = 0,
    var maxQuantityToBuy: Int = 0,
    val purchasePrice: Double = 0.0,
    val salePrice: Double = 0.0,
    val imageUrl: String = "",
)
