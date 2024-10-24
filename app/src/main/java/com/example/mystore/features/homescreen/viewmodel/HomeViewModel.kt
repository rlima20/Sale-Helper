package com.example.mystore.features.homescreen.viewmodel

import com.example.mystore.commons.usecase.CommonUseCase
import com.example.mystore.commons.viewmodel.CommonViewModel
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
        getAllProducts()
        getShowAlertDialogHomeScreen()
        getShowAlertDialogTransactionDetail()
        getShowAlertDialogHomeScreenProduct()
        getShowToastState()
        getTransaction()
        getProduct()
    }

    private fun getShowToastState() = homeViewState.showToast.value

    private fun getShowAlertDialogTransactionDetail() =
        homeViewState.showAlertDialogTransactionDetail.value

    private fun getTransaction(): Transaction = homeViewState.transaction.value

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
        val debits: Double = getDebits()
        val grossRevenue: Double = getGrossRevenue()
        val netRevenue = grossRevenue.minus(debits)
        val stock: Double = getStock()
        setResumeValue(debits, grossRevenue, netRevenue, stock)
    }

    private fun setResumeValue(
        debits: Double,
        grossRevenue: Double,
        netRevenue: Double,
        stock: Double
    ) {
        if ((debits.equals(ZERO)) && (grossRevenue.equals(ZERO)) &&
            (netRevenue.equals(ZERO)) && (stock.equals(ZERO))
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

    private fun getStock(): Double {
        var stock = 0.0
        commonViewState.listOfProducts.value.let { listOfProducts ->
            stock = listOfProducts.sumOf { it.purchasePrice * it.quantity }
        }
        return stock
    }

    private fun getGrossRevenue(): Double {
        var grossRevenue = 0.0
        commonViewState.listOfSales.value.let { listOfTransaction ->
            grossRevenue = listOfTransaction.sumOf { it.transactionAmount }
        }
        return grossRevenue
    }

    private fun getDebits(): Double {
        var debits = 0.0
        commonViewState.listOfPurchases.value.let { listOfTransaction ->
            debits = listOfTransaction.sumOf { it.transactionAmount }
        }
        return debits
    }

    private fun getShowAlertDialogHomeScreen() = homeViewState.showAlertDialogHomeScreen.value

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

    companion object {
        const val ZERO = 0.0
    }
}
