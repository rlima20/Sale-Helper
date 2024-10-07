package com.example.mystore.features.registertransaction.viewstate

import com.example.mystore.enums.TransactionType
import com.example.mystore.features.registertransaction.model.Transaction
import kotlinx.coroutines.flow.MutableStateFlow

class RegisterTransactionViewState {
    var listOfTransactionType: MutableStateFlow<List<TransactionType>> =
        MutableStateFlow(listOf(TransactionType.SALE, TransactionType.PURCHASE))
    val quantity: MutableStateFlow<Int> = MutableStateFlow(1)
    val maxQuantity: MutableStateFlow<Int> = MutableStateFlow(1)
    val screenWidth: MutableStateFlow<Int> = MutableStateFlow(0)
    val totalValue: MutableStateFlow<Double> = MutableStateFlow(0.0)
    val transactionValue: MutableStateFlow<Transaction> = MutableStateFlow(Transaction())
    val showAlertDialogOnRegisterTransaction: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val showToast: MutableStateFlow<Boolean> = MutableStateFlow(false)
}