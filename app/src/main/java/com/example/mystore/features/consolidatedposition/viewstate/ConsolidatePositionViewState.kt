package com.example.mystore.features.consolidatedposition.viewstate

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.mystore.features.registertransaction.model.Transaction

class ConsolidatePositionViewState {
    val transaction: MutableState<Transaction> = mutableStateOf(Transaction())
}