package com.example.mystore.commons.usecase

import com.example.mystore.features.registerproduct.mappers.toListOfProduct
import com.example.mystore.features.registerproduct.mappers.toProductEntity
import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registerproduct.repository.ProductRepository
import com.example.mystore.features.registertransaction.mappers.toListOfTransaction
import com.example.mystore.features.registertransaction.mappers.toTransactionEntity
import com.example.mystore.features.registertransaction.model.Transaction
import com.example.mystore.features.registertransaction.repository.TransactionRepository
import kotlinx.coroutines.flow.first

class CommonUseCaseImpl(
    private val transactionRepository: TransactionRepository,
    private val productRepository: ProductRepository,
) : CommonUseCase {

    override suspend fun getAllProducts(): List<Product> {
        var listOfProducts: List<Product> = mutableListOf()
        productRepository.getAllProducts().collect {
            listOfProducts = it.toListOfProduct()
        }
        return listOfProducts
    }

    override suspend fun getAllTransactions(): List<Transaction>{
        var listOfTransactions: List<Transaction> = mutableListOf()
        transactionRepository.getAllTransactions().collect{ listOfTransactions = it.toListOfTransaction() }
        return listOfTransactions
    }

    override fun getListOfProductsLocal(): List<Product> =
        productRepository.getAllProductsLocal()

    override fun getListOfTransactionsLocal(): List<Transaction> =
        transactionRepository.getAllTransactionsLocal()

    override suspend fun createProduct(product: Product) {
        productRepository.insertProduct(product.toProductEntity())
    }

    override suspend fun updateProduct(product: Product) {
        productRepository.updateProduct(product.toProductEntity())
    }

    override suspend fun deleteProduct(product: Product) {
        productRepository.deleteProduct(product.toProductEntity())
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        transactionRepository.deleteTransaction(transaction.toTransactionEntity())
    }

    override suspend fun insertTransaction(transaction: Transaction) {
        transactionRepository.insertTransaction(transaction.toTransactionEntity())
    }
}