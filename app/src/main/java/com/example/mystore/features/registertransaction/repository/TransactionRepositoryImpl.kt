package com.example.mystore.features.registertransaction.repository

import com.example.mystore.features.registertransaction.datasource.listOfTransactionsLocal
import com.example.mystore.features.registertransaction.model.Transaction
import com.example.mystore.features.registertransaction.room.dao.TransactionDao
import com.example.mystore.features.registertransaction.room.entities.TransactionEntity
import kotlinx.coroutines.flow.Flow

class TransactionRepositoryImpl(
    private val transactionDao: TransactionDao,
) : TransactionRepository {
    override fun getAllTransactions(): Flow<List<TransactionEntity>> {
        return transactionDao.getAllTransactions()
    }

    override fun getAllTransactionsLocal(): List<Transaction> {
        return listOfTransactionsLocal
    }

    override suspend fun insertTransaction(transaction: TransactionEntity) {
        transactionDao.insertTransaction(transaction)
    }

    override suspend fun deleteTransaction(transaction: TransactionEntity) {
        transactionDao.deleteTransaction(transaction)
    }
}
