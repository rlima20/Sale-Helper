package com.example.mystore.features.updatetransaction.usecase

import com.example.mystore.features.registertransaction.model.Transaction

interface UpdateTransactionUseCase {
    fun updateTransaction(transaction: Transaction)
}