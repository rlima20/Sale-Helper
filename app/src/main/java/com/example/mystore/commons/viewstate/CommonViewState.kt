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

class CommonViewState {
    private val application = AppApplication.instance

    val listOfProducts: MutableLiveData<List<Product>> = MutableLiveData(mutableListOf())
    val listOfSales: MutableLiveData<List<Transaction>> = MutableLiveData(listOf())
    val listOfPurchases: MutableLiveData<List<Transaction>> = MutableLiveData(listOf())
    val listOfTransactions: MutableLiveData<List<Transaction>> = MutableLiveData(mutableListOf())
    val imageRequestState: MutableLiveData<States> = MutableLiveData(States.LOADING)
    val shouldUseDatabase: MutableState<Boolean> = mutableStateOf(true)
    val screenTitle: MutableLiveData<String> = MutableLiveData(application.getString(R.string.my_store_home),)
    val isMenuExpanded: MutableLiveData<Boolean> = MutableLiveData(false)
    val textFieldSize: MutableLiveData<Size> = MutableLiveData(Size.Zero)
    val shouldItemBeVisible: MutableLiveData<Boolean> = MutableLiveData(true)
}