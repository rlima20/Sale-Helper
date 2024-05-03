package com.example.mystore.model.props

import com.example.mystore.viewmodel.screen.HomeViewModel
import com.example.mystore.viewmodel.screen.RegisterProductViewModel
import com.example.mystore.viewmodel.screen.RegisterTransactionViewModel

data class ViewModels(
    val homeViewModel: HomeViewModel,
    val registerTransactionViewModel: RegisterTransactionViewModel,
    val registerProductViewModel: RegisterProductViewModel,
)
