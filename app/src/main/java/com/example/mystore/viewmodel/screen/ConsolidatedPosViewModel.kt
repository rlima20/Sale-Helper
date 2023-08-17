package com.example.mystore.viewmodel.screen

import com.example.mystore.listOfTransactions

class ConsolidatedPosViewModel : CommomViewModel() {

    init {
        getTransactions()
        getListOfSales()
        getListOfPurchases()
    }

    private fun getTransactions() {
        transactions.value = listOfTransactions
    }
}
