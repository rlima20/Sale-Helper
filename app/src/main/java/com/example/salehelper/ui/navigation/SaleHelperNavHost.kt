package com.example.salehelper.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.salehelper.ui.components.HomeScreen
import com.example.salehelper.ui.components.RegisterScreen
import com.example.salehelper.ui.components.RegisterTransactionScreen
import com.example.salehelper.ui.components.TransactionScreen

@Composable
fun SaleHelperNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreen.route,
        modifier = modifier,
    ) {
        composable(route = HomeScreen.route) {
            HomeScreen()
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
