package com.example.mystore.viewmodel.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystore.States
import com.example.mystore.TransactionType
import com.example.mystore.listOfProductsLocal
import com.example.mystore.listOfTransactions
import com.example.mystore.mappers.toProduct
import com.example.mystore.mappers.toProductEntity
import com.example.mystore.mappers.toTransaction
import com.example.mystore.mappers.toTransactionEntity
import com.example.mystore.model.Product
import com.example.mystore.model.Transaction
import com.example.mystore.repository.ProductRepository
import com.example.mystore.repository.TransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Common view model.
 * Nessa classe ficam os métodos e variáveis que são compartilhados entre as telas.
 * Aqui eu carrego os dados de produtos e transações e a lista de vendas e compras, para serem
 * carregados na HomeScreen e na TransactionScreen.
 * @constructor Create empty constructor for common view model
 */
open class CommonViewModel(
    private val transactionRepository: TransactionRepository,
    private val productRepository: ProductRepository,
) : ViewModel() {

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
        // Load data from in-memory storage
        getListOfTransactions()
        getListOfProducts()
        getListOfSales()
        getListOfPurchases()

        // Load data from database
        // getListOfTransactionsRoom()
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
        _listOfProducts.value[
            _listOfProducts.value.indexOfFirst {
                it.productId == product.productId
            },
        ] =
            product
    }

    /**
     * Section of database operations
     */
    private fun getListOfTransactionsRoom() {
        viewModelScope.launch {
            transactionRepository.getAllTransactions().collect { listOfTransactionEntity ->
                listOfTransactionEntity.forEach { transactionEntity ->
                    _transactions.value.add(transactionEntity.toTransaction())
                }
            }
        }
    }

    fun insertTransactionRoom(transaction: Transaction) {
        viewModelScope.launch {
            transactionRepository.insertTransaction(transaction.toTransactionEntity())
        }
    }

    fun deleteTransactionRoom(transaction: Transaction) {
        viewModelScope.launch {
            transactionRepository.deleteTransaction(transaction.toTransactionEntity())
        }
    }

    fun updateTransactionRoom(transaction: Transaction) {
        viewModelScope.launch {
            transactionRepository.updateTransaction(transaction.toTransactionEntity())
        }
    }

    fun getAllProductsRoom() {
        viewModelScope.launch {
            productRepository.getAllProducts().collect { listOfProductEntity ->
                val listOfProducts = mutableListOf<Product>()
                listOfProductEntity.forEach { productEntity ->
                    listOfProducts.add(productEntity.toProduct())
                }
                setListOfProducts(listOfProducts)
            }
        }
    }

    fun insertProductRoom(product: Product) {
        viewModelScope.launch {
            productRepository.insertProduct(product.toProductEntity())
        }
    }

    fun deleteProductRoom(product: Product) {
        viewModelScope.launch {
            productRepository.deleteProduct(product.toProductEntity())
        }
    }

    fun updateProductRoom(product: Product) {
        viewModelScope.launch {
            productRepository.updateProduct(product.toProductEntity())
        }
    }
}
