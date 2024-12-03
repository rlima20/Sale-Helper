package com.example.mystore.features.registertransaction.usecase

import com.example.mystore.features.registertransaction.model.Transaction
import com.example.mystore.features.registertransaction.room.entities.TransactionEntity
import kotlinx.coroutines.flow.Flow

interface TransactionUseCase {
    fun getAllTransactions(): Flow<List<TransactionEntity>>
    fun getAllTransactionsLocal(): List<Transaction>
    suspend fun insertTransaction(transaction: TransactionEntity)
    suspend fun deleteTransaction(transaction: TransactionEntity)
}