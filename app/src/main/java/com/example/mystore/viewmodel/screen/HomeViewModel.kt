package com.example.mystore.viewmodel.screen

import com.example.mystore.States
import com.example.mystore.model.Resume
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel : CommonViewModel() {

    private var _imageRequestState: MutableStateFlow<States> = MutableStateFlow(States.LOADING)
    val imageRequestState: MutableStateFlow<States> = _imageRequestState

    private val _resume: MutableStateFlow<Resume> = MutableStateFlow(Resume())
    val resume: MutableStateFlow<Resume> = _resume

    init {
        getResume()
        getListOfProducts()
    }

//    val debits: Double = 100.0,
//    val grossRevenue: Double = 200.0,
//    val netRevenue: Double = 0.0,
//    val stockValue: Double = 2000.0,

    // Todo - Aqui eu vou pegar as transações e produtos e manipular os dados para criar um objeto Resume
    private fun getResume() {
        val debits = listOfPurchases.value.sumOf { transaction ->
            transaction.transactionAmount
        }

        val grossRevenue = listOfSales.value.sumOf { transaction ->
            transaction.transactionAmount
        }

        val netRevenue = grossRevenue - debits

        val stockValue = listOfProducts.value.sumOf { product ->
            product.salePrice * product.quantity
        }

        resume.value = Resume(
            debits = debits,
            grossRevenue = grossRevenue,
            netRevenue = netRevenue,
            stockValue = stockValue,
        )
    }

    fun setImageRequestState(state: States) {
        _imageRequestState.value = state
    }
}
