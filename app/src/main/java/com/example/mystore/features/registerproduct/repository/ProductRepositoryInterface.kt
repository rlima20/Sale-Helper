package com.example.mystore.features.registerproduct.repository

import com.example.mystore.features.registerproduct.room.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepositoryInterface {
    fun getAllProducts(): Flow<List<ProductEntity>>
    suspend fun insertProduct(product: ProductEntity)
    suspend fun deleteProduct(product: ProductEntity)
    suspend fun updateProduct(product: ProductEntity)
}
