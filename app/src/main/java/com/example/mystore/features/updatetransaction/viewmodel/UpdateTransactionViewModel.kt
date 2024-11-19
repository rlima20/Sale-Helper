package com.example.mystore.features.updatetransaction.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystore.enums.TransactionType
import com.example.mystore.features.registertransaction.model.Transaction
import com.example.mystore.features.updatetransaction.usecase.UpdateTransactionUseCase
import com.example.mystore.features.updatetransaction.viewstate.UpdateTransactionScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateTransactionViewModel(
    private val dispatcherProvider: Dispatchers,
    private val updateTransactionUseCase: UpdateTransactionUseCase
) : ViewModel() {

    val viewState = UpdateTransactionScreenState()

    fun updateTransaction(transaction: Transaction) {
        viewModelScope.launch(dispatcherProvider.IO) {
            updateTransactionUseCase.updateTransaction(transaction)
        }
    }
}



const val EMPTY_STRING = ""