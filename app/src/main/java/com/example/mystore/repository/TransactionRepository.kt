package com.example.mystore.repository

import com.example.mystore.dao.TransactionDao
import com.example.mystore.model.entities.TransactionEntity
import kotlinx.coroutines.flow.Flow

class TransactionRepository(transactionDao: TransactionDao) {

    val getAllTransactions: Flow<List<TransactionEntity>> = transactionDao.getAllTransactions()
}
