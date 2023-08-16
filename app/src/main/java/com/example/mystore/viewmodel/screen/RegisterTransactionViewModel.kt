package com.example.mystore.viewmodel.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mystore.TransactionType
import com.example.mystore.listOfProductsLocal
import com.example.mystore.model.Product
import com.example.mystore.model.Transaction
import kotlinx.coroutines.flow.MutableStateFlow

class RegisterTransactionViewModel : ViewModel() {

    // todo - quase todo esse código está duplicado, refatorar.
    private var _transactionType: MutableStateFlow<List<TransactionType>> =
        MutableStateFlow(listOf(TransactionType.SALE, TransactionType.PURCHASE))
    val listOfTransactionType: MutableStateFlow<List<TransactionType>> = _transactionType

    private var _listOfProducts: MutableStateFlow<List<Product>> = MutableStateFlow(listOf())
    val listOfProducts: MutableStateFlow<List<Product>> = _listOfProducts

    // State of the screen
    private val _listOfSales: MutableState<List<Transaction>> =
        mutableStateOf(listOf())
    val listOfSales: MutableState<List<Transaction>> = _listOfSales

    private val _listOfPurchases: MutableState<List<Transaction>> =
        mutableStateOf(listOf())
    val listOfPurchases: MutableState<List<Transaction>> = _listOfPurchases

    val _transactions: MutableStateFlow<List<Transaction>> = MutableStateFlow(listOf())
    val transactions: MutableStateFlow<List<Transaction>> = _transactions

    private val _quantity: MutableStateFlow<Int> = MutableStateFlow(1)
    val quantity: MutableStateFlow<Int> = _quantity

    private val _screenWidth: MutableStateFlow<Int> = MutableStateFlow(0)
    val screenWidth: MutableStateFlow<Int> = _screenWidth

    private val _totalValue: MutableStateFlow<Double> = MutableStateFlow(0.0)
    val totalValue: MutableStateFlow<Double> = _totalValue

    init {
        getProducts()
        getTransactionTypes()
        getSalesValue()
        getPurchasesValue()
        getListOfSales()
        getListOfPurchases()
        getScreenWidth()
        getQUantity()
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

    fun setTotalValue(value: Double) {
        _totalValue.value = value
    }

    fun setQuantityValue(value: Int) {
        _quantity.value = value
    }

    private fun getTotalValueByTransactionType(type: TransactionType): Double {
        return transactions.value
            .filter { transaction -> transaction.transactionType == type }
            .sumOf { transaction -> transaction.transactionAmount }
    }

    private fun getScreenWidth() {
        screenWidth.value = _screenWidth.value
    }

    fun setScreenWidth(width: Int) {
        _screenWidth.value = width
    }

    private fun getQUantity() {
        quantity.value = _quantity.value
    }

    fun setQuantity(quantity: Int) {
        _quantity.value = quantity
    }

    fun getSalesValue(): Double = getTotalValueByTransactionType(TransactionType.SALE)
    fun getPurchasesValue(): Double = getTotalValueByTransactionType(TransactionType.PURCHASE)
}
