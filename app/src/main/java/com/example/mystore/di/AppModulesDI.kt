package com.example.mystore.di

import com.example.mystore.commons.viewmodel.CommonViewModel
import com.example.mystore.features.homescreen.di.homeViewModelDI
import com.example.mystore.features.registerproduct.di.productDaoDI
import com.example.mystore.features.registerproduct.di.productRepositoryDI
import com.example.mystore.features.registerproduct.di.registerProductViewModelDI
import com.example.mystore.features.registertransaction.di.registerTransactionViewModelDI
import com.example.mystore.features.registertransaction.di.transactionDaoDI
import com.example.mystore.features.registertransaction.di.transactionRepositoryDI
import com.example.mystore.room.AppDatabase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseDI = module {
    single {
        AppDatabase.getInstance(get())
    }
}

val commonViewModelDI = module {
    viewModel { CommonViewModel(get(), get()) }
}

val appModules = listOf(
    databaseDI,
    productDaoDI,
    transactionDaoDI,
    productRepositoryDI,
    transactionRepositoryDI,
    commonViewModelDI,
    homeViewModelDI,
    registerTransactionViewModelDI,
    registerProductViewModelDI,
)
