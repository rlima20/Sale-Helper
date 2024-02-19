package com.example.mystore.model

import com.example.mystore.TransactionType

data class Transaction(
    val id: Int = 0,
    val transactionType: TransactionType = TransactionType.SALE,
    val unitValue: Double = 0.0,
    var transactionDate: String = "",
    val quantity: Int = 0,
    val transactionAmount: Double = 0.0,
    val product: Product = Product(),
)
