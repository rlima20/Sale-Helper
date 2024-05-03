package com.example.mystore.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mystore.model.props.GlobalProps
import com.example.mystore.model.props.NavigationProps
import com.example.mystore.model.props.ProductProps
import com.example.mystore.model.props.ViewModels
import com.example.mystore.navigateSingleTopTo
import com.example.mystore.ui.components.screens.ConsolidatedPositionScreen
import com.example.mystore.ui.components.screens.HomeScreen
import com.example.mystore.ui.components.screens.RegisterProductScreen
import com.example.mystore.ui.components.screens.RegisterTransactionScreen

@Composable
fun MyStoreNavHost(
    viewModels: ViewModels,
    globalProps: GlobalProps,
    productProps: ProductProps,
    navigationProps: NavigationProps,
) {
    var transactionClearStates by remember { mutableStateOf(false) }
    var productClearStates by remember { mutableStateOf(false) }

    NavHost(
        navController = navigationProps.navController,
        startDestination = HomeScreen.route,
        modifier = globalProps.modifier,
    ) {
        // Navega para a HomeScreen
        composable(route = HomeScreen.route) {
            transactionClearStates = true
            productClearStates = true
            globalProps.onExpandBottomBar(false)
            HomeScreen(
                homeViewModel = viewModels.homeViewModel,
                shouldItemBeVisible = globalProps.shouldItemBeVisible,
                onProductClick = { productProps.onProductClick(it) },
                onProductDoubleClick = { productProps.onProductDoubleClick() },
                onEmptyStateImageClicked = { route, screen ->
                    navigationProps.navController.navigateSingleTopTo(route)
                    globalProps.onUpdateTopBarText(screen)
                },
                onEditMode = { isEditMode, product ->
                    globalProps.onEditMode(isEditMode, product)
                },
            )
        }

        // Navega para a ConsolidatedPositionScreen
        composable(route = ConsolidatedPositionScreen.route) {
            viewModels.homeViewModel.getListOfSales()
            viewModels.homeViewModel.getListOfPurchases()
            transactionClearStates = true
            productClearStates = true
            ConsolidatedPositionScreen(
                homeViewModel = viewModels.homeViewModel,
                shouldItemBeVisible = globalProps.shouldItemBeVisible,
                onShowBottomBarExpanded = {
                    val sales = viewModels.homeViewModel.getSalesValue()
                    val purchases = viewModels.homeViewModel.getPurchasesValue()
                    val listOfSales = viewModels.homeViewModel.listOfSales.value
                    val listOfPurchases = viewModels.homeViewModel.listOfPurchases.value

                    if (listOfSales.isNotEmpty() || listOfPurchases.isNotEmpty()) {
                        globalProps.onExpandBottomBar(true)
                        globalProps.onShowBottomBarExpanded(sales, purchases)
                    }
                },
                onEmptyStateImageClicked = { route, screen ->
                    navigationProps.navController.navigateSingleTopTo(route)
                    globalProps.onUpdateTopBarText(screen)
                },
            )
        }

        // Navega para a RegisterTransactionScreen
        composable(route = RegisterTransactionScreen.route) {
            globalProps.onExpandBottomBar(false)
            productClearStates = true
            RegisterTransactionScreen(
                registerTransactionViewModel = viewModels.registerTransactionViewModel,
                shouldItemBeVisible = globalProps.shouldItemBeVisible,
                clearAllStates = transactionClearStates,
                onClearAllStates = { transactionClearStates = it },
            )
        }

        // Navega para a RegisterProductScreen
        composable(route = RegisterProductScreen.route) {
            transactionClearStates = true
            globalProps.onExpandBottomBar(false)
            RegisterProductScreen(
                product = productProps.product,
                isEditMode = globalProps.isEditMode,
                registerProductViewModel = viewModels.registerProductViewModel,
                onClearStates = { productClearStates = it },
                onNavigateToHome = { navigationProps.onNavigateToHome() },
            )
        }
    }
}
