package com.example.mystore.features.registertransaction.datasource

import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registertransaction.model.Transaction

class TransactionLocalDataSourceImpl() : TransactionLocalDataSource {
    override fun getAllTransactionsLocal(): MutableList<Transaction> {
        return listOfTransactionsLocal
    }
}