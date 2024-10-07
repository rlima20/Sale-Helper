package com.example.mystore.features.homescreen.viewmodel

import com.example.mystore.commons.usecase.CommonUseCase
import com.example.mystore.commons.viewmodel.CommonViewModel
import com.example.mystore.commons.viewstate.CommonViewState
import com.example.mystore.features.homescreen.model.Resume
import com.example.mystore.features.homescreen.viewstate.HomeViewState
import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registertransaction.model.Transaction
import kotlinx.coroutines.Dispatchers

class HomeViewModel(
    commonUseCase: CommonUseCase,
    dispatcherProvider: Dispatchers
) : CommonViewModel(
    commonUseCase,
    dispatcherProvider
) {

    val homeViewState = HomeViewState()

    init {
        getResume()
        getListOfProducts()
        getShowAlertDialogHomeScreen()
        getShowAlertDialogTransactionDetail()
        getShowAlertDialogHomeScreenProduct()
        getShowToastState()
        getTransaction()
        getProduct()
    }

    // Private functions
    private fun getShowToastState() = homeViewState.showToast.value

    private fun getShowAlertDialogTransactionDetail() =
        homeViewState.showAlertDialogTransactionDetail.value

    private fun getTransaction(): Transaction = homeViewState.transaction.value

    // Public functions
    fun setShowAlertDialogTransactionDetail(state: Boolean) {
        homeViewState.showAlertDialogTransactionDetail.value = state
    }

    fun setTransaction(transaction: Transaction) {
        homeViewState.transaction.value = transaction
    }

    fun setShowToastState(message: String, state: Boolean) {
        homeViewState.showToast.value = Pair(message, state)
    }

    fun getResume() {
        var debits = 0.0
        var grossRevenue = 0.0

        commonViewState.listOfPurchases.value?.let { listOfTransaction ->
            debits = listOfTransaction.sumOf { it.transactionAmount }
        } ?: 0

        commonViewState.listOfSales.value?.let { listOfTransaction ->
            grossRevenue = listOfTransaction.sumOf { it.transactionAmount }
        } ?: 0

        val netRevenue = grossRevenue.minus(debits)

        var stock = 0.0
        val stockValue = commonViewState.listOfProducts.value?.let { listOfProducts ->
            stock = listOfProducts.sumOf { it.purchasePrice * it.quantity }

        } ?: 0

        if ((debits.equals(0.0)) && (grossRevenue.equals(0.0)) &&
            (netRevenue.equals(0.0)) && (stockValue.equals(0.0))
        ) {
            homeViewState.resume.value = null
        } else {
            homeViewState.resume.value = Resume(
                debits = debits,
                grossRevenue = grossRevenue,
                netRevenue = netRevenue,
                stockValue = stock,
            )
        }
    }

    fun getShowAlertDialogHomeScreen() = homeViewState.showAlertDialogHomeScreen.value

    fun setShowAlertDialogHomeScreen(state: Boolean) {
        homeViewState.showAlertDialogHomeScreen.value = state
    }

    private fun getShowAlertDialogHomeScreenProduct() =
        homeViewState.showAlertDialogHomeScreenProduct.value

    fun setShowAlertDialogHomeScreenProduct(state: Boolean) {
        homeViewState.showAlertDialogHomeScreenProduct.value = state
    }

    private fun getProduct() = homeViewState.product.value

    fun setProduct(product: Product) {
        homeViewState.product.value = product
    }
}
