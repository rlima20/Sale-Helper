package com.example.salehelper.di

import com.example.salehelper.viewmodel.SaleHelperViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val gameNewsViewModelDI = module {
    viewModel { SaleHelperViewModel() }
}

val appModules = listOf(
    gameNewsViewModelDI,
)
