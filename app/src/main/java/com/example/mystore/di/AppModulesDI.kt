package com.example.mystore.di

import com.example.mystore.viewmodel.HomeViewModel
import com.example.mystore.viewmodel.MyStoreViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myStoreViewModelDI = module {
    viewModel { MyStoreViewModel() }
}

val homeViewModelDI = module {
    viewModel { HomeViewModel() }
}

val appModules = listOf(
    myStoreViewModelDI,
    homeViewModelDI,
)
