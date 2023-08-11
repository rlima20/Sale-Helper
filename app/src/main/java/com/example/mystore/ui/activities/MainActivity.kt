package com.example.mystore.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.mystore.R
import com.example.mystore.navigateSingleTopTo
import com.example.mystore.ui.components.commons.TotalComponent
import com.example.mystore.ui.components.topbar.BottomBarComponent
import com.example.mystore.ui.components.topbar.TopBarComponent
import com.example.mystore.ui.navigation.ConsolidatedPositionScreen
import com.example.mystore.ui.navigation.HomeScreen
import com.example.mystore.ui.navigation.MyStoreDestinationInterface
import com.example.mystore.ui.navigation.MyStoreNavHost
import com.example.mystore.ui.navigation.RegisterProductScreen
import com.example.mystore.ui.navigation.RegisterTransactionScreen
import com.example.mystore.ui.theme.MyStoreTheme
import com.example.mystore.viewmodel.ConsolidatedPosViewModel
import com.example.mystore.viewmodel.HomeViewModel
import com.example.mystore.viewmodel.MyStoreViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MyStoreViewModel by viewModel()
    private val homeViewModel: HomeViewModel by viewModel()
    private val consolidatedPosViewModel: ConsolidatedPosViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyStoreApp(
                viewModel,
                homeViewModel,
                consolidatedPosViewModel,
            )
        }
    }
}

@Composable
fun MyStoreApp(
    viewModel: MyStoreViewModel,
    homeViewModel: HomeViewModel,
    consolidatedPosViewModel: ConsolidatedPosViewModel,
) {
    MyStoreTheme {
        val navController = rememberNavController()
        val screenTitle by viewModel.screenTitle.collectAsState()
        val isMenuExpanded by viewModel.isMenuExpanded.collectAsState()
        val textFieldSize by viewModel.textFieldSize.collectAsState()
        val shouldItemBeVisible by viewModel.shouldItemBeVisible.collectAsState()
        var currentScreen: MyStoreDestinationInterface by remember { mutableStateOf(HomeScreen) }
        var expandedBottomBar: Boolean by remember { mutableStateOf(false) }
        var salesValue: Double by remember { mutableStateOf(0.0) }

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
                        viewModel.setScreenTitle(screen)
                        viewModel.setIsMenuExpanded(false)
                        navController.navigateSingleTopTo(currentScreen.route)
                    },
                    onChangeTextFieldSize = { size -> viewModel.setTextFieldSize(size) },
                )
            },
            content = {
                MyStoreNavHost(
                    navController = navController,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.color_400))
                        .padding(it),
                    homeViewModel = homeViewModel,
                    consolidatedPosViewModel = consolidatedPosViewModel,
                    shouldItemBeVisible = shouldItemBeVisible,
                    onExpandBottomBar = { shouldExpandBottomBar ->
                        expandedBottomBar = shouldExpandBottomBar
                    },
                    onComponent = { sales, _ ->
                        salesValue = sales
                    },
                    onClick = { navController.navigateSingleTopTo(RegisterProductScreen.route) },
                    onLongClick = {},
                    onDoubleClick = {},
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
                    expandedBottomBar = expandedBottomBar,
                    expandedBottomBarContent = {
                        TotalComponent(
                            salesValue = salesValue,
                            purchasesValue = 0.0,
                            shouldItemBeVisible = shouldItemBeVisible,
                        )
                    },
                )
            },
        )
    }
}

private fun transformStringToInterfaceObject(screen: String): MyStoreDestinationInterface {
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
fun MyStoreAppPreview() {
    MyStoreApp(
        viewModel = MyStoreViewModel(),
        homeViewModel = HomeViewModel(),
        consolidatedPosViewModel = ConsolidatedPosViewModel(),
    )
}
