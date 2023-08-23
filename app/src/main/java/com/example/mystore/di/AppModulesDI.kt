package com.example.mystore.di

import com.example.mystore.viewmodel.global.MyStoreViewModel
import com.example.mystore.viewmodel.screen.CommonViewModel
import com.example.mystore.viewmodel.screen.ConsolidatedPosViewModel
import com.example.mystore.viewmodel.screen.HomeViewModel
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

val consolidatePosViewModelDI = module {
    viewModel { ConsolidatedPosViewModel() }
}

val registerTransactionViewModel = module {
    viewModel { RegisterTransactionViewModel() }
}

val appModules = listOf(
    commonViewModel,
    myStoreViewModelDI,
    homeViewModelDI,
    consolidatePosViewModelDI,
    registerTransactionViewModel,
    consolidatePosViewModelDI,
)
