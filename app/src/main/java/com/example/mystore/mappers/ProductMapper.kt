package com.example.mystore.mappers

import com.example.mystore.model.screen.Product
import com.example.mystore.room.entities.ProductEntity

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
