package com.example.mystore.commons.viewmodel

import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystore.commons.usecase.CommonUseCase
import com.example.mystore.commons.viewstate.CommonViewState
import com.example.mystore.enums.States
import com.example.mystore.enums.TransactionType
import com.example.mystore.features.registerproduct.datasource.local.listOfProductsLocal
import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registertransaction.datasource.listOfTransactionsLocal
import com.example.mystore.features.registertransaction.model.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class CommonViewModel(
    commonUseCase: CommonUseCase,
    dispatcherProvider: Dispatchers
) : ViewModel() {

    val innerDispatcherProvider = dispatcherProvider
    val innerCommonUseCase = commonUseCase

    val commonViewState = CommonViewState()

    init {
        getListOfProducts()
        getListOfTransactions()
        getListOfSales()
        getListOfPurchases()
    }

    fun getListOfProducts() {
        if (commonViewState.shouldUseDatabase.value) {
            viewModelScope.launch(innerDispatcherProvider.IO) {
                commonViewState.listOfProducts.postValue(innerCommonUseCase.getListOfProducts())
            }
        } else {
            commonViewState.listOfProducts.postValue(innerCommonUseCase.getListOfProductsLocal())
        }
    }

    fun getListOfTransactions() {
        if (commonViewState.shouldUseDatabase.value) {
            viewModelScope.launch(innerDispatcherProvider.IO) {
                commonViewState.listOfTransactions.postValue(innerCommonUseCase.getAllTransactions())
            }
        } else {
            commonViewState.listOfTransactions.postValue(listOfTransactionsLocal)
        }
    }

    fun getListOfSales() {
        if (commonViewState.shouldUseDatabase.value) {
            commonViewState.listOfSales.value =
                commonViewState.listOfTransactions.value?.filter {
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
                commonViewState.listOfTransactions.value?.filter {
                    it.transactionType == TransactionType.PURCHASE
                } ?: emptyList()
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
    }

    fun deleteProduct(product: Product) {
        if (commonViewState.shouldUseDatabase.value) {
            CoroutineScope(Dispatchers.IO).launch {
                innerCommonUseCase.deleteProduct(product)
            }
        } else {
            listOfProductsLocal.removeAt(listOfProductsLocal.indexOf(product))
        }
        getListOfProducts()
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
        return commonViewState.listOfTransactions.value?.let {
            it.filter { transaction -> transaction.transactionType == type }
                .sumOf { transaction -> transaction.transactionAmount }
        } ?: 0.0
    }

    fun setImageRequestState(state: States) {
        commonViewState.imageRequestState.value = state
    }

    fun incrementListOfTransactions(transaction: Transaction) {
        val currentList = commonViewState.listOfTransactions.value.orEmpty().toMutableList()
        currentList.add(transaction)
        commonViewState.listOfTransactions.postValue(currentList)
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