package com.example.mystore.features.registertransaction.model

import com.example.mystore.enums.TransactionType
import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registertransaction.viewmodel.RegisterTransactionViewModel

data class RegisterTransactionBodyProps(
    val screenWidth: Int,
    val shouldItemBeVisible: Boolean = true,
    val listOfProducts: List<Product>,
    val listOfTransactionTypes: List<TransactionType>,
    val quantity: Int,
    val maxQuantity: Int,
    val total: Double,
    val transaction: Transaction,
    val registerTransactionViewModel: RegisterTransactionViewModel,
    val showAlertDialog: Boolean = false,
    val onShowAlertDialog: (Boolean) -> Unit = {},
    val showToast: Boolean = false,
    val onShowToast: (Boolean) -> Unit = {},
)