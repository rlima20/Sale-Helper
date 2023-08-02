package com.example.salehelper.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.salehelper.ui.components.topbar.BottomBarComponent
import com.example.salehelper.ui.components.topbar.TopBarComponent
import com.example.salehelper.ui.navigation.ConsolidatedPositionScreen
import com.example.salehelper.ui.navigation.HomeScreen
import com.example.salehelper.ui.navigation.RegisterProductScreen
import com.example.salehelper.ui.navigation.RegisterTransactionScreen
import com.example.salehelper.ui.navigation.SaleHelperDestinationInterface
import com.example.salehelper.ui.navigation.SaleHelperNavHost
import com.example.salehelper.ui.navigation.navigateSingleTopTo
import com.example.salehelper.ui.theme.SaleHelperTheme
import com.example.salehelper.viewmodel.SaleHelperViewModel

class MainActivity : ComponentActivity() {
    private val viewModel = SaleHelperViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SaleHelperApp(viewModel)
        }
    }
}

@Composable
fun SaleHelperApp(viewModel: SaleHelperViewModel) {
    SaleHelperTheme {
        val navController = rememberNavController()
        val screenTitle by viewModel.screenTitle.collectAsState()
        val isMenuExpanded by viewModel.isMenuExpanded.collectAsState()
        val textFieldSize by viewModel.textFieldSize.collectAsState()
        val shouldItemBeVisible by viewModel.shouldItemBeVisible.collectAsState()
        var currentScreen: SaleHelperDestinationInterface by remember { mutableStateOf(HomeScreen) }

        Scaffold(
            topBar = {
                TopBarComponent(
                    screenTitle,
                    shouldItemBeVisible,
                    isMenuExpanded,
                    textFieldSize,
                    onHomeIconClicked = {
                        viewModel.setScreenTitle("Home")
                        navController.navigateSingleTopTo(HomeScreen.route)
                    },
                    onIconVisibilityClicked = {
                        viewModel.setShouldItemBeVisible(!shouldItemBeVisible)
                    },
                    onMenuIconClicked = {
                        viewModel.setIsMenuExpanded(!isMenuExpanded)
                    },
                    onDismissRequest = {
                        viewModel.setIsMenuExpanded(false)
                    },
                    onDropDownMenuItemClicked = { screen ->
                        currentScreen = transformStringToInterfaceObject(screen)
                        /*                        viewModel.setCurrentScreen(
                                                    screenInterfaceObject = transformStringToInterfaceObject(
                                                        screen = screen,
                                                    ),
                                                )*/
                        viewModel.setScreenTitle(screen)
                        viewModel.setIsMenuExpanded(false)
                        navController.navigateSingleTopTo(currentScreen.route)
                    },
                    onChangeTextFieldSize = { size -> viewModel.setTextFieldSize(size) },
                )
            },
            content = {
                SaleHelperNavHost(
                    navController = navController,
                    modifier = Modifier.padding(it),
                )
            },
            bottomBar = {
                BottomBarComponent(
                    onPositionConsolidateIconClicked = {
                        viewModel.setScreenTitle("Posição consilidada")
                        navController.navigateSingleTopTo(ConsolidatedPositionScreen.route)
                    },
                    onRegisterProductIconClicked = {
                        viewModel.setScreenTitle("Cadastro de produtos")
                        navController.navigateSingleTopTo(RegisterProductScreen.route)
                    },
                    onRegisterTransactionIconClicked = {
                        viewModel.setScreenTitle("Registro de transação")
                        navController.navigateSingleTopTo(RegisterTransactionScreen.route)
                    },
                )
            },
        )
    }
}

private fun transformStringToInterfaceObject(screen: String): SaleHelperDestinationInterface {
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
    SaleHelperApp(viewModel = SaleHelperViewModel())
}
