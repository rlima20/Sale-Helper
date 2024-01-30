package com.example.mystore.repository

import com.example.mystore.model.entities.TransactionEntity
import com.example.mystore.room.dao.TransactionDao
import kotlinx.coroutines.flow.Flow

class TransactionRepository(transactionDao: TransactionDao) {

    val getAllTransactions: Flow<List<TransactionEntity>> = transactionDao.getAllTransactions()
}
