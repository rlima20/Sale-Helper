package com.example.mystore.features.updatetransaction.repository

import com.example.mystore.features.registertransaction.room.entities.TransactionEntity
import com.example.mystore.features.updatetransaction.dao.UpdateTransactionDao

class UpdateTransactionRepositoryImpl(
    private val updateTransactionDao: UpdateTransactionDao
) : UpdateTransactionRepository {
    override fun updateTransaction(transaction: TransactionEntity) {
        updateTransactionDao.updateTransaction(transaction)
    }
}