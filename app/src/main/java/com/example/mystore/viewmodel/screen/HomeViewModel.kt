package com.example.mystore.viewmodel.screen

import com.example.mystore.model.Product
import com.example.mystore.model.Resume
import com.example.mystore.model.Transaction
import com.example.mystore.repository.ProductRepository
import com.example.mystore.repository.TransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Home view model.
 * Essa classe é responsável por gerenciar os dados da tela Home.
 * _resume: Dados do resumo.
 * _showAlertDialogHomeScreen: Estado do alert dialog da tela Home.
 * _showAlertDialogHomeScreenProduct: Estado do alert dialog da tela Home.
 * _showAlertDialogTransactionDetail: Estado do alert dialog da tela de Detalhe.
 * _showToast: Estado do toast.
 * _transaction: Dados da transação.
 * _product: Dados do produto.
 * getResume: Função que retorna os dados do resumo.
 * @constructor Create empty constructor for home view model
 */

class HomeViewModel(
    transactionRepository: TransactionRepository,
    productRepository: ProductRepository,
) : CommonViewModel(
    transactionRepository,
    productRepository,
) {

    private val _resume: MutableStateFlow<Resume?> = MutableStateFlow(Resume())
    val resume: StateFlow<Resume?> = _resume

    private val _showAlertDialogHomeScreen: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showAlertDialogHomeScreen: StateFlow<Boolean> = _showAlertDialogHomeScreen

    private val _showAlertDialogHomeScreenProduct: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val showAlertDialogHomeScreenProduct: StateFlow<Boolean> = _showAlertDialogHomeScreenProduct

    private val _showAlertDialogTransactionDetail: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val showAlertDialogTransactionDetail: StateFlow<Boolean> =
        _showAlertDialogTransactionDetail

    private val _showToast: MutableStateFlow<Pair<String, Boolean>> =
        MutableStateFlow(Pair("", false))
    val showToast: StateFlow<Pair<String, Boolean>> = _showToast

    private val _transaction: MutableStateFlow<Transaction> = MutableStateFlow(Transaction())
    val transaction: StateFlow<Transaction> = _transaction

    private val _product: MutableStateFlow<Product> = MutableStateFlow(Product())
    val product: StateFlow<Product> = _product

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
    private fun getShowToastState() = showToast.value

    private fun getShowAlertDialogTransactionDetail() = showAlertDialogTransactionDetail.value

    private fun getTransaction(): Transaction = transaction.value

    // Public functions
    fun setShowAlertDialogTransactionDetail(state: Boolean) {
        _showAlertDialogTransactionDetail.value = state
    }

    fun getTransactions(): List<Transaction> = transactions.value

    fun setTransaction(transaction: Transaction) {
        _transaction.value = transaction
    }

    fun setShowToastState(message: String, state: Boolean) {
        _showToast.value = Pair(message, state)
    }

    fun getResume() {
        val debits = listOfPurchases.value.sumOf { it.transactionAmount }
        val grossRevenue = listOfSales.value.sumOf { it.transactionAmount }
        val netRevenue = grossRevenue.minus(debits)
        val stockValue = listOfProducts.value.sumOf { it.purchasePrice * it.quantity }

        if ((debits.equals(0.0)) && (grossRevenue.equals(0.0)) && (netRevenue.equals(0.0)) && (
                stockValue.equals(0.0)
                )
        ) {
            _resume.value = null
        } else {
            _resume.value = Resume(
                debits = debits,
                grossRevenue = grossRevenue,
                netRevenue = netRevenue,
                stockValue = stockValue,
            )
        }
    }

    fun getShowAlertDialogHomeScreen() = showAlertDialogHomeScreen.value

    fun setShowAlertDialogHomeScreen(state: Boolean) {
        _showAlertDialogHomeScreen.value = state
    }

    private fun getShowAlertDialogHomeScreenProduct() = showAlertDialogHomeScreenProduct.value

    fun setShowAlertDialogHomeScreenProduct(state: Boolean) {
        _showAlertDialogHomeScreenProduct.value = state
    }

    private fun getProduct() = product.value

    fun setProduct(product: Product) {
        _product.value = product
    }
}
