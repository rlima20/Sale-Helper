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
import com.example.mystore.AppApplication
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
import com.example.mystore.viewmodel.global.MyStoreViewModel
import com.example.mystore.viewmodel.screen.HomeViewModel
import com.example.mystore.viewmodel.screen.RegisterTransactionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val application = AppApplication.instance
    private val viewModel: MyStoreViewModel by viewModel()
    private val homeViewModel: HomeViewModel by viewModel()
    private val registerTransactionViewModel: RegisterTransactionViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyStoreApp(
                application,
                viewModel,
                homeViewModel,
                registerTransactionViewModel,
            )
        }
    }
}

@Composable
fun MyStoreApp(
    application: AppApplication,
    myStoreViewModel: MyStoreViewModel,
    homeViewModel: HomeViewModel,
    registerTransactionViewModel: RegisterTransactionViewModel,
) {
    MyStoreTheme {
        val navController = rememberNavController()
        val screenTitle by myStoreViewModel.screenTitle.collectAsState()
        val isMenuExpanded by myStoreViewModel.isMenuExpanded.collectAsState()
        val textFieldSize by myStoreViewModel.textFieldSize.collectAsState()
        val shouldItemBeVisible by myStoreViewModel.shouldItemBeVisible.collectAsState()
        var currentScreen: MyStoreDestinationInterface by remember { mutableStateOf(HomeScreen) }
        var expandedBottomBar: Boolean by remember { mutableStateOf(false) }
        var totalAmountOfSales: Double by remember { mutableStateOf(0.0) }
        var totalAmountOfPurchases: Double by remember { mutableStateOf(0.0) }

        Scaffold(
            topBar = {
                TopBarComponent(
                    screenTitle = screenTitle,
                    shouldItemBeVisible = shouldItemBeVisible,
                    isMenuExpanded = isMenuExpanded,
                    textFieldSize = textFieldSize,
                    onHomeIconClicked = {
                        myStoreViewModel.setScreenTitle(application.getString(R.string.my_store_home))
                        navController.navigateSingleTopTo(HomeScreen.route)
                    },
                    onIconVisibilityClicked = {
                        myStoreViewModel.setValueVisibility(!shouldItemBeVisible)
                    },
                    onMenuIconClicked = {
                        myStoreViewModel.expandMenu(!isMenuExpanded)
                    },
                    onDismissRequest = {
                        myStoreViewModel.expandMenu(false)
                    },
                    onDropDownMenuItemClicked = { screen ->
                        currentScreen = transformStringToInterfaceObject(application, screen)
                        myStoreViewModel.setScreenTitle(screen)
                        myStoreViewModel.expandMenu(false)
                        navController.navigateSingleTopTo(currentScreen.route)
                    },
                    onChangeTextFieldSize = { size -> myStoreViewModel.setTextFieldSize(size) },
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
                    registerTransactionViewModel = registerTransactionViewModel,
                    shouldItemBeVisible = shouldItemBeVisible,
                    onExpandBottomBar = { shouldExpandBottomBar ->
                        expandedBottomBar = shouldExpandBottomBar
                    },
                    onShowBottomBarExpanded = { sales, purchases ->
                        totalAmountOfSales = sales
                        totalAmountOfPurchases = purchases
                    },
                    onProductClick = { navController.navigateSingleTopTo(RegisterProductScreen.route) },
                    onProductLongClick = {},
                    onProductDoubleClick = {},
                )
            },
            bottomBar = {
                BottomBarComponent(
                    expandedBottomBar = expandedBottomBar,
                    onPositionConsolidateIconClicked = {
                        myStoreViewModel.setScreenTitle(
                            application.getString(
                                R.string
                                    .my_store_consolidated_position,
                            ),
                        )
                        navController.navigateSingleTopTo(ConsolidatedPositionScreen.route)
                    },
                    onRegisterProductIconClicked = {
                        myStoreViewModel.setScreenTitle(
                            application.getString(R.string.my_store_register_product),
                        )
                        navController.navigateSingleTopTo(RegisterProductScreen.route)
                    },
                    onRegisterTransactionIconClicked = {
                        myStoreViewModel.setScreenTitle(
                            application.getString(
                                R.string
                                    .my_store_register_transaction,
                            ),
                        )
                        navController.navigateSingleTopTo(RegisterTransactionScreen.route)
                    },
                    expandedBottomBarContent = {
                        TotalComponent(
                            salesValue = totalAmountOfSales,
                            purchasesValue = totalAmountOfPurchases,
                            shouldItemBeVisible = shouldItemBeVisible,
                        )
                    },
                )
            },
        )
    }
}

private fun transformStringToInterfaceObject(application: AppApplication, screen: String):
    MyStoreDestinationInterface {
    return when (screen) {
        application.getString(R.string.my_store_home) -> HomeScreen
        application.getString(R.string.my_store_register_product) -> RegisterProductScreen
        application.getString(R.string.my_store_register_transaction) -> RegisterTransactionScreen
        application.getString(R.string.my_store_consolidated_position) -> ConsolidatedPositionScreen
        else -> HomeScreen
    }
}

@Preview
@Composable
fun MyStoreAppPreview() {
    MyStoreApp(
        application = AppApplication.instance,
        myStoreViewModel = MyStoreViewModel(),
        homeViewModel = HomeViewModel(),
        registerTransactionViewModel = RegisterTransactionViewModel(),
    )
}
