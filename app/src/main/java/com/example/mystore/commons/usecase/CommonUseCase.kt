package com.example.mystore.commons.usecase

import com.example.mystore.features.registerproduct.datasource.room.entities.ProductEntity
import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registertransaction.model.Transaction
import com.example.mystore.features.registertransaction.room.entities.TransactionEntity
import kotlinx.coroutines.flow.Flow

interface CommonUseCase {
    suspend fun getAllProducts(): Flow<List<ProductEntity>>
    suspend fun getAllTransactions(): Flow<List<TransactionEntity>>
    suspend fun createProduct(product: Product)
    suspend fun updateProduct(product: Product)
    suspend fun deleteProduct(product: Product)
    suspend fun deleteTransaction(transaction: Transaction)
    suspend fun insertTransaction(transaction: Transaction)
    fun getListOfProductsLocal(): List<Product>
    fun getListOfTransactionsLocal(): List<Transaction>
}