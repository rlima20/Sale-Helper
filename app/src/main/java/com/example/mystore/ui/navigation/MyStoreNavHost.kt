package com.example.mystore.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mystore.model.Product
import com.example.mystore.navigateSingleTopTo
import com.example.mystore.ui.components.screens.ConsolidatedPositionScreen
import com.example.mystore.ui.components.screens.HomeScreen
import com.example.mystore.ui.components.screens.RegisterProductScreen
import com.example.mystore.ui.components.screens.RegisterTransactionScreen
import com.example.mystore.viewmodel.screen.HomeViewModel
import com.example.mystore.viewmodel.screen.RegisterProductViewModel
import com.example.mystore.viewmodel.screen.RegisterTransactionViewModel

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
) {
    var clearStates by remember { mutableStateOf(false) }
    var clearProductStates by remember { mutableStateOf(false) }

    NavHost(
        navController = navController,
        startDestination = HomeScreen.route,
        modifier = modifier,
    ) {
        // Navega para a HomeScreen
        composable(route = HomeScreen.route) {
            clearStates = true
            clearProductStates = true
            onExpandBottomBar(false)
            HomeScreen(
                homeViewModel = homeViewModel,
                shouldItemBeVisible = shouldItemBeVisible,
                onProductClick = { onProductClick(it) },
                onProductDoubleClick = { onProductDoubleClick() },
                onEmptyStateImageClicked = {
                    navController.navigateSingleTopTo(it)
                },
                onEditMode = { isEditMode, product ->
                    onEditMode(isEditMode, product)
                },
            )
        }

        // Navega para a ConsolidatedPositionScreen
        composable(route = ConsolidatedPositionScreen.route) {
            homeViewModel.getListOfSales()
            homeViewModel.getListOfPurchases()
            clearStates = true
            clearProductStates = true
            ConsolidatedPositionScreen(
                homeViewModel = homeViewModel,
                shouldItemBeVisible = shouldItemBeVisible,
                onShowBottomBarExpanded = {
                    val sales = homeViewModel.getSalesValue()
                    val purchases = homeViewModel.getPurchasesValue()
                    val listOfSales = homeViewModel.listOfSales.value
                    val listOfPurchases = homeViewModel.listOfPurchases.value

                    if (listOfSales.isNotEmpty() || listOfPurchases.isNotEmpty()) {
                        onExpandBottomBar(true)
                        onShowBottomBarExpanded(sales, purchases)
                    }
                },
                onEmptyStateImageClicked = {
                    navController.navigateSingleTopTo(it)
                },
            )
        }

        // Navega para a RegisterTransactionScreen
        composable(route = RegisterTransactionScreen.route) {
            onExpandBottomBar(false)
            clearProductStates = true
            RegisterTransactionScreen(
                registerTransactionViewModel = registerTransactionViewModel,
                shouldItemBeVisible = shouldItemBeVisible,
                clearAllStates = clearStates,
                onClearAllStates = { clearStates = it },
            )
        }

        // Navega para a RegisterProductScreen
        composable(route = RegisterProductScreen.route) {
            clearStates = true
            onExpandBottomBar(false)
            RegisterProductScreen(
                product = product,
                isEditMode = isEditMode,
                registerProductViewModel = registerProductViewModel,
                stateCleared = clearProductStates,
                onClearStates = { clearProductStates = it },
            )
        }
    }
}
