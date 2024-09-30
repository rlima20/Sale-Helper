package com.example.mystore.commons.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystore.R
import com.example.mystore.commons.AppApplication
import com.example.mystore.enums.States
import com.example.mystore.enums.TransactionType
import com.example.mystore.features.registerproduct.datasource.listOfProductsLocal
import com.example.mystore.features.registerproduct.mappers.toProduct
import com.example.mystore.features.registerproduct.mappers.toProductEntity
import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registerproduct.repository.ProductRepositoryImpl
import com.example.mystore.features.registertransaction.datasource.listOfTransactionsLocal
import com.example.mystore.features.registertransaction.mappers.toTransaction
import com.example.mystore.features.registertransaction.mappers.toTransactionEntity
import com.example.mystore.features.registertransaction.model.Transaction
import com.example.mystore.features.registertransaction.repository.TransactionRepositoryImpl
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
    val transactionRepository: TransactionRepositoryImpl,
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

    private val _shouldUseDatabase: MutableState<Boolean> = mutableStateOf(true)
    val shouldUseDatabase: MutableState<Boolean> = _shouldUseDatabase

    private val application = AppApplication.instance

    // DropdownMenu variables
    private var _screenTitle: MutableStateFlow<String> = MutableStateFlow(
        application.getString(R.string.my_store_home),
    )
    val screenTitle: StateFlow<String> = _screenTitle

    private val _isMenuExpanded: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isMenuExpanded: StateFlow<Boolean> = _isMenuExpanded

    private val _textFieldSize: MutableStateFlow<Size> = MutableStateFlow(Size.Zero)
    val textFieldSize: StateFlow<Size> = _textFieldSize

    private val _shouldItemBeVisible: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val shouldItemBeVisible: StateFlow<Boolean> = _shouldItemBeVisible

    init {
        getListOfProducts()
        getListOfTransactions()
        getListOfSales()
        getListOfPurchases()
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

    fun getListOfTransactions() {
        if (shouldUseDatabase.value) {
            viewModelScope.launch {
                transactionRepository.getAllTransactions().collect { listOfTransactionEntity ->
                    val listOfTransactions = mutableListOf<Transaction>()
                    listOfTransactionEntity.forEach { transactionEntity ->
                        listOfTransactions.add(transactionEntity.toTransaction())
                    }
                    setListOfTransactions(listOfTransactions)
                }
            }
        } else {
            setListOfTransactions(listOfTransactions.value)
        }
    }

    fun getListOfSales() {
        if (shouldUseDatabase.value) {
            listOfSales.value = listOfTransactions.value.filter {
                it.transactionType == TransactionType.SALE
            }
        } else {
            listOfSales.value = listOfTransactionsLocal.filter {
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
            listOfPurchases.value = listOfTransactionsLocal.filter {
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
        if (shouldUseDatabase.value) {
            CoroutineScope(Dispatchers.IO).launch {
                transactionRepository.deleteTransaction(transaction.toTransactionEntity())
            }
        } else {
            _listOfTransactions.value.removeAt(_listOfTransactions.value.indexOf(transaction))
        }
    }

    private fun setListOfProducts(listOfProducts: MutableList<Product>) {
        _listOfProducts.value = listOfProducts
    }

    private fun setListOfTransactions(listOfTransactions: MutableList<Transaction>) {
        _listOfTransactions.value = listOfTransactions
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

    fun setScreenTitle(title: String) {
        _screenTitle.value = title
    }

    fun expandMenu(isExpanded: Boolean) {
        _isMenuExpanded.value = isExpanded
    }

    fun setTextFieldSize(size: Size) {
        _textFieldSize.value = size
    }

    fun setValueVisibility(shouldItemBeVisible: Boolean) {
        _shouldItemBeVisible.value = shouldItemBeVisible
    }
}
