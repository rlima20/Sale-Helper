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
import kotlinx.coroutines.flow.StateFlow

/**
 * Common view model.
 * Nessa classe ficam os métodos e variáveis que são compartilhados entre as telas.
 * Aqui eu carrego os dados de produtos e transações e a lista de vendas e compras, para serem
 * carregados na HomeScreen e na TransactionScreen.
 * @constructor Create empty constructor for common view model
 */
open class CommonViewModel : ViewModel() {

    private var _listOfProducts: MutableStateFlow<MutableList<Product>> =
        MutableStateFlow(mutableListOf())
    val listOfProducts: StateFlow<MutableList<Product>> = _listOfProducts

    private val _listOfSales: MutableState<List<Transaction>> = mutableStateOf(listOf())
    val listOfSales: MutableState<List<Transaction>> = _listOfSales

    private val _listOfPurchases: MutableState<List<Transaction>> = mutableStateOf(listOf())
    val listOfPurchases: MutableState<List<Transaction>> = _listOfPurchases

    private val _transactions: MutableStateFlow<MutableList<Transaction>> =
        MutableStateFlow(listOfTransactions)
    val transactions: StateFlow<MutableList<Transaction>> = _transactions

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
        _listOfProducts.value = listOfProductsLocal
    }

    fun incrementListOfTransactions(transactions: Transaction) {
        _transactions.value.add(transactions)
    }

    fun getSalesValue(): Double = getTotalValueByTransactionType(TransactionType.SALE)

    fun getPurchasesValue(): Double = getTotalValueByTransactionType(TransactionType.PURCHASE)

    fun deleteTransaction(transaction: Transaction) {
        _transactions.value.removeAt(_transactions.value.indexOf(transaction))
    }

    fun setListOfProducts(listOfProducts: MutableList<Product>) {
        _listOfProducts.value = listOfProducts
    }

    /**
     * Delete product.
     * This method is used to delete the product in the list of products.
     * @param product Product
     */
    fun deleteProduct(product: Product) {
        _listOfProducts.value.removeAt(listOfProducts.value.indexOf(product))
    }

    /**
     * Add product.
     * This method is used to add the product in the list of products.
     * @param product Product
     */
    fun createProduct(product: Product) {
        _listOfProducts.value.add(product)
    }

    /**
     * Update product.
     * This method is used to update the product in the list of products.
     * @param product Product
     */
    fun updateProduct(product: Product) {
        _listOfProducts.value[_listOfProducts.value.indexOfFirst { it.id == product.id }] = product
    }
}
