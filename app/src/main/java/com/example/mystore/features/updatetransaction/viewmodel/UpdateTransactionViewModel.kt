package com.example.mystore.features.updatetransaction.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystore.commons.usecase.CommonUseCase
import com.example.mystore.features.registertransaction.model.Transaction
import com.example.mystore.features.updatetransaction.usecase.UpdateTransactionUseCase
import com.example.mystore.features.updatetransaction.viewstate.UpdateTransactionScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateTransactionViewModel(
    private val dispatcherProvider: Dispatchers,
    private val updateTransactionUseCase: UpdateTransactionUseCase,
    private val commonUseCase: CommonUseCase
) : ViewModel() {

    val viewState = UpdateTransactionScreenState()

    fun updateTransaction(transaction: Transaction) {
        viewModelScope.launch(dispatcherProvider.IO) {
            updateTransactionUseCase.updateTransaction(transaction)
        }
    }

    fun setConfirmationAlert(value: Boolean) {
        viewState.showConfirmationAlert.value = value
    }

    fun setToast(value: Boolean) {
        viewState.showSuccessUpdateToast.value = value
    }

    fun getAllTransactions() {
        viewModelScope.launch(dispatcherProvider.IO) {
            commonUseCase.getAllTransactions()
        }
    }
}