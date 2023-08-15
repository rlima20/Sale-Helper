package com.example.mystore.viewmodel.screen

import androidx.lifecycle.ViewModel
import com.example.mystore.TransactionType
import com.example.mystore.listOfProductsLocal
import com.example.mystore.model.Product
import kotlinx.coroutines.flow.MutableStateFlow

class RegisterTransactionViewModel : ViewModel() {

    private var _transactionType: MutableStateFlow<List<TransactionType>> =
        MutableStateFlow(listOf(TransactionType.SALE))
    val listOfTransactionType: MutableStateFlow<List<TransactionType>> = _transactionType

    private var _listOfProducts: MutableStateFlow<List<Product>> = MutableStateFlow(listOf())
    val listOfProducts: MutableStateFlow<List<Product>> = _listOfProducts

    init {
        getProducts()
        getTransactionTypes()
    }

    private fun getTransactionTypes() {
        listOfTransactionType.value = listOf(TransactionType.SALE)
    }

    private fun getProducts() {
        listOfProducts.value = listOfProductsLocal
    }
}
