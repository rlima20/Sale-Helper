package com.example.mystore.features.registertransaction.mappers

// Mapper Transaction to TransactionEntity
import com.example.mystore.features.registertransaction.datasource.listOfTransactionsLocal
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

fun List<TransactionEntity>.toListOfTransaction(): List<Transaction> {
    val listOfTransactions: MutableList<Transaction> = mutableListOf()
    forEach { transaction ->
        Transaction(
            id = transaction.transactionId,
            transactionType = transaction.transactionType,
            unitValue = transaction.unitValue,
            transactionDate = transaction.transactionDate,
            quantity = transaction.quantity,
            transactionAmount = transaction.transactionAmount,
            product = transaction.product,
        )
    }
    return listOfTransactionsLocal
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
