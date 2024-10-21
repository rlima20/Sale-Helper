package com.example.mystore.features.homescreen.model

import com.example.mystore.features.registertransaction.model.Transaction

data class TransactionProps(
    val listOfTransactions: List<Transaction> = listOf(),
    val shouldItemBeVisible: Boolean,
    val onClick: (Transaction) -> Unit = {},
    val onLongClick: (Transaction) -> Unit = {},
)