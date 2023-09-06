package com.example.mystore.di

import com.example.mystore.viewmodel.global.MyStoreViewModel
import com.example.mystore.viewmodel.screen.CommonViewModel
import com.example.mystore.viewmodel.screen.HomeViewModel
import com.example.mystore.viewmodel.screen.RegisterProductViewModel
import com.example.mystore.viewmodel.screen.RegisterTransactionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val commonViewModel = module {
    viewModel { CommonViewModel() }
}

val myStoreViewModelDI = module {
    viewModel { MyStoreViewModel() }
}

val homeViewModelDI = module {
    viewModel { HomeViewModel() }
}

val registerTransactionViewModel = module {
    viewModel { RegisterTransactionViewModel() }
}

val registerProductViewModel = module {
    viewModel { RegisterProductViewModel() }
}

val appModules = listOf(
    commonViewModel,
    myStoreViewModelDI,
    homeViewModelDI,
    registerTransactionViewModel,
    registerProductViewModel,
)
