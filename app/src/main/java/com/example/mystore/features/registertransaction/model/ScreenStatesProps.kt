package com.example.mystore.features.registertransaction.model

import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registertransaction.viewmodel.RegisterTransactionViewModel

class RegisterTransactionScreenStatesProps(
    val listOfProducts: List<Product>,
    val selectedTextTransaction: String,
    val quantity: Int,
    val maxQuantity: Int,
    val productName: String,
    val registerTransactionViewModel: RegisterTransactionViewModel,
    val itemSelected: String = "",
    val quantifierQuantity: Int? = null,
    val callbacks: Callbacks
)


class Callbacks(
    val onQuantifierQuantity: (Int) -> Unit = {},
    val onSetQuantity: (Int, Transaction) -> Unit = { _, _ -> },
    val onSelectedTextTransaction: (String) -> Unit = {},
)