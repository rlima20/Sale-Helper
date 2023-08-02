package com.example.salehelper.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home

object HomeScreen : SaleHelperDestinationInterface {
    override val icon = Icons.Filled.Home
    override val route = "home_screen"
}

object RegisterProductScreen : SaleHelperDestinationInterface {
    override val icon = Icons.Filled.Add
    override val route = "register_product_screen"
}

// posição consolidada em inglês para facilitar a busca

object ConsolidatedPositionScreen : SaleHelperDestinationInterface {
    override val icon = Icons.Filled.Create
    override val route = "consolidated_position_screen"
}

object RegisterTransactionScreen : SaleHelperDestinationInterface {
    override val icon = Icons.Filled.Add
    override val route = "register_transaction_screen"
}
