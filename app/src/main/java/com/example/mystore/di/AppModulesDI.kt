package com.example.mystore.di

import com.example.mystore.viewmodel.screen.ConsolidatedPosViewModel
import com.example.mystore.viewmodel.screen.HomeViewModel
import com.example.mystore.viewmodel.global.MyStoreViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myStoreViewModelDI = module {
    viewModel { MyStoreViewModel() }
}

val homeViewModelDI = module {
    viewModel { HomeViewModel() }
}

val consolidatePosViewModelDI = module {
    viewModel { ConsolidatedPosViewModel() }
}

val appModules = listOf(
    myStoreViewModelDI,
    homeViewModelDI,
    consolidatePosViewModelDI,
)
