package com.example.mystore.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mystore.features.consolidatedposition.ui.ConsolidatedPositionScreen
import com.example.mystore.features.homescreen.model.HomeScreenProps
import com.example.mystore.features.homescreen.ui.HomeScreen
import com.example.mystore.features.registerproduct.ui.RegisterProductScreen
import com.example.mystore.features.registertransaction.model.RegisterTransactionScreenProps
import com.example.mystore.features.registertransaction.model.Transaction
import com.example.mystore.features.registertransaction.ui.RegisterTransactionScreen
import com.example.mystore.features.updatetransaction.ui.UpdateTransactionScreen
import com.example.mystore.navigateSingleTopTo
import com.example.mystore.ui.model.MyStoreNavHostProps

@Composable
fun MyStoreNavHost(
    myStoreNavHostProps: MyStoreNavHostProps
) {
    var transactionClearStates by remember { mutableStateOf(false) }
    var productClearStates by remember { mutableStateOf(false) }
    var transactionState by myStoreNavHostProps.viewModelProps.consolidatedPositionScreenViewModel.viewState.transaction

    with(myStoreNavHostProps) {
        NavHost(
            navController = navController,
            startDestination = HomeScreen.route,
            modifier = modifier,
        ) {
            // Navigates to Home Screen
            composable(route = HomeScreen.route) {
                NavigateToHomeScreen(
                    onTransactionClearAllState = { transactionClearStates = it },
                    onProductClearAllState = { productClearStates = it },
                    onUpdateTransaction = { transactionState = it }
                )
            }

            // Navigates to ConsolidatedPositionScreen
            composable(route = ConsolidatedPositionScreen.route) {
                NavigateToConsolidatedPositionScreen(
                    onTransactionClearAllState = { transactionClearStates = it },
                    onProductClearAllState = { productClearStates = it },
                    onUpdateTransaction = { transactionState = it }
                )
            }

            // Navigates to RegisterTransactionScreen
            composable(route = RegisterTransactionScreen.route) {
                NavigateToRegisterTransactionScreen(
                    productClearStates,
                    onClearAllStates = { transactionClearStates = it }
                )
            }

            // Navigates to RegisterProductScreen
            composable(route = RegisterProductScreen.route) {
                NavigateToRegisterProductScreen(
                    onClearStates = {
                        productClearStates = it
                        if (productClearStates) viewModelProps.registerProductViewModel.clearAllStates()
                    }
                )
            }

            // Navigates to EditTransactionScreen
            composable(route = EditTransactionScreen.route) {
                UpdateTransactionScreen(
                    transaction = transactionState,
                    updateTransactionViewModel = viewModelProps.updateTransactionViewModel,
                    onUpdateTransaction = {
                        viewModelProps.updateTransactionViewModel.updateTransaction(it)
                        viewModelProps.updateTransactionViewModel.getAllTransactions()
                        composable(route = HomeScreen.route) {
                            NavigateToHomeScreen()
                        }
                    },
                )
            }
        }
    }
}

@Composable
private fun MyStoreNavHostProps.NavigateToRegisterProductScreen(
    onClearStates: (onClearStates: Boolean) -> Unit
) {
    uiProps.callbackProps.onExpandBottomBar(false)
    uiProps.callbackProps.onShouldDisplayIcon(false)

    RegisterProductScreen(
        product = uiProps.stateProps.product,
        isEditMode = uiProps.stateProps.isEditMode,
        registerProductViewModel = viewModelProps.registerProductViewModel,
        onClearStates = { onClearStates(it) },
        onNavigateToHome = { uiProps.callbackProps.onNavigateToHome() },
    )
}

@Composable
private fun MyStoreNavHostProps.NavigateToRegisterTransactionScreen(
    transactionClearStates: Boolean,
    onClearAllStates: (onClearAllStates: Boolean) -> Unit
) {
    uiProps.callbackProps.onExpandBottomBar(false)
    uiProps.callbackProps.onShouldDisplayIcon(true)

    RegisterTransactionScreen(
        registerTransactionScreenProps = RegisterTransactionScreenProps(
            registerTransactionViewModel = viewModelProps.registerTransactionViewModel,
            shouldItemBeVisible = uiProps.stateProps.shouldItemBeVisible,
            clearAllStates = transactionClearStates,
            onClearAllStates = { onClearAllStates(it) },
        )
    )
}

@Composable
private fun MyStoreNavHostProps.NavigateToConsolidatedPositionScreen(
    onTransactionClearAllState: (onTransactionClearAll: Boolean) -> Unit,
    onProductClearAllState: (onTransactionClearAll: Boolean) -> Unit,
    onUpdateTransaction: (Transaction) -> Unit
) {
    viewModelProps.homeViewModel.getListOfSales()
    viewModelProps.homeViewModel.getListOfPurchases()
    uiProps.callbackProps.onShouldDisplayIcon(true)
    onTransactionClearAllState(true)
    onProductClearAllState(true)

    ConsolidatedPositionScreen(
        homeViewModel = viewModelProps.homeViewModel,
        shouldItemBeVisible = uiProps.stateProps.shouldItemBeVisible,
        onShowBottomBarExpanded = { ShowBottomBarExpanded() },
        onEmptyStateImageClicked = { route, screen ->
            navController.navigateSingleTopTo(route)
            uiProps.callbackProps.onUpdateTopBarText(screen)
        },
        onNavigateToEditTransactionScreen = { route, transaction ->
            onUpdateTransaction(transaction)
            navController.navigateSingleTopTo(route)
        }
    )
}

@Composable
private fun MyStoreNavHostProps.NavigateToHomeScreen(
    onTransactionClearAllState: (onTransactionClearAll: Boolean) -> Unit = {},
    onProductClearAllState: (onTransactionClearAll: Boolean) -> Unit = {},
    onUpdateTransaction: (Transaction) -> Unit = {}
) {
    onTransactionClearAllState(true)
    onProductClearAllState(true)
    uiProps.callbackProps.onExpandBottomBar(false)
    uiProps.callbackProps.onShouldDisplayIcon(true)

    HomeScreen(
        homeScreenProps = HomeScreenProps(
            homeViewModel = viewModelProps.homeViewModel,
            shouldItemBeVisible = uiProps.stateProps.shouldItemBeVisible,
            onProductClick = { uiProps.callbackProps.onProductClick(it) },
            onProductDoubleClick = { uiProps.callbackProps.onProductDoubleClick() },
            onEmptyStateImageClicked = { route, screen ->
                navController.navigateSingleTopTo(route)
                uiProps.callbackProps.onUpdateTopBarText(screen)
            },
            onEditMode = { isEditMode, product ->
                uiProps.callbackProps.onEditMode(
                    isEditMode,
                    product
                )
            },
            onNavigateToEditTransactionScreen = { route, transaction ->
                onUpdateTransaction(transaction)
                navController.navigateSingleTopTo(route)
            }
        ),
    )
}

@Composable
private fun MyStoreNavHostProps.ShowBottomBarExpanded() {
    with(viewModelProps.homeViewModel) {
        if (commonViewState.listOfSales.value.isNotEmpty() || commonViewState.listOfPurchases.value.isNotEmpty()) {
            uiProps.callbackProps.onExpandBottomBar(true)
            uiProps.callbackProps.onShowBottomBarExpanded(getSalesValue(), getPurchasesValue())
        }
    }
}