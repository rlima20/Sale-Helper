package com.example.mystore.mappers

// Mapper Transaction to TransactionEntity
import com.example.mystore.model.Transaction
import com.example.mystore.room.entities.TransactionEntity

fun TransactionEntity.toTransaction(): Transaction {
    return Transaction(
        id = this.transactionId,
        transactionType = this.transactionType,
        unitValue = this.unitValue,
        transactionDate = this.transactionDate,
        quantity = this.quantity,
        transactionAmount = this.transactionAmount,
        product = this.product,
    )
}

fun Transaction.toTransactionEntity(): TransactionEntity {
    return TransactionEntity(
        transactionId = this.id,
        product = this.product,
        transactionType = this.transactionType,
        unitValue = this.unitValue,
        transactionDate = this.transactionDate,
        quantity = this.quantity,
        transactionAmount = this.transactionAmount,
    )
}
