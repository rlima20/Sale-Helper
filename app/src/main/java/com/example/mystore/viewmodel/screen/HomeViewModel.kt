package com.example.mystore.viewmodel.screen

import androidx.lifecycle.ViewModel
import com.example.mystore.States
import com.example.mystore.listOfProductsLocal
import com.example.mystore.model.Product
import com.example.mystore.model.Resume
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel : ViewModel() {

    private var _imageRequestState: MutableStateFlow<States> = MutableStateFlow(States.LOADING)
    val imageRequestState: MutableStateFlow<States> = _imageRequestState

    private val _resume: MutableStateFlow<Resume> = MutableStateFlow(Resume())
    val resume: MutableStateFlow<Resume> = _resume

    private val _listOfProducts: MutableStateFlow<List<Product>> = MutableStateFlow(listOf())
    val listOfProducts: MutableStateFlow<List<Product>> = _listOfProducts

    init {
        getResume()
        getListOfProducts()
    }

    private fun getResume() {
        resume.value = Resume()
    }

    private fun getListOfProducts() {
        listOfProducts.value = listOfProductsLocal
    }

    fun setImageRequestState(state: States) {
        _imageRequestState.value = state
    }
}
