package com.example.mystore.features.registerproduct.datasource.local

import com.example.mystore.features.registerproduct.model.Product

class ProductLocalDataSourceImpl : ProductLocalDataSource {
    override fun getAllProductsLocal(): MutableList<Product> {
        return listOfProductsLocal
    }
}