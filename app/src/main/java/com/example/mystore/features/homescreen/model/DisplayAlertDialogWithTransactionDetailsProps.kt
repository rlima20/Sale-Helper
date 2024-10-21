package com.example.mystore.features.homescreen.model

import com.example.mystore.features.registertransaction.model.Transaction

data class DisplayAlertDialogWithTransactionDetailsProps(
    val showAlertDialogTransactionDetail: Boolean,
    val transaction: Transaction,
    val shouldItemBeVisible: Boolean
)