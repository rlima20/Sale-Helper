package com.example.mystore.di

import androidx.room.Room
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

val database = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            DATA_BASE_NAME,
        )
            .build()
    }
    single { get<AppDatabase>().productDao() }
    single { get<AppDatabase>().transactionDao() }
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

val commonViewModel = module {
    viewModel { CommonViewModel() }
}

val myStoreViewModelDI = module {
    viewModel { MyStoreViewModel() }
}

val homeViewModelDI = module {
    viewModel { HomeViewModel() }
}

val registerProductViewModelDI = module {
    viewModel { RegisterProductViewModel() }
}

val registerTransactionViewModelDI = module {
    viewModel { RegisterTransactionViewModel() }
}

val appModules = listOf(
    database,
    productRepositoryDI,
    transactionRepositoryDI,
    commonViewModel,
    myStoreViewModelDI,
    homeViewModelDI,
    registerTransactionViewModelDI,
    registerProductViewModelDI,
)
