package com.example.mystore.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mystore.listOfTransactions
import com.example.mystore.model.Product
import com.example.mystore.model.TransactionType
import java.util.Date

class ConsolidatedPosViewModel : ViewModel() {

    fun getTransactions() = listOfTransactions

    class Transaction(
        val product: Product,
        val transactionType: TransactionType,
        val unitValue: Double = 0.0,
        val transactionDate: Date,
        val quantity: Int = 0,
        val transactionAmount: Double = 0.0,
    )

    fun getListOfSales(): List<Transaction> {
        return listOfTransactions.filter { it.transactionType == TransactionType.SALE }
    }

    fun getSalesValue(): Double {
        var totalValue = 0.0

        listOfTransactions.filter { transaction ->
            transaction.transactionType == TransactionType.SALE
        }.forEach { transaction ->
            totalValue += transaction.transactionAmount
        }

        return totalValue
    }



    fun getPurchases(): List<Transaction> {
        return listOfTransactions.filter { it.transactionType == TransactionType.PURCHASE }
    }

    // get the total amount of sales less the total amount of purchases
    fun getTotal(): Double {
        val sales = getListOfSales()
        val purchases = getPurchases()
        val salesTotal = sales.sumOf { it.transactionAmount }
        val purchasesTotal = purchases.sumOf { it.transactionAmount }
        return salesTotal - purchasesTotal
    }
}
