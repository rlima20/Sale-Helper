package com.example.mystore.di

import com.example.mystore.dao.AppDatabase
import com.example.mystore.repository.ProductRepository
import com.example.mystore.repository.TransactionRepository
import com.example.mystore.viewmodel.global.MyStoreViewModel
import com.example.mystore.viewmodel.screen.CommonViewModel
import com.example.mystore.viewmodel.screen.HomeViewModel
import com.example.mystore.viewmodel.screen.RegisterProductViewModel
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

val registerTransactionViewModel = module {
    viewModel { RegisterTransactionViewModel() }
}

val registerProductViewModel = module {
    viewModel { RegisterProductViewModel() }
}

val appDatabaseDI = module {
    single { AppDatabase.getDatabase(get()) }
}

val productDaoDI = module {
    factory { AppDatabase.getDatabase(get()).productDao() }
}

val transactionDaoDI = module {
    factory { AppDatabase.getDatabase(get()).transactionDao() }
}

val productRepositoryDI = module {
    factory { ProductRepository(get()) }
}

val transactionRepositoryDI = module {
    factory { TransactionRepository(get()) }
}

val appModules = listOf(
    commonViewModel,
    myStoreViewModelDI,
    homeViewModelDI,
    registerTransactionViewModel,
    registerProductViewModel,
    appDatabaseDI,
    productDaoDI,
    transactionDaoDI,
    productRepositoryDI,
    transactionRepositoryDI,
)
