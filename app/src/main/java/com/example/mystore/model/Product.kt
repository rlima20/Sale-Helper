package com.example.mystore.model

data class Product(
    val id: Long = 0,
    val title: String = "",
    val description: String = "",
    var quantity: Int = 0,
    val purchasePrice: Double = 0.0,
    val salePrice: Double = 0.0,
    val imageUrl: String = "",
)
