package com.example.mystore.features.registerproduct.mappers

import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registerproduct.room.entities.ProductEntity

// Mapper Product to ProductEntity
fun ProductEntity.toProduct(): Product {
    return Product(
        productId = this.productId,
        title = this.title,
        description = this.description,
        quantity = this.quantity,
        maxQuantityToBuy = this.maxQuantityToBuy,
        purchasePrice = this.purchasePrice,
        salePrice = this.salePrice,
        imageUrl = this.image,
    )
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
