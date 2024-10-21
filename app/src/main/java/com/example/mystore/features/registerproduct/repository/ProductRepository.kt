package com.example.mystore.features.registerproduct.repository

import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registerproduct.datasource.room.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getAllProducts(): Flow<List<ProductEntity>>
    fun getAllProductsLocal(): List<Product>
    suspend fun insertProduct(product: ProductEntity)
    suspend fun deleteProduct(product: ProductEntity)
    suspend fun updateProduct(product: ProductEntity)
}
