package com.example.mystore.mappers

import com.example.mystore.model.Product
import com.example.mystore.room.entities.ProductEntity

// Mapper Product to ProductEntity
fun ProductEntity.toProduct(): Product {
    return Product(
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
        title = this.title,
        description = this.description,
        quantity = this.quantity,
        maxQuantityToBuy = this.maxQuantityToBuy,
        purchasePrice = this.purchasePrice,
        salePrice = this.salePrice,
        image = this.imageUrl,
    )
}
