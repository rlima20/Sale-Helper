package com.example.mystore.features.homescreen.model

import com.example.mystore.features.registerproduct.model.Product

data class ProductSectionProps(
    val listOfProducts: List<Product>,
    val shouldItemBeVisible: Boolean,
    val onEditMode: (Boolean, Product) -> Unit,
    val onProductClick: (product: Product) -> Unit,
    val onProductDoubleClick: () -> Unit,
    val onEmptyStateImageClicked: (route: String, screen: String) -> Unit
)