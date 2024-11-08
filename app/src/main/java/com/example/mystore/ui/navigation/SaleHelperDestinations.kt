package com.example.mystore.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home

object HomeScreen : MyStoreDestinationInterface {
    override val icon = Icons.Filled.Home
    override val route = "home_screen"
}

object RegisterProductScreen : MyStoreDestinationInterface {
    override val icon = Icons.Filled.Add
    override val route = "register_product_screen"
}

object ConsolidatedPositionScreen : MyStoreDestinationInterface {
    override val icon = Icons.Filled.Create
    override val route = "consolidated_position_screen"
}

object RegisterTransactionScreen : MyStoreDestinationInterface {
    override val icon = Icons.Filled.Add
    override val route = "register_transaction_screen"
}

object EditTransactionScreen : MyStoreDestinationInterface {
    override val icon = Icons.Filled.Edit
    override val route = "edit_transaction_screen"
}

