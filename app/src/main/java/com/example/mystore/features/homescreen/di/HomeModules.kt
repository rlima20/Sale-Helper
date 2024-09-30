package com.example.mystore.features.homescreen.di

import com.example.mystore.features.homescreen.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeViewModelDI = module {
    viewModel { HomeViewModel(get(), get()) }
}
