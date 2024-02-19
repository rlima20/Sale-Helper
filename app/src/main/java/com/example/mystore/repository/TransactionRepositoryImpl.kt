package com.example.mystore.repository

import com.example.mystore.room.dao.TransactionDao
import com.example.mystore.room.entities.TransactionEntity
import kotlinx.coroutines.flow.Flow

class TransactionRepositoryImpl(
    private val transactionDao: TransactionDao,
) : TransactionRepositoryInterface {
    override fun getAllTransactions(): Flow<List<TransactionEntity>> {
        return transactionDao.getAllTransactions()
    }

    override suspend fun insertTransaction(transaction: TransactionEntity) {
        transactionDao.insertTransaction(transaction)
    }

    override suspend fun deleteTransaction(transaction: TransactionEntity) {
        transactionDao.deleteTransaction(transaction)
    }

    override suspend fun updateTransaction(transaction: TransactionEntity) {
        transactionDao.updateTransaction(transaction)
    }
}
