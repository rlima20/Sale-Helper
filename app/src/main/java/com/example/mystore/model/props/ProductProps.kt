package com.example.mystore.model.props

import com.example.mystore.model.screen.Product

data class ProductProps(
    val product: Product,
    val onProductClick: (product: Product) -> Unit = {},
    val onProductDoubleClick: () -> Unit = {},
)
