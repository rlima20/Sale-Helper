package com.example.mystore.features.registertransaction.model

import com.example.mystore.enums.TransactionType
import com.example.mystore.features.registerproduct.model.Product

data class Transaction(
    val id: Int = 0,
    val transactionType: TransactionType = TransactionType.SALE,
    val unitValue: Double = 0.0,
    var transactionDate: String = "",
    val quantity: Int = 0,
    val transactionAmount: Double = 0.0,
    val product: Product = Product(),
)
