package com.example.mystore.features.registerproduct.mappers

import com.example.mystore.features.registerproduct.datasource.room.entities.ProductEntity
import com.example.mystore.features.registerproduct.model.Product

fun List<ProductEntity>.toListOfProduct(): List<Product> {
    val listOfProducts: MutableList<Product> = mutableListOf()
    forEach { product ->
        listOfProducts.add(
            Product(
                productId = product.productId,
                title = product.title,
                description = product.description,
                quantity = product.quantity,
                maxQuantityToBuy = product.maxQuantityToBuy,
                purchasePrice = product.purchasePrice,
                salePrice = product.salePrice,
                imageUrl = product.image,
            )
        )
    }
    return listOfProducts
}

fun Product.toProductEntity(): ProductEntity {
    return ProductEntity(
        productId = this.productId,
        title = this.title,
        description = this.description,
        quantity = this.quantity,
        maxQuantityToBuy = this.maxQuantityToBuy,
        purchasePrice = this.purchasePrice,
        salePrice = this.salePrice,
        image = this.imageUrl,
    )
}
