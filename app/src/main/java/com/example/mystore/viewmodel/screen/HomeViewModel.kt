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

    // Todo - Aqui eu vou pegar as transações e produtos e manipular os dados para criar um objeto Resume
    private fun getResume() {
        resume.value = Resume()
    }

    fun setImageRequestState(state: States) {
        _imageRequestState.value = state
    }
}
