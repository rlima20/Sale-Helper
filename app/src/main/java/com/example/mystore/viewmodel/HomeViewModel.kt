package com.example.mystore.viewmodel

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    fun getResume() = Resume()
}

class Resume(
    val debits: Double = 100.0,
    val grossRevenue: Double = 200.0,
    val netRevenue: Double = 100.0,
    val profit: Double = 100.0,
    val inStock: Int = 65,
)
