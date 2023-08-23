package com.example.mystore.viewmodel.screen

import com.example.mystore.listOfTransactions

class ConsolidatedPosViewModel : CommonViewModel() {

    init {
        getTransactions()
    }

    private fun getTransactions() {
        transactions.value = listOfTransactions
    }
}
