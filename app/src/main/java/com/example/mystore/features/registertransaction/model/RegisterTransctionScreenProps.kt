package com.example.mystore.features.registertransaction.model

import com.example.mystore.features.registertransaction.viewmodel.RegisterTransactionViewModel

data class RegisterTransactionScreenProps(
    val registerTransactionViewModel: RegisterTransactionViewModel,
    val shouldItemBeVisible: Boolean,
    val clearAllStates: Boolean = false,
    val onClearAllStates: (Boolean) -> Unit = {},
)