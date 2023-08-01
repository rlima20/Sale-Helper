package com.example.salehelper.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.salehelper.ui.components.DropDownComponent
import com.example.salehelper.ui.theme.Purple700
import com.example.salehelper.ui.theme.SaleAdvisorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SaleHelperApp()
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SaleHelperApp() {
    SaleAdvisorTheme {
        // Navigation variables
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        var currentScreen = screens.find { it.route == currentDestination?.route } ?: HomeScreen

        // DropdownMenu variables
        var isMenuExpanded by remember { mutableStateOf(false) }
        var selectedScreen by remember { mutableStateOf("") }
        var textFieldSize by remember { mutableStateOf(Size.Zero) }

        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier
                        .background(Purple700)
                        .padding(12.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Icon(
                        Icons.Rounded.Home,
                        contentDescription = null,
                        tint = Color.White,
                    )

                    Text(
                        text = "Alura",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 22.sp,
                    )

                    DropDownComponent(
                        screens = com.example.salehelper.screens,
                        isMenuExpanded = isMenuExpanded,
                        textFieldSize = textFieldSize,
                        onMenuIconClicked = {
                            isMenuExpanded = !isMenuExpanded
                        },
                        onDismissRequest = {
                            isMenuExpanded = false
                        },
                        onDropDownMenuItemClicked = { screen ->
                            selectedScreen = screen
                            isMenuExpanded = false
                            currentScreen = setCurrentScreen(screen)
                            navController.navigate(currentScreen.route)
                        },
                        onChangeTextFieldSize = { size ->
                            textFieldSize = size
                        },
                    )
                }
            },
        ) { innerPadding ->
            SaleHelperNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}

private fun setCurrentScreen(screen: String): SaleHelperDestination {
    return when (screen) {
        "Home" -> HomeScreen
        "Cadastro de produtos" -> RegisterProductScreen
        "Registro de transação" -> RegisterTransactionScreen
        "Posição consolidada" -> ConsolidatedPositionScreen
        else -> HomeScreen
    }
}
