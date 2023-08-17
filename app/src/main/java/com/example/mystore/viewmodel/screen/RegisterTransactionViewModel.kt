package com.example.mystore.viewmodel.screen

import com.example.mystore.TransactionType
import com.example.mystore.listOfProductsLocal
import com.example.mystore.model.Product
import com.example.mystore.model.Transaction
import kotlinx.coroutines.flow.MutableStateFlow

class RegisterTransactionViewModel : CommonViewModel() {

    private var _transactionType: MutableStateFlow<List<TransactionType>> =
        MutableStateFlow(listOf(TransactionType.SALE, TransactionType.PURCHASE))
    val listOfTransactionType: MutableStateFlow<List<TransactionType>> = _transactionType

    private var _listOfProducts: MutableStateFlow<List<Product>> = MutableStateFlow(listOf())
    val listOfProducts: MutableStateFlow<List<Product>> = _listOfProducts

    private val _quantity: MutableStateFlow<Int> = MutableStateFlow(1)
    val quantity: MutableStateFlow<Int> = _quantity

    private val _screenWidth: MutableStateFlow<Int> = MutableStateFlow(0)
    val screenWidth: MutableStateFlow<Int> = _screenWidth

    private val _totalValue: MutableStateFlow<Double> = MutableStateFlow(0.0)
    val totalValue: MutableStateFlow<Double> = _totalValue

    private val _transactionValue: MutableStateFlow<Transaction> = MutableStateFlow(Transaction())
    val transactionValue: MutableStateFlow<Transaction> = _transactionValue

    init {
        getProducts()
        getTransactionTypes()
        getSalesValue()
        getPurchasesValue()
        getListOfSales()
        getListOfPurchases()
        getScreenWidth()
        getQuantity()
    }

    private fun getTransactionTypes() {
        listOfTransactionType.value = listOf(
            TransactionType.SALE,
            TransactionType.PURCHASE,
        )
    }

    private fun getProducts() {
        listOfProducts.value = listOfProductsLocal
    }

    private fun getScreenWidth() {
        screenWidth.value = _screenWidth.value
    }

    private fun getQuantity() {
        quantity.value = _quantity.value
    }

    fun setTotalValue(value: Double) {
        _totalValue.value = value
    }

    fun setScreenWidth(width: Int) {
        _screenWidth.value = width
    }

    fun setQuantity(quantity: Int) {
        _quantity.value = quantity
    }

    fun setTransactionValue(transaction: Transaction) {
        _transactionValue.value = transaction
    }

    fun saveTransaction(transaction: Transaction) {
        _transactionValue.value = transaction
    }
}
