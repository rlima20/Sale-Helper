package com.example.mystore.features.registertransaction.viewmodel

import com.example.mystore.commons.viewmodel.CommonViewModel
import com.example.mystore.enums.TransactionType
import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registerproduct.repository.ProductRepositoryImpl
import com.example.mystore.features.registertransaction.mappers.toTransactionEntity
import com.example.mystore.features.registertransaction.model.Transaction
import com.example.mystore.features.registertransaction.repository.TransactionRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterTransactionViewModel(
    transactionRepository: TransactionRepositoryImpl,
    productRepository: ProductRepositoryImpl,
) : CommonViewModel(
    transactionRepository,
    productRepository,
) {

    private var _listOfTransactionType: MutableStateFlow<List<TransactionType>> =
        MutableStateFlow(listOf(TransactionType.SALE, TransactionType.PURCHASE))
    val listOfTransactionType: StateFlow<List<TransactionType>> = _listOfTransactionType

    private val _quantity: MutableStateFlow<Int> = MutableStateFlow(1)
    val quantity: StateFlow<Int> = _quantity

    private val _maxQuantity: MutableStateFlow<Int> = MutableStateFlow(1)
    val maxQuantity: StateFlow<Int> = _maxQuantity

    private val _screenWidth: MutableStateFlow<Int> = MutableStateFlow(0)
    val screenWidth: StateFlow<Int> = _screenWidth

    private val _totalValue: MutableStateFlow<Double> = MutableStateFlow(0.0)
    val totalValue: StateFlow<Double> = _totalValue

    private val _transactionValue: MutableStateFlow<Transaction> = MutableStateFlow(Transaction())
    val transactionValue: StateFlow<Transaction> = _transactionValue

    private val _showAlertDialogOnRegisterTransaction: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val showAlertDialogOnRegisterTransaction: StateFlow<Boolean> =
        _showAlertDialogOnRegisterTransaction

    private val _showToast: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showToast: StateFlow<Boolean> = _showToast

    init {
        getListOfProducts()
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
        _listOfTransactionType.value = listOf(
            TransactionType.SALE,
            TransactionType.PURCHASE,
        )
    }

    private fun getScreenWidth() {
        _screenWidth.value = screenWidth.value
    }

    private fun getQuantity() {
        _quantity.value = quantity.value
    }

    private fun getMaxQuantity() {
        _maxQuantity.value = maxQuantity.value
    }

    private fun getShowAlertDialogOnRegisterTransaction() {
        _showAlertDialogOnRegisterTransaction.value = showAlertDialogOnRegisterTransaction.value
    }

    fun setShowAlertDialogOnRegisterTransaction(show: Boolean) {
        _showAlertDialogOnRegisterTransaction.value = show
    }

    fun getShowToast() {
        _showToast.value = showToast.value
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
        if (shouldUseDatabase.value) {
            CoroutineScope(Dispatchers.IO).launch {
                transactionRepository.insertTransaction(transaction.toTransactionEntity())
            }
        } else {
            _transactionValue.value = transaction
        }
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

        CoroutineScope(Dispatchers.IO).launch {
            updateProduct(listOfProducts.value[index])
        }
    }
}
