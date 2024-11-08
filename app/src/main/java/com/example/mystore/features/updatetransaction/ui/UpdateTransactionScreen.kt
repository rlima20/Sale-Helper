package com.example.mystore.features.updatetransaction.ui

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.mystore.features.registertransaction.model.Transaction

@Composable
fun UpdateTransactionScreen(
    transactionState: Transaction,
    onUpdateTransaction: (transaction: Transaction) -> Unit
) {
    Button(onClick = {
        onUpdateTransaction(
            Transaction(
                id = transactionState.id,
                transactionType = transactionState.transactionType,
                unitValue = 1234.00,
                transactionDate = transactionState.transactionDate,
                quantity = transactionState.quantity,
                transactionAmount = transactionState.transactionAmount,
                product = transactionState.product,
            )
        )
    }) {
        Text(text = "Alterar transação")
    }
}