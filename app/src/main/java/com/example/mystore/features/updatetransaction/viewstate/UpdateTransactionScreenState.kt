package com.example.mystore.features.updatetransaction.viewstate

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.mystore.enums.TransactionType
import com.example.mystore.features.registertransaction.model.Transaction

class UpdateTransactionScreenState {
    val transaction: MutableState<Transaction> = mutableStateOf(Transaction())
    val showConfirmationAlert: MutableState<Boolean> = mutableStateOf(false)
    val showSuccessUpdateToast: MutableState<Boolean> = mutableStateOf(false)
    var listOfTransactionType: List<TransactionType> =
        listOf(
            TransactionType.SALE,
            TransactionType.PURCHASE
        )
}