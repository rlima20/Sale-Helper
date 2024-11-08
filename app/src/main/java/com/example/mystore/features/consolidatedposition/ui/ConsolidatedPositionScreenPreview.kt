package com.example.mystore.features.consolidatedposition.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registertransaction.model.Transaction
import com.example.mystore.ui.components.commons.ConsolidatedPositionScreenSection
import com.example.mystore.ui.model.ColorProps

@Preview
@Composable
fun ScreenSectionComponentPartialPreview() {
    createTransactions()
    ConsolidatedPositionScreenSection(
        title = "Vendas",
        transactions = listOfTransactions,
        shouldItemBeVisible = true,
        colorProps = ColorProps(),
        onNavigateToTransactionEditScreen = {}
    )
}

val listOfTransactions: MutableList<Transaction> = mutableListOf()

fun createTransactions() {
    for (i in 1..5) {
        listOfTransactions.add(
            Transaction(
                product = Product(
                    productId = 0 + i,
                    title = "Produto $i",
                    description = "Description $i",
                    quantity = i,
                    maxQuantityToBuy = i,
                    purchasePrice = i.toDouble(),
                    salePrice = i.toDouble()
                ),
                transactionDate = "10/04/2024",
                unitValue = i.toDouble(),
                quantity = i,
                transactionAmount = i.toDouble()
            )
        )
    }
}