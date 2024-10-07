package com.example.mystore.commons.di

import com.example.mystore.commons.usecase.CommonUseCaseImpl
import com.example.mystore.commons.viewmodel.CommonViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

//val dispatcherDI = module {
//    single { Dispatchers.IO }
//}

//val commonViewModelDI = module(override = true) {
//    viewModel {
//        CommonViewModel(
//            commonUseCase = get(),
//            dispatcherProvider = get()
//        )
//    }
//}