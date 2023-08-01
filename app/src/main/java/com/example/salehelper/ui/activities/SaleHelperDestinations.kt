package com.example.salehelper.ui.activities

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

// todo - mover essa interface daqui
interface SaleHelperDestination {
    val icon: ImageVector
    val route: String
}

/**
 * Rally app navigation destinations
 */
object HomeScreen : SaleHelperDestination {
    override val icon = Icons.Filled.Home
    override val route = "home_screen"
}

object RegisterProductScreen : SaleHelperDestination {
    override val icon = Icons.Filled.Add
    override val route = "register_product_screen"
}

// posição consolidada em inglês para facilitar a busca

object ConsolidatedPositionScreen : SaleHelperDestination {
    override val icon = Icons.Filled.Create
    override val route = "consolidated_position_screen"
}

object RegisterTransactionScreen : SaleHelperDestination {
    override val icon = Icons.Filled.Add
    override val route = "register_transaction_screen"
}

val screens =
    listOf(
        HomeScreen,
        RegisterProductScreen,
        ConsolidatedPositionScreen,
        RegisterTransactionScreen,
    )
