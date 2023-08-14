package com.example.mystore.model

import com.example.mystore.model.entities.TransactionType
import java.util.Date

data class Transaction(
    val product: Product,
    val transactionType: TransactionType,
    val unitValue: Double = 0.0,
    val transactionDate: Date,
    val quantity: Int = 0,
    val transactionAmount: Double = 0.0,
)
