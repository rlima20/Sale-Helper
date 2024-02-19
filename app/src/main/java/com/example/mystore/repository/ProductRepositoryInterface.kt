package com.example.mystore.repository

import com.example.mystore.room.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepositoryInterface {
    fun getAllProducts(): Flow<List<ProductEntity>>
    suspend fun insertProduct(product: ProductEntity)
    suspend fun deleteProduct(product: ProductEntity)
    suspend fun updateProduct(product: ProductEntity)
}
