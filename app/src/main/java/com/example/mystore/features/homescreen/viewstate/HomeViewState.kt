package com.example.mystore.features.homescreen.viewstate

import com.example.mystore.features.homescreen.model.Resume
import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registertransaction.model.Transaction
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewState {
    val resume: MutableStateFlow<Resume?> = MutableStateFlow(Resume())
    val showAlertDialogHomeScreen: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showAlertDialogHomeScreenProduct: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val showAlertDialogTransactionDetail: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val showToast: MutableStateFlow<Pair<String, Boolean>> =
        MutableStateFlow(Pair("", false))
    val transaction: MutableStateFlow<Transaction> = MutableStateFlow(Transaction())
    val product: MutableStateFlow<Product> = MutableStateFlow(Product())
}