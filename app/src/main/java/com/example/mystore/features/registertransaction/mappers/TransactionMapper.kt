package com.example.mystore.features.registertransaction.mappers

// Mapper Transaction to TransactionEntity
import com.example.mystore.features.registertransaction.model.Transaction
import com.example.mystore.features.registertransaction.room.entities.TransactionEntity

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
