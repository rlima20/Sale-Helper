package com.example.mystore.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mystore.listOfTransactions
import com.example.mystore.model.Product
import com.example.mystore.model.TransactionType
import java.util.Date

class ConsolidatedPosViewModel : ViewModel() {

    fun getTransactions() = listOfTransactions

    class Transaction(
        val product: Product,
        val transactionType: TransactionType,
        val unitValue: Double = 0.0,
        val transactionDate: Date,
        val quantity: Int = 0,
        val transactionAmount: Double = 0.0,
    )

    fun getSales(): List<Transaction> {
        return listOfTransactions.filter { it.transactionType == TransactionType.SALE }
    }

    fun getPurchases(): List<Transaction> {
        return listOfTransactions.filter { it.transactionType == TransactionType.PURCHASE }
    }
}
