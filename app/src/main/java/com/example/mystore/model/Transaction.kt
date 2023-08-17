package com.example.mystore.model

import com.example.mystore.TransactionType
import java.util.Date

data class Transaction(
    val product: Product = Product(),
    val transactionType: TransactionType = TransactionType.SALE,
    val unitValue: Double = 0.0,
    val transactionDate: Date = Date(),
    val quantity: Int = 0,
    val transactionAmount: Double = 0.0,
)
