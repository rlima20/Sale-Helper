package com.example.mystore.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mystore.ui.components.screens.HomeScreen
import com.example.mystore.ui.components.screens.RegisterScreen
import com.example.mystore.ui.components.screens.RegisterTransactionScreen
import com.example.mystore.ui.components.screens.TransactionScreen
import com.example.mystore.viewmodel.HomeViewModel

@Composable
fun MyStoreNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    shouldItemBeVisible: Boolean,
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreen.route,
        modifier = modifier,
    ) {
        composable(route = HomeScreen.route) {
            HomeScreen(homeViewModel, shouldItemBeVisible)
        }
        composable(route = RegisterProductScreen.route) {
            RegisterScreen()
        }
        composable(route = ConsolidatedPositionScreen.route) {
            TransactionScreen()
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
