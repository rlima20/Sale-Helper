package com.example.mystore.features.registerproduct.di

import com.example.mystore.features.registerproduct.repository.ProductRepositoryImpl
import com.example.mystore.features.registerproduct.viewmodel.RegisterProductViewModel
import com.example.mystore.room.AppDatabase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productDaoDI = module {
    factory {
        get<AppDatabase>().productDao()
    }
}

val productRepositoryDI = module {
    factory {
        ProductRepositoryImpl(get())
    }
}

val registerProductViewModelDI = module {
    viewModel { RegisterProductViewModel(get(), get()) }
}