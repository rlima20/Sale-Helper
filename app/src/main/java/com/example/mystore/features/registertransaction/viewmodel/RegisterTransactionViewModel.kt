package com.example.mystore.features.registertransaction.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.mystore.commons.usecase.CommonUseCase
import com.example.mystore.commons.viewmodel.CommonViewModel
import com.example.mystore.enums.TransactionType
import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registertransaction.model.Transaction
import com.example.mystore.features.registertransaction.viewstate.RegisterTransactionViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterTransactionViewModel(
    private val commonUseCase: CommonUseCase,
    private val dispatcherProvider: Dispatchers
) : CommonViewModel(commonUseCase, dispatcherProvider) {

    val registerTransactionViewState = RegisterTransactionViewState()

    init {
        getAllProducts()
        getTransactionTypes()
        getSalesValue()
        getPurchasesValue()
        getListOfSales()
        getListOfPurchases()
        getScreenWidth()
        getQuantity()
        getMaxQuantity()
        getShowAlertDialogOnRegisterTransaction()
        getShowToast()
    }

    private fun getTransactionTypes() {
        registerTransactionViewState.listOfTransactionType = listOf(
            TransactionType.SALE,
            TransactionType.PURCHASE,
        )
    }

    private fun getScreenWidth() = registerTransactionViewState.screenWidth.value
    private fun getQuantity() = registerTransactionViewState.quantity.value
    private fun getMaxQuantity() = registerTransactionViewState.maxQuantity.value
    private fun getShowAlertDialogOnRegisterTransaction() =
        registerTransactionViewState.showAlertDialogOnRegisterTransaction.value

    fun setShowAlertDialogOnRegisterTransaction(show: Boolean) {
        registerTransactionViewState.showAlertDialogOnRegisterTransaction.value = show
    }

    private fun getShowToast() = registerTransactionViewState.showToast.value
    fun setShowToast(show: Boolean) {
        registerTransactionViewState.showToast.value = show
    }

    fun setMaxQuantity(quantity: Int) {
        registerTransactionViewState.maxQuantity.value = quantity
    }

    fun setTotalValue(value: Double) {
        registerTransactionViewState.totalValue.value = value
    }

    fun setScreenWidth(width: Int) {
        registerTransactionViewState.screenWidth.value = width
    }

    fun setQuantity(quantity: Int) {
        registerTransactionViewState.quantity.value = quantity
    }

    fun setTransactionValue(transaction: Transaction) {
        registerTransactionViewState.transactionValue.value = transaction
    }

    fun saveTransaction(transaction: Transaction) {
        if (commonViewState.shouldUseDatabase.value) {
            viewModelScope.launch(dispatcherProvider.IO) {
                commonUseCase.insertTransaction(transaction)
            }
        } else {
            registerTransactionViewState.transactionValue.value = transaction
        }
    }

    fun clearAll() {
        registerTransactionViewState.transactionValue.value = Transaction()
        registerTransactionViewState.quantity.value = 1
        registerTransactionViewState.totalValue.value = 0.0
    }

    /**
     * This method is used to update the quantity of the product in the list of products
     */
    fun updateProductQuantity(
        product: Product,
        quantity: Int,
        transaction: Transaction,
    ) {
        val index = commonViewState.listOfProducts.value.indexOf(product)
        val innerList = commonViewState.listOfProducts.value

        if (transaction.transactionType.name == TransactionType.SALE.name) {
            innerList[index].quantity -= quantity
        } else {
            innerList[index].quantity += quantity
        }
        viewModelScope.launch(dispatcherProvider.IO) {
            commonUseCase.updateProduct(innerList[index])
        }
    }
}
