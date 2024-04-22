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
import androidx.navigation.compose.rememberNavController
import com.example.mystore.AppApplication
import com.example.mystore.R
import com.example.mystore.model.Product
import com.example.mystore.navigateSingleTopTo
import com.example.mystore.ui.components.commons.BottomBarComponent
import com.example.mystore.ui.components.commons.TopBarComponent
import com.example.mystore.ui.components.commons.TotalComponent
import com.example.mystore.ui.navigation.ConsolidatedPositionScreen
import com.example.mystore.ui.navigation.HomeScreen
import com.example.mystore.ui.navigation.MyStoreDestinationInterface
import com.example.mystore.ui.navigation.MyStoreNavHost
import com.example.mystore.ui.navigation.RegisterProductScreen
import com.example.mystore.ui.navigation.RegisterTransactionScreen
import com.example.mystore.ui.theme.MyStoreTheme
import com.example.mystore.viewmodel.global.GlobalViewModel
import com.example.mystore.viewmodel.screen.HomeViewModel
import com.example.mystore.viewmodel.screen.RegisterProductViewModel
import com.example.mystore.viewmodel.screen.RegisterTransactionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val application = AppApplication.instance
    private val viewModel: GlobalViewModel by viewModel()
    private val homeViewModel: HomeViewModel by viewModel()
    private val registerTransactionViewModel: RegisterTransactionViewModel by viewModel()
    private val registerProductViewModel: RegisterProductViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyStoreApp(
                application,
                viewModel,
                homeViewModel,
                registerTransactionViewModel,
                registerProductViewModel,
            )
        }
    }
}

@Composable
fun MyStoreApp(
    application: AppApplication,
    myStoreViewModel: GlobalViewModel,
    homeViewModel: HomeViewModel,
    registerTransactionViewModel: RegisterTransactionViewModel,
    registerProductViewModel: RegisterProductViewModel,
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
        var isEditMode: Boolean by remember { mutableStateOf(false) }
        var product: Product by remember { mutableStateOf(Product()) }
        var shouldDisplayIcon: Boolean by remember { mutableStateOf(true) }

        Scaffold(
            topBar = {
                TopBarComponent(
                    screenTitle = screenTitle,
                    shouldItemBeVisible = shouldItemBeVisible,
                    isMenuExpanded = isMenuExpanded,
                    textFieldSize = textFieldSize,
                    shouldDisplayIcon = shouldDisplayIcon,
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

            content = { content ->
                MyStoreNavHost(
                    navController = navController,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.color_50))
                        .padding(content),
                    homeViewModel = homeViewModel,
                    registerTransactionViewModel = registerTransactionViewModel,
                    registerProductViewModel = registerProductViewModel,
                    shouldItemBeVisible = shouldItemBeVisible,
                    isEditMode = isEditMode,
                    product = product,
                    onExpandBottomBar = { shouldExpandBottomBar ->
                        expandedBottomBar = shouldExpandBottomBar
                    },
                    onShowBottomBarExpanded = { sales, purchases ->
                        totalAmountOfSales = sales
                        totalAmountOfPurchases = purchases
                    },
                    onProductClick = { navController.navigateSingleTopTo(RegisterProductScreen.route) },
                    onProductDoubleClick = {},
                    onEditMode = { first, second ->
                        isEditMode = first
                        product = second
                    },
                    onShouldDisplayIcon = { shouldDisplay ->
                        shouldDisplayIcon = shouldDisplay
                    },
                    onNavigateToHome = {
                        navController.navigateSingleTopTo(HomeScreen.route)
                        myStoreViewModel.setScreenTitle(
                            application.getString(R.string.my_store_home),
                        )
                    },
                    onUpdateTopBarText = { myStoreViewModel.setScreenTitle(it) },
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
                        isEditMode = false
                        product = Product()
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
                    onHomeIconClicked = {
                        myStoreViewModel.setScreenTitle(application.getString(R.string.my_store_home))
                        navController.navigateSingleTopTo(HomeScreen.route)
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
