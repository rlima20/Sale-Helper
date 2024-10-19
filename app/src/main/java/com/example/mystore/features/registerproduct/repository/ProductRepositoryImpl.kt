package com.example.mystore.features.registerproduct.repository

import com.example.mystore.features.registerproduct.datasource.local.ProductLocalDataSource
import com.example.mystore.features.registerproduct.datasource.room.dao.ProductDao
import com.example.mystore.features.registerproduct.datasource.room.entities.ProductEntity
import com.example.mystore.features.registerproduct.model.Product
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl(
    private val productDataSource: ProductDao,
    private val productLocalDataSource: ProductLocalDataSource
) : ProductRepository {

    override fun getAllProducts(): Flow<List<ProductEntity>> {
        return productDataSource.getAllProducts()
    }

    override fun getAllProductsLocal(): List<Product> = productLocalDataSource.getAllProductsLocal()

    override suspend fun insertProduct(product: ProductEntity) {
        productDataSource.insertProduct(product)
    }

    override suspend fun deleteProduct(product: ProductEntity) {
        productDataSource.deleteProduct(product)
    }

    override suspend fun updateProduct(product: ProductEntity) {
        productDataSource.updateProduct(product)
    }
}
