package com.example.mystore.commons.viewstate

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.MutableLiveData
import com.example.mystore.R
import com.example.mystore.commons.AppApplication
import com.example.mystore.enums.States
import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registertransaction.model.Transaction
import kotlinx.coroutines.flow.MutableStateFlow

class CommonViewState {
    private val application = AppApplication.instance

    val listOfProducts: MutableStateFlow<List<Product>> = MutableStateFlow(mutableListOf())
    val listOfSales: MutableStateFlow<List<Transaction>> = MutableStateFlow(listOf())
    val listOfPurchases: MutableStateFlow<List<Transaction>> = MutableStateFlow(listOf())
    val listOfTransactions: MutableStateFlow<List<Transaction>> = MutableStateFlow(mutableListOf())
    val imageRequestState: MutableStateFlow<States> = MutableStateFlow(States.LOADING)
    val shouldUseDatabase: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val screenTitle: MutableStateFlow<String> = MutableStateFlow(application.getString(R.string.my_store_home))
    val isMenuExpanded: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val textFieldSize: MutableStateFlow<Size> = MutableStateFlow(Size.Zero)
    val shouldItemBeVisible: MutableStateFlow<Boolean> = MutableStateFlow(true)
}