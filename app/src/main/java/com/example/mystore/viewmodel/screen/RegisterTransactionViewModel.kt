package com.example.mystore.viewmodel.screen

import com.example.mystore.TransactionType
import com.example.mystore.listOfProductsLocal
import com.example.mystore.model.Product
import com.example.mystore.model.Transaction
import kotlinx.coroutines.flow.MutableStateFlow

class RegisterTransactionViewModel : CommonViewModel() {

    private var _transactionType: MutableStateFlow<List<TransactionType>> =
        MutableStateFlow(listOf(TransactionType.SALE, TransactionType.PURCHASE))
    val listOfTransactionType: MutableStateFlow<List<TransactionType>> = _transactionType

    private val _quantity: MutableStateFlow<Int> = MutableStateFlow(1)
    val quantity: MutableStateFlow<Int> = _quantity

    private val _maxQuantity: MutableStateFlow<Int> = MutableStateFlow(1)
    val maxQuantity: MutableStateFlow<Int> = _maxQuantity

    private val _screenWidth: MutableStateFlow<Int> = MutableStateFlow(0)
    val screenWidth: MutableStateFlow<Int> = _screenWidth

    private val _totalValue: MutableStateFlow<Double> = MutableStateFlow(0.0)
    val totalValue: MutableStateFlow<Double> = _totalValue

    private val _transactionValue: MutableStateFlow<Transaction> = MutableStateFlow(Transaction())
    val transactionValue: MutableStateFlow<Transaction> = _transactionValue

    private val _showAlertDialogOnRegisterTransaction: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val showAlertDialogOnRegisterTransaction: MutableStateFlow<Boolean> =
        _showAlertDialogOnRegisterTransaction

    private val _showToast: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showToast: MutableStateFlow<Boolean> = _showToast

    init {
        getProducts()
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
        listOfTransactionType.value = listOf(
            TransactionType.SALE,
            TransactionType.PURCHASE,
        )
    }

    private fun getProducts() {
        listOfProducts.value = listOfProductsLocal
    }

    private fun getScreenWidth() {
        screenWidth.value = _screenWidth.value
    }

    private fun getQuantity() {
        quantity.value = _quantity.value
    }

    private fun getMaxQuantity() {
        maxQuantity.value = _maxQuantity.value
    }

    private fun getShowAlertDialogOnRegisterTransaction() {
        showAlertDialogOnRegisterTransaction.value = _showAlertDialogOnRegisterTransaction.value
    }

    fun setShowAlertDialogOnRegisterTransaction(show: Boolean) {
        _showAlertDialogOnRegisterTransaction.value = show
    }

    fun getShowToast() {
        showToast.value = _showToast.value
    }

    fun setShowToast(show: Boolean) {
        _showToast.value = show
    }

    fun setMaxQuantity(quantity: Int) {
        _maxQuantity.value = quantity
    }

    fun setTotalValue(value: Double) {
        _totalValue.value = value
    }

    fun setScreenWidth(width: Int) {
        _screenWidth.value = width
    }

    fun setQuantity(quantity: Int) {
        _quantity.value = quantity
    }

    fun setTransactionValue(transaction: Transaction) {
        _transactionValue.value = transaction
    }

    fun saveTransaction(transaction: Transaction) {
        _transactionValue.value = transaction
    }

    fun clearAll() {
        _transactionValue.value = Transaction()
        _quantity.value = 1
        _totalValue.value = 0.0
    }

    /**
     * This method is used to update the quantity of the product in the list of products
     */
    fun updateProductQuantity(
        product: Product,
        quantity: Int,
        transaction: Transaction,
    ) {
        val index = listOfProducts.value.indexOf(product)

        if (transaction.transactionType.name == TransactionType.SALE.name) {
            listOfProducts.value[index].quantity -= quantity
        } else {
            listOfProducts.value[index].quantity += quantity
        }
    }
}
