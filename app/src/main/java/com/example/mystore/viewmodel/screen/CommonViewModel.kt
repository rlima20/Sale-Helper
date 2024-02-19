package com.example.mystore.viewmodel.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystore.States
import com.example.mystore.TransactionType
import com.example.mystore.listOfProductsLocal
import com.example.mystore.mappers.toProduct
import com.example.mystore.mappers.toProductEntity
import com.example.mystore.model.Product
import com.example.mystore.model.Transaction
import com.example.mystore.repository.ProductRepositoryImpl
import com.example.mystore.repository.TransactionRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
    private val transactionRepository: TransactionRepositoryImpl,
    private val productRepository: ProductRepositoryImpl,
) : ViewModel() {

    private var _listOfProducts: MutableStateFlow<MutableList<Product>> =
        MutableStateFlow(mutableListOf())
    val listOfProducts: StateFlow<MutableList<Product>> = _listOfProducts

    private val _listOfSales: MutableState<List<Transaction>> = mutableStateOf(listOf())
    val listOfSales: MutableState<List<Transaction>> = _listOfSales

    private val _listOfPurchases: MutableState<List<Transaction>> = mutableStateOf(listOf())
    val listOfPurchases: MutableState<List<Transaction>> = _listOfPurchases

    private val _listOfTransactions: MutableStateFlow<MutableList<Transaction>> =
        MutableStateFlow(mutableListOf())
    val listOfTransactions: StateFlow<MutableList<Transaction>> = _listOfTransactions

    private var _imageRequestState: MutableStateFlow<States> = MutableStateFlow(States.LOADING)

    private val shouldUseDatabase: MutableState<Boolean> = mutableStateOf(true)

    init {
        getListOfProducts()
        getListOfTransactions()
        getListOfSales()
        getListOfPurchases()
    }

    // PRODUCTS
    private fun getListOfTransactions() {
        if (shouldUseDatabase.value) {
            _listOfTransactions.value = listOfTransactions.value
        } else {
            _listOfTransactions.value = com.example.mystore.listOfTransactions
        }
    }

    fun getListOfProducts() {
        if (shouldUseDatabase.value) {
            viewModelScope.launch {
                productRepository.getAllProducts().collect { listOfProductEntity ->
                    val listOfProducts = mutableListOf<Product>()
                    listOfProductEntity.forEach { productEntity ->
                        listOfProducts.add(productEntity.toProduct())
                    }
                    setListOfProducts(listOfProducts)
                }
            }
        } else {
            _listOfProducts.value = listOfProductsLocal
        }
    }

    fun getListOfSales() {
        if (shouldUseDatabase.value) {
            listOfSales.value = listOfTransactions.value.filter {
                it.transactionType == TransactionType.SALE
            }
        } else {
            listOfSales.value = com.example.mystore.listOfTransactions.filter {
                it.transactionType == TransactionType.SALE
            }
        }
    }

    fun getListOfPurchases() {
        if (shouldUseDatabase.value) {
            listOfPurchases.value = listOfTransactions.value.filter {
                it.transactionType == TransactionType.PURCHASE
            }
        } else {
            listOfPurchases.value = com.example.mystore.listOfTransactions.filter {
                it.transactionType == TransactionType.PURCHASE
            }
        }
    }

    fun createProduct(product: Product) {
        if (shouldUseDatabase.value) {
            CoroutineScope(Dispatchers.IO).launch {
                productRepository.insertProduct(product.toProductEntity())
            }
        } else {
            listOfProductsLocal.add(product)
        }
    }

    fun updateProduct(product: Product) {
        if (shouldUseDatabase.value) {
            CoroutineScope(Dispatchers.IO).launch {
                productRepository.updateProduct(product.toProductEntity())
            }
        } else {
            _listOfProducts.value[
                _listOfProducts.value.indexOfFirst {
                    it.productId == product.productId
                },
            ] =
                product
        }
    }

    fun deleteProduct(product: Product) {
        if (shouldUseDatabase.value) {
            CoroutineScope(Dispatchers.IO).launch {
                productRepository.deleteProduct(product.toProductEntity())
            }
        } else {
            _listOfProducts.value.removeAt(listOfProducts.value.indexOf(product))
        }
        getListOfProducts()
    }

    // TRANSACTIONS
    fun deleteTransaction(transaction: Transaction) {
        _listOfTransactions.value.removeAt(_listOfTransactions.value.indexOf(transaction))
    }

    private fun setListOfProducts(listOfProducts: MutableList<Product>) {
        _listOfProducts.value = listOfProducts
    }

    private fun getTotalValueByTransactionType(type: TransactionType): Double {
        return listOfTransactions.value
            .filter { transaction -> transaction.transactionType == type }
            .sumOf { transaction -> transaction.transactionAmount }
    }

    fun setImageRequestState(state: States) {
        _imageRequestState.value = state
    }

    fun incrementListOfTransactions(transactions: Transaction) {
        _listOfTransactions.value.add(transactions)
    }

    fun getSalesValue(): Double = getTotalValueByTransactionType(TransactionType.SALE)

    fun getPurchasesValue(): Double = getTotalValueByTransactionType(TransactionType.PURCHASE)
}
