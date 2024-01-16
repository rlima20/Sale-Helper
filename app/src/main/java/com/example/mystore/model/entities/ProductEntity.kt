package com.example.mystore.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String = "",
    val description: String = "",
    val quantity: Int = 0,
    val maxQuantityToBuy: Int = 0,
    val purchasePrice: Double = 0.0,
    val salePrice: Double = 0.0,
    val image: String = "",
)
