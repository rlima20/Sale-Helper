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
import com.example.mystore.R
import com.example.mystore.commons.AppApplication
import com.example.mystore.commons.viewmodel.CommonViewModel
import com.example.mystore.features.homescreen.viewmodel.HomeViewModel
import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registerproduct.viewmodel.RegisterProductViewModel
import com.example.mystore.features.registertransaction.viewmodel.RegisterTransactionViewModel
import com.example.mystore.navigateSingleTopTo
import com.example.mystore.transformStringToInterfaceObject
import com.example.mystore.ui.components.commons.TotalComponent
import com.example.mystore.ui.components.main.BottomBarComponent
import com.example.mystore.ui.components.main.BottomBarComponentProps
import com.example.mystore.ui.components.main.TopBarComponent
import com.example.mystore.ui.components.main.TopBarComponentProps
import com.example.mystore.ui.navigation.ConsolidatedPositionScreen
import com.example.mystore.ui.navigation.HomeScreen
import com.example.mystore.ui.navigation.MyStoreDestinationInterface
import com.example.mystore.ui.navigation.MyStoreNavHost
import com.example.mystore.ui.navigation.MyStoreNavHostProps
import com.example.mystore.ui.navigation.RegisterProductScreen
import com.example.mystore.ui.navigation.RegisterTransactionScreen
import com.example.mystore.ui.theme.MyStoreTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val application = AppApplication.instance
    private val commonViewModel: CommonViewModel by viewModel()
    private val homeViewModel: HomeViewModel by viewModel()
    private val registerTransactionViewModel: RegisterTransactionViewModel by viewModel()
    private val registerProductViewModel: RegisterProductViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyStoreApp(
                application,
                commonViewModel,
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
    commonViewModel: CommonViewModel,
    homeViewModel: HomeViewModel,
    registerTransactionViewModel: RegisterTransactionViewModel,
    registerProductViewModel: RegisterProductViewModel,
) {
    MyStoreTheme {
        val navController = rememberNavController()
        val screenTitle by commonViewModel.commonViewState.screenTitle.collectAsState()
        val isMenuExpanded by commonViewModel.commonViewState.isMenuExpanded.collectAsState()
        val textFieldSize by commonViewModel.commonViewState.textFieldSize.collectAsState()
        val shouldItemBeVisible by commonViewModel.commonViewState.shouldItemBeVisible.collectAsState()
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
                    topBarComponentProps = TopBarComponentProps(
                        screenTitle = screenTitle,
                        isIconLined = shouldItemBeVisible,
                        isMenuExpanded = isMenuExpanded,
                        dropdownMenuWidth = textFieldSize,
                        isIconVisible = shouldDisplayIcon,
                        onIconVisibilityClicked = { commonViewModel.setValueVisibility(!shouldItemBeVisible) },
                        onMenuIconClicked = { commonViewModel.expandMenu(!isMenuExpanded) },
                        onDismissRequest = { commonViewModel.expandMenu(false) },
                        onDropDownMenuItemClicked = { screen ->
                            currentScreen = screen.transformStringToInterfaceObject(application)
                            commonViewModel.setScreenTitle(screen)
                            commonViewModel.expandMenu(false)
                            navController.navigateSingleTopTo(currentScreen.route)
                        },
                        onChangeDropdownMenuWidth = { size -> commonViewModel.setTextFieldSize(size) },
                    ),
                )
            },
            content = { content ->
                MyStoreNavHost(
                    MyStoreNavHostProps(
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
                            commonViewModel.setScreenTitle(
                                application.getString(R.string.my_store_home),
                            )
                        },
                        onUpdateTopBarText = { commonViewModel.setScreenTitle(it) },
                    )
                )
            },
            bottomBar = {
                BottomBarComponent(
                    bottomBarComponentProps = BottomBarComponentProps(
                        expandedBottomBar = expandedBottomBar,
                        onPositionConsolidateIconClicked = {
                            commonViewModel.setScreenTitle(
                                application.getString(
                                    R.string
                                        .my_store_consolidated_position,
                                ),
                            )
                            navController.navigateSingleTopTo(ConsolidatedPositionScreen.route)
                        },
                        onRegisterProductIconClicked = {
                            commonViewModel.setScreenTitle(
                                application.getString(R.string.my_store_register_product),
                            )
                            isEditMode = false
                            product = Product()
                            navController.navigateSingleTopTo(RegisterProductScreen.route)
                        },
                        onRegisterTransactionIconClicked = {
                            commonViewModel.setScreenTitle(
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
                            commonViewModel.setScreenTitle(application.getString(R.string.my_store_home))
                            navController.navigateSingleTopTo(HomeScreen.route)
                        },
                    )
                )
            },
        )
    }
}
