package com.example.mystore.commons.usecase

import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registertransaction.model.Transaction

interface CommonUseCase {
    suspend fun getListOfProducts(): List<Product>
    suspend fun getAllTransactions(): List<Transaction>
    suspend fun createProduct(product: Product)
    suspend fun updateProduct(product: Product)
    suspend fun deleteProduct(product: Product)
    suspend fun deleteTransaction(transaction: Transaction)
    suspend fun insertTransaction(transaction: Transaction)
    fun getListOfProductsLocal(): List<Product>
    fun getListOfTransactionsLocal(): List<Transaction>
}