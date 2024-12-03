package com.example.mystore.features.registertransaction.usecase

import com.example.mystore.features.registertransaction.model.Transaction
import com.example.mystore.features.registertransaction.repository.TransactionRepository
import com.example.mystore.features.registertransaction.room.entities.TransactionEntity
import kotlinx.coroutines.flow.Flow

class TransactionUseCaseImpl(
    private val transactionRepository: TransactionRepository
) : TransactionUseCase {
    override fun getAllTransactions(): Flow<List<TransactionEntity>> {
        return transactionRepository.getAllTransactions()
    }

    override fun getAllTransactionsLocal(): List<Transaction> {
        return transactionRepository.getAllTransactionsLocal()
    }

    override suspend fun insertTransaction(transaction: TransactionEntity) {
        transactionRepository.insertTransaction(transaction)
    }

    override suspend fun deleteTransaction(transaction: TransactionEntity) {
        transactionRepository.deleteTransaction(transaction)
    }
}