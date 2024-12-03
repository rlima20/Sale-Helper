package com.example.mystore.di

import com.example.mystore.commons.usecase.CommonUseCase
import com.example.mystore.commons.usecase.CommonUseCaseImpl
import com.example.mystore.commons.viewmodel.CommonViewModel
import com.example.mystore.features.consolidatedposition.viewmodel.ConsolidatedPositionViewModel
import com.example.mystore.features.homescreen.viewmodel.HomeViewModel
import com.example.mystore.features.registerproduct.datasource.local.ProductLocalDataSource
import com.example.mystore.features.registerproduct.datasource.local.ProductLocalDataSourceImpl
import com.example.mystore.features.registerproduct.repository.ProductRepository
import com.example.mystore.features.registerproduct.repository.ProductRepositoryImpl
import com.example.mystore.features.registerproduct.viewmodel.RegisterProductViewModel
import com.example.mystore.features.registertransaction.datasource.TransactionLocalDataSource
import com.example.mystore.features.registertransaction.datasource.TransactionLocalDataSourceImpl
import com.example.mystore.features.registertransaction.repository.TransactionRepository
import com.example.mystore.features.registertransaction.repository.TransactionRepositoryImpl
import com.example.mystore.features.registertransaction.viewmodel.RegisterTransactionViewModel
import com.example.mystore.features.updatetransaction.repository.UpdateTransactionRepository
import com.example.mystore.features.updatetransaction.repository.UpdateTransactionRepositoryImpl
import com.example.mystore.features.updatetransaction.usecase.UpdateTransactionUseCase
import com.example.mystore.features.updatetransaction.usecase.UpdateTransactionUseCaseImpl
import com.example.mystore.features.updatetransaction.viewmodel.UpdateTransactionViewModel
import com.example.mystore.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dispatcherDI = module {
    single<Dispatchers> { Dispatchers }
}

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

val updateTransactionDaoDI = module {
    factory {
        get<AppDatabase>().updateTransactionDao()
    }
}

val productLocalDataSourceDI = module {
    factory<ProductLocalDataSource> {
        ProductLocalDataSourceImpl()
    }
}

val transactionLocalDataSourceDI = module {
    factory<TransactionLocalDataSource> {
        TransactionLocalDataSourceImpl()
    }
}

val productRepositoryDI = module {
    factory<ProductRepository> {
        ProductRepositoryImpl(get(), get())
    }
}

val transactionRepositoryDI = module {
    factory<TransactionRepository> {
        TransactionRepositoryImpl(get())
    }
}

val updateTransactionRepositoryDI = module {
    factory<UpdateTransactionRepository> {
        UpdateTransactionRepositoryImpl(get())
    }
}

val commonUseCaseDI = module {
    factory<CommonUseCase> { CommonUseCaseImpl(get(), get()) }
}

val updateTransactionUseCaseDI = module {
    factory<UpdateTransactionUseCase> {
        UpdateTransactionUseCaseImpl(get())
    }
}

val homeViewModelDI = module {
    viewModel { HomeViewModel(get(), get()) }
}

val commonViewModelDI = module {
    viewModel {
        CommonViewModel(
            commonUseCase = get(),
            dispatcherProvider = get()
        )
    }
}

val consolidatedPositionViewModelDI = module {
    viewModel { ConsolidatedPositionViewModel() }
}

val registerTransactionViewModelDI = module {
    viewModel { RegisterTransactionViewModel(get(), get()) }
}

val registerProductViewModelDI = module {
    viewModel { RegisterProductViewModel(get(), get()) }
}

val updateTransactionViewModelDI = module {
    viewModel { UpdateTransactionViewModel(get(), get(), get()) }
}

val appModules = listOf(
    dispatcherDI,
    databaseDI,
    productDaoDI,
    transactionDaoDI,
    updateTransactionDaoDI,
    productLocalDataSourceDI,
    transactionLocalDataSourceDI,
    productRepositoryDI,
    transactionRepositoryDI,
    updateTransactionRepositoryDI,
    commonUseCaseDI,
    updateTransactionUseCaseDI,
    commonViewModelDI,
    homeViewModelDI,
    registerTransactionViewModelDI,
    registerProductViewModelDI,
    consolidatedPositionViewModelDI,
    updateTransactionViewModelDI,
)
