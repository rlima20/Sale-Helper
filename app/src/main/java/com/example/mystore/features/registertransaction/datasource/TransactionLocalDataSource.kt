package com.example.mystore.features.registertransaction.datasource

import com.example.mystore.features.registertransaction.model.Transaction

interface TransactionLocalDataSource {
    fun getAllTransactionsLocal(): MutableList<Transaction>
}