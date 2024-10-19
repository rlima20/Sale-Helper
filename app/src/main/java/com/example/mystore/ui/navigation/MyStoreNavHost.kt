package com.example.mystore.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mystore.features.consolidatedposition.ui.ConsolidatedPositionScreen
import com.example.mystore.features.homescreen.ui.HomeScreen
import com.example.mystore.features.homescreen.viewmodel.HomeViewModel
import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registerproduct.ui.RegisterProductScreen
import com.example.mystore.features.registerproduct.viewmodel.RegisterProductViewModel
import com.example.mystore.features.registertransaction.ui.RegisterTransactionScreen
import com.example.mystore.features.registertransaction.viewmodel.RegisterTransactionViewModel
import com.example.mystore.navigateSingleTopTo

@Composable
fun MyStoreNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    registerTransactionViewModel: RegisterTransactionViewModel,
    registerProductViewModel: RegisterProductViewModel,
    isEditMode: Boolean,
    product: Product,
    shouldItemBeVisible: Boolean,
    onExpandBottomBar: (Boolean) -> Unit = {},
    onShowBottomBarExpanded: (sales: Double, purchase: Double) -> Unit = { _: Double, _: Double -> },
    onProductClick: (product: Product) -> Unit = {},
    onProductDoubleClick: () -> Unit = {},
    onEditMode: (Boolean, Product) -> Unit = { _, _ -> },
    onShouldDisplayIcon: (Boolean) -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onUpdateTopBarText: (text: String) -> Unit = {},
) {
    var transactionClearStates by remember { mutableStateOf(false) }
    var productClearStates by remember { mutableStateOf(false) }

    NavHost(
        navController = navController,
        startDestination = HomeScreen.route,
        modifier = modifier,
    ) {
        // Navega para a HomeScreen
        composable(route = HomeScreen.route) {
            transactionClearStates = true
            productClearStates = true
            onExpandBottomBar(false)
            onShouldDisplayIcon(true)
            HomeScreen(
                homeViewModel = homeViewModel,
                shouldItemBeVisible = shouldItemBeVisible,
                onProductClick = { onProductClick(it) },
                onProductDoubleClick = { onProductDoubleClick() },
                onEmptyStateImageClicked = { route, screen ->
                    navController.navigateSingleTopTo(route)
                    onUpdateTopBarText(screen)
                },
                onEditMode = { isEditMode, product -> onEditMode(isEditMode, product) },
            )
        }

        // Navega para a ConsolidatedPositionScreen
        composable(route = ConsolidatedPositionScreen.route) {
            homeViewModel.getListOfSales()
            homeViewModel.getListOfPurchases()
            transactionClearStates = true
            productClearStates = true
            onShouldDisplayIcon(true)
            ConsolidatedPositionScreen(
                homeViewModel = homeViewModel,
                shouldItemBeVisible = shouldItemBeVisible,
                onShowBottomBarExpanded = {
                    val sales = homeViewModel.getSalesValue()
                    val purchases = homeViewModel.getPurchasesValue()
                    val listOfSales = homeViewModel.commonViewState.listOfSales.value ?: emptyList()
                    val listOfPurchases =
                        homeViewModel.commonViewState.listOfPurchases.value ?: emptyList()

                    if (listOfSales.isNotEmpty() || listOfPurchases.isNotEmpty()) {
                        onExpandBottomBar(true)
                        onShowBottomBarExpanded(sales, purchases)
                    }
                },
                onEmptyStateImageClicked = { route, screen ->
                    navController.navigateSingleTopTo(route)
                    onUpdateTopBarText(screen)
                },
            )
        }

        // Navega para a RegisterTransactionScreen
        composable(route = RegisterTransactionScreen.route) {
            onExpandBottomBar(false)
            productClearStates = true
            onShouldDisplayIcon(true)
            RegisterTransactionScreen(
                registerTransactionViewModel = registerTransactionViewModel,
                shouldItemBeVisible = shouldItemBeVisible,
                clearAllStates = transactionClearStates,
                onClearAllStates = { transactionClearStates = it },
            )
        }

        // Navega para a RegisterProductScreen
        composable(route = RegisterProductScreen.route) {
            transactionClearStates = true
            onExpandBottomBar(false)
            onShouldDisplayIcon(false)
            RegisterProductScreen(
                product = product,
                isEditMode = isEditMode,
                registerProductViewModel = registerProductViewModel,
                onClearStates = { productClearStates = it },
                onNavigateToHome = { onNavigateToHome() },
            )
        }
    }
}
