package com.example.mystore.features.registertransaction.di

import com.example.mystore.features.registertransaction.datasource.TransactionLocalDataSource
import com.example.mystore.features.registertransaction.datasource.TransactionLocalDataSourceImpl
import com.example.mystore.features.registertransaction.repository.TransactionRepositoryImpl
import com.example.mystore.features.registertransaction.viewmodel.RegisterTransactionViewModel
import com.example.mystore.room.AppDatabase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

//val transactionDaoDI = module {
//    factory {
//        get<AppDatabase>().transactionDao()
//    }
//}

//val transactionLocalDataSourceDI = module {
//    factory {
//        TransactionLocalDataSourceImpl()
//    }
//}

//val transactionRepositoryDI = module {
//    factory {
//        TransactionRepositoryImpl(get())
//    }
//}

//val registerTransactionViewModelDI = module {
//    viewModel { RegisterTransactionViewModel(get(), get()) }
//}