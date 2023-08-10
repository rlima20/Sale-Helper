package com.example.mystore.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mystore.model.Product
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
    onClick: (product: Product) -> Unit = {},
    onLongClick: () -> Unit = {},
    onDoubleClick: () -> Unit = {},
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreen.route,
        modifier = modifier,
    ) {
        composable(route = HomeScreen.route) {
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
        composable(route = RegisterProductScreen.route) {
            RegisterScreen()
        }
        composable(route = ConsolidatedPositionScreen.route) {
            ConsolidatedPositionScreen(
                consolidatedPosViewModel = consolidatedPosViewModel,
                shouldItemBeVisible = shouldItemBeVisible,
                onClick = { },
                onLongClick = { },
            )
        }
        composable(route = RegisterTransactionScreen.route) {
            RegisterTransactionScreen()
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
            saveState = true
        }

        launchSingleTop = true
        restoreState = true
    }
