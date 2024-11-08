package com.example.mystore.features.consolidatedposition.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.mystore.features.registertransaction.model.Transaction

class EditTransactionScreenState {
    val transaction: MutableState<Transaction> = mutableStateOf(Transaction())
}