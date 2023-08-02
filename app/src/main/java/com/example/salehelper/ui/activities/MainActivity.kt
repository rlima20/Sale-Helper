package com.example.salehelper.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.salehelper.R
import com.example.salehelper.screenList
import com.example.salehelper.ui.components.MenuComponent
import com.example.salehelper.ui.navigation.ConsolidatedPositionScreen
import com.example.salehelper.ui.navigation.HomeScreen
import com.example.salehelper.ui.navigation.RegisterProductScreen
import com.example.salehelper.ui.navigation.RegisterTransactionScreen
import com.example.salehelper.ui.navigation.SaleHelperDestinationInterface
import com.example.salehelper.ui.navigation.SaleHelperNavHost
import com.example.salehelper.ui.navigation.navigateSingleTopTo
import com.example.salehelper.ui.theme.SaleAdvisorTheme
import com.example.salehelper.ui.theme.secondary

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
        var currentScreen: SaleHelperDestinationInterface by remember { mutableStateOf(HomeScreen) }
        var screenTitle: String by remember { mutableStateOf("Home") }

        // DropdownMenu variables
        var isMenuExpanded by remember { mutableStateOf(false) }
        var selectedScreen by remember { mutableStateOf("") }
        var textFieldSize by remember { mutableStateOf(Size.Zero) }

        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier
                        .background(secondary)
                        .padding(12.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable(
                                onClick = {
                                    navController.navigateSingleTopTo(HomeScreen.route)
                                },
                            ),
                        imageVector = Icons.Rounded.Home,
                        contentDescription = null,
                        tint = Color.White,
                    )

                    Text(
                        text = screenTitle,
                        color = Color.White,
                        fontSize = 18.sp,
                    )

                    MenuComponent(
                        screens = screenList,
                        isMenuExpanded = isMenuExpanded,
                        textFieldSize = textFieldSize,
                        onMenuIconClicked = {
                            isMenuExpanded = !isMenuExpanded
                        },
                        onDismissRequest = {
                            isMenuExpanded = false
                        },
                        onDropDownMenuItemClicked = { screen ->
                            screenTitle = screen
                            selectedScreen = screen
                            isMenuExpanded = false
                            currentScreen = setCurrentScreen(screen)
                            navController.navigateSingleTopTo(currentScreen.route)
                        },
                        onChangeTextFieldSize = { size ->
                            textFieldSize = size
                        },
                    )
                }
            },
            content = {
                SaleHelperNavHost(
                    navController = navController,
                    modifier = Modifier.padding(it),
                )
            },
            bottomBar = {
                Row(
                    modifier = Modifier
                        .background(secondary)
                        .padding(12.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(20.dp)
                            .clickable(
                                onClick = {
                                    navController.navigateSingleTopTo(HomeScreen.route)
                                },
                            ),
                        painter = painterResource(id = R.drawable.position),
                        contentDescription = null,
                        tint = Color.White,
                    )
                    Icon(
                        modifier = Modifier
                            .clickable(
                                onClick = {
                                    navController.navigateSingleTopTo(HomeScreen.route)
                                },
                            ),
                        imageVector = Icons.Rounded.Add,
                        contentDescription = null,
                        tint = Color.White,
                    )
                    Icon(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(20.dp)
                            .clickable(
                                onClick = {
                                    navController.navigateSingleTopTo(HomeScreen.route)
                                },
                            ),
                        painter = painterResource(id = R.drawable.transaction),
                        contentDescription = null,
                        tint = Color.White,
                    )
                }
            },
        )
    }
}

private fun setCurrentScreen(screen: String): SaleHelperDestinationInterface {
    return when (screen) {
        "Home" -> HomeScreen
        "Cadastro de produtos" -> RegisterProductScreen
        "Registro de transação" -> RegisterTransactionScreen
        "Posição consolidada" -> ConsolidatedPositionScreen
        else -> HomeScreen
    }
}

@Preview
@Composable
fun SaleHelperAppPreview() {
    SaleHelperApp()
}
