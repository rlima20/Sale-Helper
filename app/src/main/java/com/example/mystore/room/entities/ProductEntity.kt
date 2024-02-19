package com.example.mystore.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val productId: Int = 0,
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "description") val description: String = "",
    @ColumnInfo(name = "product_quantity") val quantity: Int = 0,
    @ColumnInfo(name = "max_quantity_to_buy") val maxQuantityToBuy: Int = 0,
    @ColumnInfo(name = "purchase_price") val purchasePrice: Double = 0.0,
    @ColumnInfo(name = "sale_price") val salePrice: Double = 0.0,
    @ColumnInfo(name = "image") val image: String = "",
)
