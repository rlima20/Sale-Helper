package com.example.mystore.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mystore.model.Product
import com.example.mystore.navigateSingleTopTo
import com.example.mystore.ui.components.screens.ConsolidatedPositionScreen
import com.example.mystore.ui.components.screens.HomeScreen
import com.example.mystore.ui.components.screens.RegisterScreen
import com.example.mystore.ui.components.screens.RegisterTransactionScreen
import com.example.mystore.viewmodel.screen.ConsolidatedPosViewModel
import com.example.mystore.viewmodel.screen.HomeViewModel
import com.example.mystore.viewmodel.screen.RegisterTransactionViewModel

@Composable
fun MyStoreNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    consolidatedPosViewModel: ConsolidatedPosViewModel,
    registerTransactionViewModel: RegisterTransactionViewModel,
    shouldItemBeVisible: Boolean,
    onExpandBottomBar: (Boolean) -> Unit = {},
    onShowBottomBarExpanded: (sales: Double, purchase: Double) -> Unit = { _: Double, _: Double -> },
    onProductClick: (product: Product) -> Unit = {},
    onProductLongClick: () -> Unit = {},
    onProductDoubleClick: () -> Unit = {},
    onShowFab: (Boolean) -> Unit = {},
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreen.route,
        modifier = modifier,
    ) {
        // Navega para a HomeScreen
        composable(route = HomeScreen.route) {
            onExpandBottomBar(false)
            onShowFab(false)
            HomeScreen(
                homeViewModel = homeViewModel,
                shouldItemBeVisible = shouldItemBeVisible,
                onProductClick = { onProductClick(it) },
                onProductLongClick = { onProductLongClick() },
                onProductDoubleClick = { onProductDoubleClick() },
                onEmptyStateImageClicked = {
                    navController.navigateSingleTopTo(it)
                },
            )
        }

        // Navega para a RegisterProductScreen
        composable(route = RegisterProductScreen.route) {
            onExpandBottomBar(false)
            onShowFab(true)
            RegisterScreen()
        }

        // Navega para a ConsolidatedPositionScreen
        composable(route = ConsolidatedPositionScreen.route) {
            onExpandBottomBar(true)
            onShowFab(false)
            ConsolidatedPositionScreen(
                consolidatedPosViewModel = consolidatedPosViewModel,
                shouldItemBeVisible = shouldItemBeVisible,
                onShowBottomBarExpanded = {
                    val sales = consolidatedPosViewModel.getSalesValue()
                    val purchases = consolidatedPosViewModel.getPurchasesValue()
                    val listOfSales = consolidatedPosViewModel.listOfSales.value
                    val listOfPurchases = consolidatedPosViewModel.listOfPurchases.value

                    if (listOfSales.isNotEmpty() && listOfPurchases.isNotEmpty()) {
                        onExpandBottomBar(true)
                        onShowBottomBarExpanded(sales, purchases)
                    }
                },
            )
        }

        // Navega para a RegisterTransactionScreen
        composable(route = RegisterTransactionScreen.route) {
            onExpandBottomBar(false)
            onShowFab(true)
            RegisterTransactionScreen(
                registerTransactionViewModel = registerTransactionViewModel,
                shouldItemBeVisible = shouldItemBeVisible,
            )
        }
    }
}
