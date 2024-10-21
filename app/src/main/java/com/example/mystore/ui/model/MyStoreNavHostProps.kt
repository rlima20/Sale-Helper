package com.example.mystore.ui.model

import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.mystore.features.homescreen.viewmodel.HomeViewModel
import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registerproduct.viewmodel.RegisterProductViewModel
import com.example.mystore.features.registertransaction.viewmodel.RegisterTransactionViewModel

data class MyStoreNavHostProps(
    val navController: NavHostController,
    val modifier: Modifier = Modifier,
    val homeViewModel: HomeViewModel,
    val registerTransactionViewModel: RegisterTransactionViewModel,
    val registerProductViewModel: RegisterProductViewModel,
    val isEditMode: Boolean,
    val product: Product,
    val shouldItemBeVisible: Boolean,
    val onExpandBottomBar: (Boolean) -> Unit = {},
    val onShowBottomBarExpanded: (sales: Double, purchase: Double) -> Unit = { _: Double, _: Double -> },
    val onProductClick: (product: Product) -> Unit = {},
    val onProductDoubleClick: () -> Unit = {},
    val onEditMode: (Boolean, Product) -> Unit = { _, _ -> },
    val onShouldDisplayIcon: (Boolean) -> Unit = {},
    val onNavigateToHome: () -> Unit = {},
    val onUpdateTopBarText: (text: String) -> Unit = {},
)