package com.example.mystore.viewmodel.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mystore.TransactionType
import com.example.mystore.model.Transaction
import kotlinx.coroutines.flow.MutableStateFlow

open class CommonViewModel : ViewModel() {
    private val _listOfSales: MutableState<List<Transaction>> =
        mutableStateOf(listOf())
    val listOfSales: MutableState<List<Transaction>> = _listOfSales

    private val _listOfPurchases: MutableState<List<Transaction>> =
        mutableStateOf(listOf())
    val listOfPurchases: MutableState<List<Transaction>> = _listOfPurchases

    private val _transactions: MutableStateFlow<List<Transaction>> = MutableStateFlow(listOf())
    val transactions: MutableStateFlow<List<Transaction>> = _transactions

    fun getSalesValue(): Double = getTotalValueByTransactionType(TransactionType.SALE)
    fun getPurchasesValue(): Double = getTotalValueByTransactionType(TransactionType.PURCHASE)

    private fun getTotalValueByTransactionType(type: TransactionType): Double {
        return transactions.value
            .filter { transaction -> transaction.transactionType == type }
            .sumOf { transaction -> transaction.transactionAmount }
    }

    fun getListOfPurchases() {
        listOfPurchases.value = transactions.value.filter {
            it.transactionType == TransactionType
                .PURCHASE
        }
    }

    fun getListOfSales() {
        listOfSales.value = transactions.value.filter {
            it.transactionType == TransactionType.SALE
        }
    }
}
