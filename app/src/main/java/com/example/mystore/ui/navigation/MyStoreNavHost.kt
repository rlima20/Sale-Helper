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
import com.example.mystore.viewmodel.ConsolidatedPosViewModel
import com.example.mystore.viewmodel.HomeViewModel

@Composable
fun MyStoreNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    consolidatedPosViewModel: ConsolidatedPosViewModel,
    shouldItemBeVisible: Boolean,
    onExpandBottomBar: (Boolean) -> Unit = {},
    onComponent: (sales: Double, purchase: Double) -> Unit = { sales: Double, purchase: Double -> },
    onClick: (product: Product) -> Unit = {},
    onLongClick: () -> Unit = {},
    onDoubleClick: () -> Unit = {},
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreen.route,
        modifier = modifier,
    ) {
        // Navega para a HomeScreen
        composable(route = HomeScreen.route) {
            onExpandBottomBar(false)
            HomeScreen(
                homeViewModel = homeViewModel,
                shouldItemBeVisible = shouldItemBeVisible,
                onClick = { onClick(it) },
                onLongClick = { onLongClick() },
                onDoubleClick = { onDoubleClick() },
                onEmptyStateImageClicked = {
                    navController.navigateSingleTopTo(it)
                },
            )
        }

        // Navega para a RegisterProductScreen
        composable(route = RegisterProductScreen.route) {
            onExpandBottomBar(false)
            RegisterScreen()
        }

        // Navega para a ConsolidatedPositionScreen
        composable(route = ConsolidatedPositionScreen.route) {
            onExpandBottomBar(true)
            ConsolidatedPositionScreen(
                consolidatedPosViewModel = consolidatedPosViewModel,
                shouldItemBeVisible = shouldItemBeVisible,
                onComponentCanBeSeen = {
                    onComponent(
                        consolidatedPosViewModel.getSalesValue(),
                        0.0,
                    )
                },
            )

            /*            val sales = consolidatedPosViewModel.getSalesValue()
                        val purchases = consolidatedPosViewModel.getPurchases()

                        TotalComponent(
                            salesValue = sales,
                            purchasesValue = 0.0,
                            shouldItemBeVisible = shouldItemBeVisible,
                        )*/
        }

        // Navega para a RegisterTransactionScreen
        composable(route = RegisterTransactionScreen.route) {
            onExpandBottomBar(false)
            RegisterTransactionScreen()
        }
    }
}
