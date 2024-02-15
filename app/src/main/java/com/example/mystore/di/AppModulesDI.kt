package com.example.mystore.di

import com.example.mystore.repository.ProductRepository
import com.example.mystore.repository.TransactionRepository
import com.example.mystore.room.AppDatabase
import com.example.mystore.viewmodel.global.MyStoreViewModel
import com.example.mystore.viewmodel.screen.CommonViewModel
import com.example.mystore.viewmodel.screen.HomeViewModel
import com.example.mystore.viewmodel.screen.RegisterProductViewModel
import com.example.mystore.viewmodel.screen.RegisterTransactionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val DATA_BASE_NAME = "mystore_database"

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
        ProductRepository(get())
    }
}

val transactionRepositoryDI = module {
    factory {
        TransactionRepository(get())
    }
}

val commonViewModelDI = module {
    viewModel { CommonViewModel(get(), get()) }
}

val myStoreViewModelDI = module {
    viewModel { MyStoreViewModel() }
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
