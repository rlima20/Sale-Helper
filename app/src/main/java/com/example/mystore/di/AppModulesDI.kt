package com.example.mystore.di

import com.example.mystore.repository.ProductRepositoryImpl
import com.example.mystore.repository.TransactionRepositoryImpl
import com.example.mystore.room.AppDatabase
import com.example.mystore.viewmodel.global.GlobalViewModel
import com.example.mystore.viewmodel.screen.CommonViewModel
import com.example.mystore.viewmodel.screen.HomeViewModel
import com.example.mystore.viewmodel.screen.RegisterProductViewModel
import com.example.mystore.viewmodel.screen.RegisterTransactionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseDI = module {
    single {
        AppDatabase.getInstance(get())
    }
}

val productDaoDI = module {
    factory {
        get<AppDatabase>().productDao()
    }
}

val transactionDaoDI = module {
    factory {
        get<AppDatabase>().transactionDao()
    }
}

val productRepositoryDI = module {
    factory {
        ProductRepositoryImpl(get())
    }
}

val transactionRepositoryDI = module {
    factory {
        TransactionRepositoryImpl(get())
    }
}

val commonViewModelDI = module {
    viewModel { CommonViewModel(get(), get()) }
}

val myStoreViewModelDI = module {
    viewModel { GlobalViewModel() }
}

val homeViewModelDI = module {
    viewModel { HomeViewModel(get(), get()) }
}

val registerProductViewModelDI = module {
    viewModel { RegisterProductViewModel(get(), get()) }
}

val registerTransactionViewModelDI = module {
    viewModel { RegisterTransactionViewModel(get(), get()) }
}

val appModules = listOf(
    databaseDI,
    productDaoDI,
    transactionDaoDI,
    productRepositoryDI,
    transactionRepositoryDI,
    commonViewModelDI,
    myStoreViewModelDI,
    homeViewModelDI,
    registerTransactionViewModelDI,
    registerProductViewModelDI,
)
