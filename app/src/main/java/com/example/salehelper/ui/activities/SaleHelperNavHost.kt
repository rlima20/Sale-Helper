package com.example.salehelper.ui.activities

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.salehelper.ui.components.HomeScreen
import com.example.salehelper.ui.components.RegisterScreen
import com.example.salehelper.ui.components.RegisterTransactionScreen
import com.example.salehelper.ui.components.TransactionScreen

@Composable
fun RallyNavHost(
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
        composable(route = RegisterScreen.route) {
            RegisterScreen()
        }
        composable(route = TransactionScreen.route) {
            TransactionScreen()
        }
        composable(route = RegisterTransactionScreen.route) {
            RegisterTransactionScreen()
        }
    }
}
