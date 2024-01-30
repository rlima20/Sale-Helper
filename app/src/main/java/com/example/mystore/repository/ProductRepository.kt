package com.example.mystore.repository

import com.example.mystore.model.entities.ProductEntity
import com.example.mystore.room.dao.ProductDao
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val productDao: ProductDao) {

    val getAllProducts: Flow<List<ProductEntity>> = productDao.getAllProducts()
}
