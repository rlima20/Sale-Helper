package com.example.mystore.viewmodel

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    fun getResume() = Resume()
}

class Resume(
    val debits: Double = 100.0,
    val grossRevenue: Double = 200.0,
    val netRevenue: Double = 0.0,
    val stockValue: Double = 2000.0,
)
