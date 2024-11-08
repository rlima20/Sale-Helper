package com.example.mystore.features.homescreen.model

import com.example.mystore.features.homescreen.viewmodel.HomeViewModel
import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registertransaction.model.Transaction

data class HomeScreenProps(
    val homeViewModel: HomeViewModel,
    val shouldItemBeVisible: Boolean,
    val onProductClick: (product: Product) -> Unit = {},
    val onProductDoubleClick: () -> Unit = {},
    val onNavigateToEditTransactionScreen: (route: String, transaction: Transaction) -> Unit,
    val onEmptyStateImageClicked: (route: String, screen: String) -> Unit = { _: String, _: String -> },
    val onEditMode: (Boolean, Product) -> Unit = { _, _ -> },
)