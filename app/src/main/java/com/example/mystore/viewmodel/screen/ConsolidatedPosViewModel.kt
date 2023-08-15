package com.example.mystore.viewmodel.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mystore.TransactionType
import com.example.mystore.listOfTransactions
import com.example.mystore.model.Transaction

class ConsolidatedPosViewModel : ViewModel() {

    // State of the screen
    private val _listOfSales: MutableState<List<Transaction>> =
        mutableStateOf(listOf())
    val listOfSales: MutableState<List<Transaction>> = _listOfSales

    private val _listOfPurchases: MutableState<List<Transaction>> =
        mutableStateOf(listOf())
    val listOfPurchases: MutableState<List<Transaction>> = _listOfPurchases

    val _transactions: MutableState<List<Transaction>> =
        mutableStateOf(listOf())
    val transactions: MutableState<List<Transaction>> = _transactions

    init {
        getTransactions()
        getListOfSales()
        getListOfPurchases()
    }

    // get the list of transactions
    private fun getTransactions() {
        transactions.value = listOfTransactions
    }

    // get the list of sales
    private fun getListOfSales() {
        listOfSales.value = transactions.value.filter {
            it.transactionType == TransactionType.SALE
        }
    }

    // get the list of purchases
    private fun getListOfPurchases() {
        listOfPurchases.value = transactions.value.filter {
            it.transactionType == TransactionType
                .PURCHASE
        }
    }

    private fun getTotalValueByTransactionType(type: TransactionType): Double {
        return transactions.value
            .filter { transaction -> transaction.transactionType == type }
            .sumOf { transaction -> transaction.transactionAmount }
    }

    fun getSalesValue(): Double = getTotalValueByTransactionType(TransactionType.SALE)
    fun getPurchasesValue(): Double = getTotalValueByTransactionType(TransactionType.PURCHASE)
}
