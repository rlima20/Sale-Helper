package com.example.mystore.repository

import com.example.mystore.dao.ProductDao
import com.example.mystore.model.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

class ProductRepository(productDao: ProductDao) {

    val getAllProducts: Flow<List<ProductEntity>> = productDao.getAllProducts()
}
