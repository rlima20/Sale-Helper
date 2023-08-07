package com.example.mystore.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val quantity: Int,
    val purchasePrice: Double,
    val salePrice: Double,
    val image: String,
)
