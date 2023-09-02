package com.example.mystore.viewmodel.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mystore.States
import com.example.mystore.TransactionType
import com.example.mystore.listOfProductsLocal
import com.example.mystore.listOfTransactions
import com.example.mystore.model.Product
import com.example.mystore.model.Transaction
import kotlinx.coroutines.flow.MutableStateFlow

open class CommonViewModel : ViewModel() {

    private var _listOfProducts: MutableStateFlow<MutableList<Product>> =
        MutableStateFlow(mutableListOf())
    val listOfProducts: MutableStateFlow<MutableList<Product>> = _listOfProducts

    private val _listOfSales: MutableState<List<Transaction>> =
        mutableStateOf(listOf())
    val listOfSales: MutableState<List<Transaction>> = _listOfSales

    private val _listOfPurchases: MutableState<List<Transaction>> =
        mutableStateOf(listOf())
    val listOfPurchases: MutableState<List<Transaction>> = _listOfPurchases

    private val _transactions: MutableStateFlow<MutableList<Transaction>> =
        MutableStateFlow(listOfTransactions)
    val transactions: MutableStateFlow<MutableList<Transaction>> = _transactions

    private var _imageRequestState: MutableStateFlow<States> = MutableStateFlow(States.LOADING)

    init {
        getListOfTransactions()
        getListOfProducts()
        getListOfSales()
        getListOfPurchases()
    }

    private fun getTotalValueByTransactionType(type: TransactionType): Double {
        return transactions.value
            .filter { transaction -> transaction.transactionType == type }
            .sumOf { transaction -> transaction.transactionAmount }
    }

    private fun getListOfTransactions() {
        _transactions.value = listOfTransactions
    }

    fun setImageRequestState(state: States) {
        _imageRequestState.value = state
    }

    fun getListOfSales() {
        listOfSales.value = listOfTransactions.filter {
            it.transactionType == TransactionType.SALE
        }
    }

    fun getListOfPurchases() {
        listOfPurchases.value = listOfTransactions.filter {
            it.transactionType == TransactionType
                .PURCHASE
        }
    }

    fun getListOfProducts() {
        listOfProducts.value = listOfProductsLocal
    }

    fun incrementListOfTransactions(transactions: Transaction) {
        _transactions.value.add(transactions)
    }

    fun getSalesValue(): Double = getTotalValueByTransactionType(TransactionType.SALE)

    fun getPurchasesValue(): Double = getTotalValueByTransactionType(TransactionType.PURCHASE)

    fun deleteTransaction(transaction: Transaction) {
        _transactions.value.removeAt(_transactions.value.indexOf(transaction))
    }

    fun deleteProduct(product: Product) {
        _listOfProducts.value.removeAt(listOfProducts.value.indexOf(product))
    }
}
