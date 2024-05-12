package com.example.mystore.model.screen

data class Product(
    val productId: Int = 0,
    val title: String = "",
    val description: String = "",
    var quantity: Int = 0,
    var maxQuantityToBuy: Int = 0,
    val purchasePrice: Double = 0.0,
    val salePrice: Double = 0.0,
    val imageUrl: String = "",
)
