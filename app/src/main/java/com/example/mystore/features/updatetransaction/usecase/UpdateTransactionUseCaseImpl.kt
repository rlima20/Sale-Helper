package com.example.mystore.features.updatetransaction.usecase

import com.example.mystore.features.registertransaction.mappers.toTransactionEntity
import com.example.mystore.features.registertransaction.model.Transaction
import com.example.mystore.features.updatetransaction.repository.UpdateTransactionRepository

class UpdateTransactionUseCaseImpl(
    val repository: UpdateTransactionRepository
) : UpdateTransactionUseCase {
    override fun updateTransaction(transaction: Transaction) {
        repository.updateTransaction(transaction.toTransactionEntity())
    }
}