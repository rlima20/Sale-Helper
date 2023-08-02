package com.example.mystore.di

import com.example.mystore.viewmodel.MyStoreViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myStoreViewModelDI = module {
    viewModel { MyStoreViewModel() }
}

val appModules = listOf(
    myStoreViewModelDI,
)
