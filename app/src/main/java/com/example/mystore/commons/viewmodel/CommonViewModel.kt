package com.example.mystore.commons.viewmodel

import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystore.commons.usecase.CommonUseCase
import com.example.mystore.commons.viewstate.CommonViewState
import com.example.mystore.enums.States
import com.example.mystore.enums.TransactionType
import com.example.mystore.features.registerproduct.datasource.local.listOfProductsLocal
import com.example.mystore.features.registerproduct.mappers.toProduct
import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registertransaction.datasource.listOfTransactionsLocal
import com.example.mystore.features.registertransaction.mappers.toTransaction
import com.example.mystore.features.registertransaction.model.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class CommonViewModel(
    commonUseCase: CommonUseCase,
    dispatcherProvider: Dispatchers
) : ViewModel() {

    private val innerDispatcherProvider = dispatcherProvider
    private val innerCommonUseCase = commonUseCase

    val commonViewState = CommonViewState()

    init {
        getAllProducts()
        getListOfTransactions()
        getListOfSales()
        getListOfPurchases()
    }

    fun getAllProducts() {
        if (commonViewState.shouldUseDatabase.value) {
            viewModelScope.launch(innerDispatcherProvider.IO) {
                innerCommonUseCase.getAllProducts().collect { listOfProductEntity ->
                    val listOfProducts = mutableListOf<Product>()
                    listOfProductEntity.forEach { productEntity ->
                        listOfProducts.add(productEntity.toProduct())
                    }
                    commonViewState.listOfProducts.value = listOfProducts
                }
            }
        } else {
            commonViewState.listOfProducts.value = innerCommonUseCase.getListOfProductsLocal()
        }
    }

//    fun getAllProducts2() {
//        if (commonViewState.shouldUseDatabase.value) {
//            viewModelScope.launch {
//                commonViewState.listOfProducts.value = innerCommonUseCase.getAllProducts()
//            }
//        } else {
//            commonViewState.listOfProducts.value = innerCommonUseCase.getListOfProductsLocal()
//        }
//    }

    fun getListOfTransactions() {
        if (commonViewState.shouldUseDatabase.value) {
            viewModelScope.launch(innerDispatcherProvider.IO) {
                innerCommonUseCase.getAllTransactions().collect { listOfTransactionEntity ->
                    val listOfTransactions = mutableListOf<Transaction>()
                    listOfTransactionEntity.forEach { transactionEntity ->
                        listOfTransactions.add(transactionEntity.toTransaction())
                    }
                    commonViewState.listOfTransactions.value = listOfTransactions
                }
            }
        } else {
            commonViewState.listOfTransactions.value = listOfTransactionsLocal
        }
    }

//    fun getListOfTransactions() {
//        if (commonViewState.shouldUseDatabase.value) {
//            viewModelScope.launch(innerDispatcherProvider.IO) {
//                commonViewState.listOfTransactions.value = innerCommonUseCase.getAllTransactions()
//            }
//        } else {
//            commonViewState.listOfTransactions.value = listOfTransactionsLocal
//        }
//    }

    fun getListOfSales() {
        if (commonViewState.shouldUseDatabase.value) {
            commonViewState.listOfSales.value =
                commonViewState.listOfTransactions.value.filter {
                    it.transactionType == TransactionType.SALE
                }
        } else {
            commonViewState.listOfSales.value = listOfTransactionsLocal.filter {
                it.transactionType == TransactionType.SALE
            }
        }
    }

    fun getListOfPurchases() {
        if (commonViewState.shouldUseDatabase.value) {
            commonViewState.listOfPurchases.value =
                commonViewState.listOfTransactions.value.filter {
                    it.transactionType == TransactionType.PURCHASE
                }
        } else {
            commonViewState.listOfPurchases.value = listOfTransactionsLocal.filter {
                it.transactionType == TransactionType.PURCHASE
            }
        }
    }

    fun createProduct(product: Product) {
        if (commonViewState.shouldUseDatabase.value) {
            viewModelScope.launch(innerDispatcherProvider.IO) {
                innerCommonUseCase.createProduct(product)
            }
        } else {
            listOfProductsLocal.add(product)
        }
        getAllProducts()
    }

    suspend fun updateProduct(product: Product) {
        if (commonViewState.shouldUseDatabase.value) {
            viewModelScope.launch(innerDispatcherProvider.IO) {
                innerCommonUseCase.updateProduct(product)
            }
        } else {
            listOfProductsLocal[listOfProductsLocal.indexOfFirst {
                it.productId == product.productId
            }] = product
        }
        getAllProducts()
    }

    fun deleteProduct(product: Product) {
        if (commonViewState.shouldUseDatabase.value) {
            CoroutineScope(Dispatchers.IO).launch {
                innerCommonUseCase.deleteProduct(product)
            }
        } else {
            listOfProductsLocal.removeAt(listOfProductsLocal.indexOf(product))
        }
        getAllProducts()
    }

    // TRANSACTIONS
    fun deleteTransaction(transaction: Transaction) {
        if (commonViewState.shouldUseDatabase.value) {
            CoroutineScope(Dispatchers.IO).launch {
                innerCommonUseCase.deleteTransaction(transaction)
            }
        } else {
            listOfTransactionsLocal.removeAt(listOfTransactionsLocal.indexOf(transaction))
        }
    }

    private fun getTotalValueByTransactionType(type: TransactionType): Double {
        return commonViewState.listOfTransactions.value.let {
            it.filter { transaction -> transaction.transactionType == type }
                .sumOf { transaction -> transaction.transactionAmount }
        }
    }

    fun setImageRequestState(state: States) {
        commonViewState.imageRequestState.value = state
    }

    fun incrementListOfTransactions(transaction: Transaction) {
        val currentList = commonViewState.listOfTransactions.value.orEmpty().toMutableList()
        currentList.add(transaction)
        commonViewState.listOfTransactions.value = currentList
    }

    fun getSalesValue(): Double = getTotalValueByTransactionType(TransactionType.SALE)

    fun getPurchasesValue(): Double = getTotalValueByTransactionType(TransactionType.PURCHASE)

    fun setScreenTitle(title: String) {
        commonViewState.screenTitle.value = title
    }

    fun expandMenu(isExpanded: Boolean) {
        commonViewState.isMenuExpanded.value = isExpanded
    }

    fun setTextFieldSize(size: Size) {
        commonViewState.textFieldSize.value = size
    }

    fun setValueVisibility(shouldItemBeVisible: Boolean) {
        commonViewState.shouldItemBeVisible.value = shouldItemBeVisible
    }
}
