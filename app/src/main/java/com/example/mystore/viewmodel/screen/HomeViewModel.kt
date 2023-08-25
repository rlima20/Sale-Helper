package com.example.mystore.viewmodel.screen

import com.example.mystore.States
import com.example.mystore.model.Resume
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel : CommonViewModel() {

    private var _imageRequestState: MutableStateFlow<States> = MutableStateFlow(States.LOADING)
    val imageRequestState: MutableStateFlow<States> = _imageRequestState

    private val _resume: MutableStateFlow<Resume?> = MutableStateFlow(Resume())
    val resume: MutableStateFlow<Resume?> = _resume

    init {
        getResume()
        getListOfProducts()
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
            resume.value = null
        } else {
            resume.value = Resume(
                debits = debits,
                grossRevenue = grossRevenue,
                netRevenue = netRevenue,
                stockValue = stockValue,
            )
        }
    }

    fun setImageRequestState(state: States) {
        _imageRequestState.value = state
    }
}
