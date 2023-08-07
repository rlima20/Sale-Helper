package com.example.mystore.model

data class Product(
    val id: Long = 0,
    val title: String,
    val description: String,
    val quantity: Int,
    val purchasePrice: Double,
    val salePrice: Double,
    val imageUrl: String,
)
