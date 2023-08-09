package com.example.mystore.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import coil.request.ImageRequest
import coil.size.Size
import com.example.mystore.States
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel : ViewModel() {

    private var _imageRequestState: MutableStateFlow<States> = MutableStateFlow(States.LOADING)
    val imageRequestState: MutableStateFlow<States> = _imageRequestState

    fun setImageRequestState(state: States) {
        _imageRequestState.value = state
    }

    fun getResume() = Resume()

    class Resume(
        val debits: Double = 100.0,
        val grossRevenue: Double = 200.0,
        val netRevenue: Double = 0.0,
        val stockValue: Double = 2000.0,
    )
}
