package com.example.mystore.features.homescreen.model

import com.example.mystore.features.registertransaction.model.Transaction

data class DisplayAlertDialogTransactionDeleteConfirmationProps(
    val showAlertDialogHomeScreen: Boolean,
    val transactionToastMessage: String,
    val transaction: Transaction
)