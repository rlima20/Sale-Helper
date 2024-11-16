package com.example.mystore.features.updatetransaction.viewstate

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.mystore.enums.TransactionType
import com.example.mystore.features.registertransaction.model.Transaction
import com.example.mystore.features.updatetransaction.viewmodel.EMPTY_STRING
import kotlinx.coroutines.flow.MutableStateFlow

class UpdateTransactionScreenState {
    val transaction: MutableState<Transaction> = mutableStateOf(Transaction())



//    class UpdateTransactionViewState {
//        val productName: MutableStateFlow<String> = MutableStateFlow(EMPTY_STRING)
//        val transactionType: TransactionType = TransactionType.SALE
//        val transactionDate: MutableStateFlow<String> = MutableStateFlow(EMPTY_STRING)
//        val transactionUnitValue: MutableStateFlow<Double> = MutableStateFlow(0.0)
//        val transactionQuantity: MutableStateFlow<Int> = MutableStateFlow(0)
//    }
}