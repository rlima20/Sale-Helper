package com.example.mystore.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mystore.features.consolidatedposition.ui.ConsolidatedPositionScreen
import com.example.mystore.features.homescreen.ui.HomeScreen
import com.example.mystore.features.registerproduct.ui.RegisterProductScreen
import com.example.mystore.features.registertransaction.ui.RegisterTransactionScreen
import com.example.mystore.navigateSingleTopTo

@Composable
fun MyStoreNavHost(
    myStoreNavHostProps: MyStoreNavHostProps
) {

    var transactionClearStates by remember { mutableStateOf(false) }
    var productClearStates by remember { mutableStateOf(false) }

    with(myStoreNavHostProps) {
        NavHost(
            navController = navController,
            startDestination = HomeScreen.route,
            modifier = modifier,
        ) {
            // Navigates to Home Screen
            composable(route = HomeScreen.route) {
                NavigateToHomeScreen(
                    onTransactionClearAllState = { transactionClearStates = it },
                    onProductClearAllState = { productClearStates = it }
                )
            }

            // Navigates to ConsolidatedPositionScreen
            composable(route = ConsolidatedPositionScreen.route) {
                NavigateToConsolidatedPositionScreen(
                    onTransactionClearAllState = { transactionClearStates = it },
                    onProductClearAllState = { productClearStates = it }
                )
            }

            // Navigates to RegisterTransactionScreen
            composable(route = RegisterTransactionScreen.route) {
                NavigateToRegisterTransactionScreen(
                    productClearStates,
                    onClearAllStates = { transactionClearStates = it }
                )
            }

            // Navigates to RegisterProductScreen
            composable(route = RegisterProductScreen.route) {
                NavigateToRegisterProductScreen(
                    onClearStates = { productClearStates = it }
                )
            }
        }
    }
}

@Composable
private fun MyStoreNavHostProps.NavigateToRegisterProductScreen(
    onClearStates: (onClearStates: Boolean) -> Unit
) {
    onExpandBottomBar(false)
    onShouldDisplayIcon(false)
    RegisterProductScreen(
        product = product,
        isEditMode = isEditMode,
        registerProductViewModel = registerProductViewModel,
        onClearStates = { onClearStates(it) },
        onNavigateToHome = { onNavigateToHome() },
    )
}

@Composable
private fun MyStoreNavHostProps.NavigateToRegisterTransactionScreen(
    transactionClearStates: Boolean,
    onClearAllStates: (onClearAllStates: Boolean) -> Unit
) {
    onExpandBottomBar(false)
    onShouldDisplayIcon(true)
    RegisterTransactionScreen(
        registerTransactionViewModel = registerTransactionViewModel,
        shouldItemBeVisible = shouldItemBeVisible,
        clearAllStates = transactionClearStates,
        onClearAllStates = { onClearAllStates(it) },
    )
}

@Composable
private fun MyStoreNavHostProps.NavigateToConsolidatedPositionScreen(
    onTransactionClearAllState: (onTransactionClearAll: Boolean) -> Unit,
    onProductClearAllState: (onTransactionClearAll: Boolean) -> Unit
) {
    homeViewModel.getListOfSales()
    homeViewModel.getListOfPurchases()
    onTransactionClearAllState(true)
    onProductClearAllState(true)
    onShouldDisplayIcon(true)
    ConsolidatedPositionScreen(
        homeViewModel = homeViewModel,
        shouldItemBeVisible = shouldItemBeVisible,
        onShowBottomBarExpanded = {
            val sales = homeViewModel.getSalesValue()
            val purchases = homeViewModel.getPurchasesValue()
            val listOfSales =
                homeViewModel.commonViewState.listOfSales.value
            val listOfPurchases =
                homeViewModel.commonViewState.listOfPurchases.value

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

@Composable
private fun MyStoreNavHostProps.NavigateToHomeScreen(
    onTransactionClearAllState: (onTransactionClearAll: Boolean) -> Unit,
    onProductClearAllState: (onTransactionClearAll: Boolean) -> Unit
) {
    onTransactionClearAllState(true)
    onProductClearAllState(true)
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
