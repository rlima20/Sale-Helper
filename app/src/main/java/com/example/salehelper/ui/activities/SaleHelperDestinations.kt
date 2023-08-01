package com.example.salehelper.ui.activities

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

interface SaleHelperDestination {
    val icon: ImageVector
    val route: String
}

/**
 * Rally app navigation destinations
 */
object HomeScreen : SaleHelperDestination {
    override val icon = Icons.Filled.Home
    override val route = "homescreen"
}

object RegisterScreen : SaleHelperDestination {
    override val icon = Icons.Filled.Add
    override val route = "registerscreen"
}

object TransactionScreen : SaleHelperDestination {
    override val icon = Icons.Filled.Create
    override val route = "transactionscreen"
}

object RegisterTransactionScreen : SaleHelperDestination {
    override val icon = Icons.Filled.Add
    override val route = "registertransactionscreen"
}

val screens = listOf(HomeScreen, RegisterScreen, TransactionScreen, RegisterTransactionScreen)
