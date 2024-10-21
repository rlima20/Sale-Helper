package com.example.mystore.features.registerproduct.datasource.local

import com.example.mystore.features.registerproduct.model.Product

interface ProductLocalDataSource {
    fun getAllProductsLocal(): MutableList<Product>
}