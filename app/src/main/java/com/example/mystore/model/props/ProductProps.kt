package com.example.mystore.model.props

import com.example.mystore.model.Product

data class ProductProps(
    val product: Product,
    val onProductClick: (product: Product) -> Unit = {},
    val onProductDoubleClick: () -> Unit = {},
)
