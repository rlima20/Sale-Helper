package com.example.mystore.repository

import com.example.mystore.room.entities.TransactionEntity
import kotlinx.coroutines.flow.Flow

interface TransactionRepositoryInterface {
    fun getAllTransactions(): Flow<List<TransactionEntity>>
    suspend fun insertTransaction(transaction: TransactionEntity)
    suspend fun deleteTransaction(transaction: TransactionEntity)
    suspend fun updateTransaction(transaction: TransactionEntity)
}
