package com.example.mystore.features.homescreen.model

import com.example.mystore.features.registertransaction.model.Transaction

data class TransactionSectionProps(
    val listOfTransactions: List<Transaction>,
    val shouldItemBeVisible: Boolean,
    val onEmptyStateImageClicked: (route: String, screen: String) -> Unit,
    val onEditTransactionIconClick: (Transaction) -> Unit
)
