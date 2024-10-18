package com.example.mystore.features.homescreen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mystore.R
import com.example.mystore.enums.Screens
import com.example.mystore.enums.Section
import com.example.mystore.enums.Type
import com.example.mystore.features.homescreen.model.Resume
import com.example.mystore.features.homescreen.viewmodel.HomeViewModel
import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registertransaction.model.Transaction
import com.example.mystore.toStringResource
import com.example.mystore.ui.components.commons.AlertDialogComponent
import com.example.mystore.ui.components.commons.DividerComponent
import com.example.mystore.ui.components.commons.RowComponent
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.components.commons.SectionEmptyStateInfo
import com.example.mystore.ui.components.commons.SectionInfo
import com.example.mystore.ui.components.commons.ShowAlertDialogComponent
import com.example.mystore.ui.components.commons.TextCurrencyComponent
import com.example.mystore.ui.components.commons.ToastComponent
import com.example.mystore.ui.components.commons.ValidateSection
import com.example.mystore.ui.components.commons.validateSection

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    shouldItemBeVisible: Boolean,
    onProductClick: (product: Product) -> Unit = {},
    onProductDoubleClick: () -> Unit = {},
    onEmptyStateImageClicked: (route: String, screen: String) -> Unit = { _: String, _: String -> },
    onEditMode: (Boolean, Product) -> Unit = { _, _ -> },
) {
    with(homeViewModel) {
        getResume()
        getAllProducts()
        getListOfSales()
        getListOfPurchases()
        getShowAlertDialogHomeScreen()

        val resume by homeViewState.resume.collectAsState()
        val listOfProducts by commonViewState.listOfProducts.collectAsState()
        val listOfTransactions by commonViewState.listOfTransactions.collectAsState()
        val showAlertDialogHomeScreen by homeViewState.showAlertDialogHomeScreen.collectAsState()
        val showAlertDialogHomeScreenProduct by homeViewState.showAlertDialogHomeScreenProduct.collectAsState()
        val showAlertDialogTransactionDetail by homeViewState.showAlertDialogTransactionDetail.collectAsState()
        val showToast by homeViewState.showToast.collectAsState()
        val transaction by homeViewState.transaction.collectAsState()
        val product by homeViewState.product.collectAsState()

        val transactionToastMessage =
            stringResource(R.string.my_store_successfull_transaction_removed)
        val productToastMessage = stringResource(R.string.my_store_successfull_product_removed)

        if (showToast.second) {
            ToastComponent(showToast.first)
        }

        Column(
            modifier = Modifier
                .padding(bottom = 12.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            // AlertDialog with transaction details
            if (showAlertDialogTransactionDetail) {
                AlertDialogComponent(
                    size = Size(
                        width = LocalConfiguration.current.screenWidthDp.dp.value * 1f,
                        height = LocalConfiguration.current.screenHeightDp.dp.value * 0.58f,
                    ),
                    color = colorResource(id = R.color.color_transaparent),
                    content = {
                        TransactionDetailsComponent(
                            transaction = transaction,
                            shouldItemBeVisible = shouldItemBeVisible,
                            onCloseAlertDialogTransactionDetail = {
                                setShowAlertDialogTransactionDetail(false)
                            },
                        )
                    },
                    onDismissRequest = { setShowAlertDialogTransactionDetail(false) },
                )
            }

            // AlertDialog Transaction delete confirmation
            ShowAlertDialogComponent(
                showAlert = showAlertDialogHomeScreen,
                title = stringResource(R.string.my_store_registry_removal),
                alertDialogMessage = stringResource(R.string.my_store_removal_confirmation),
                onDismissRequest = { setShowAlertDialogHomeScreen(false) },
                onDismissButtonClicked = { setShowAlertDialogHomeScreen(false) },
                onConfirmButtonClicked = {
                    setShowToastState(transactionToastMessage, true)
                    deleteTransaction(transaction)
                    setShowAlertDialogHomeScreen(false)
                    getListOfTransactions()
                },
            )

            // AlertDialog Product delete confirmation
            ShowAlertDialogComponent(
                showAlert = showAlertDialogHomeScreenProduct,
                title = stringResource(R.string.my_store_registry_removal),
                alertDialogMessage = stringResource(R.string.my_store_removal_product_confirmation),
                onDismissRequest = { setShowAlertDialogHomeScreenProduct(false) },
                onDismissButtonClicked = { setShowAlertDialogHomeScreenProduct(false) },
                onConfirmButtonClicked = {
                    setShowToastState(productToastMessage, true)
                    deleteProduct(product)
                    setShowAlertDialogHomeScreenProduct(false)
                    getAllProducts()
                },
            )

            // Total geral Section
            ValidateSection(
                sectionInfo = SectionInfo(
                    section = {
                        ScreenSectionComponent(
                            title = stringResource(id = R.string.my_store_overall_total),
                            body = {
                                resume?.let {
                                    HomeBody(
                                        it,
                                        shouldItemBeVisible,
                                    )
                                }
                            },
                        )
                    },
                ),
                sectionEmptyStateInfo = SectionEmptyStateInfo(
                    data = if (resume == null) emptyList() else listOf(resume),
                    emptySectionTitle = stringResource(R.string.my_store_no_transactions_done),
                    emptySectionPainter = painterResource(id = R.drawable.my_store_plus_icon),
                    onEmptyStateImageClicked = {
                        onEmptyStateImageClicked(
                            validateSection(
                                Section.RESUME,
                            ),
                            R.string.my_store_register_transaction.toStringResource()
                        )
                    },
                ),
            )

            // Transactions Section
            ValidateSection(
                screen = Screens.HOME,
                sectionInfo = SectionInfo(
                    sectionName = Section.TRANSACTIONS,
                    section = {
                        ScreenSectionComponent(
                            title = stringResource(id = R.string.my_store_transactions),
                            body = {
                                HomeTransactions(
                                    listOfTransactions = listOfTransactions,
                                    shouldItemBeVisible = shouldItemBeVisible,
                                    onClick = {
                                        setShowAlertDialogTransactionDetail(true)
                                        setTransaction(it)
                                    },
                                    onLongClick = {
                                        setTransaction(it)
                                        setShowAlertDialogHomeScreen(true)
                                    },
                                )
                            },
                        )
                    },
                ),
                sectionEmptyStateInfo = SectionEmptyStateInfo(
                    data = if (listOfTransactions.isEmpty()) listOf() else listOfTransactions,
                    emptySectionTitle = stringResource(R.string.my_store_no_transactions_done),
                    emptySectionPainter = painterResource(id = R.drawable.my_store_plus_icon),
                    onEmptyStateImageClicked = {
                        onEmptyStateImageClicked(
                            validateSection(
                                Section.TRANSACTIONS,
                            ),
                            R.string.my_store_register_transaction.toStringResource()
                        )
                    },
                ),
            )

            // Products Section
            ValidateSection(
                screen = Screens.HOME,
                sectionInfo = SectionInfo(
                    sectionName = Section.PRODUCTS,
                    section =
                    {
                        ScreenSectionComponent(
                            title = stringResource(id = R.string.my_store_products),
                            body = {
                                ProductCarouselComponent(
                                    listOfProducts = listOfProducts,
                                    shouldItemBeVisible = shouldItemBeVisible,
                                    onImageRequestState = { state ->
                                        setImageRequestState(state)
                                    },
                                    onProductClick = { product ->
                                        onEditMode(true, product)
                                        onProductClick(product)
                                    },
                                    onProductLongClick = { product ->
                                        setShowAlertDialogHomeScreenProduct(true)
                                        setProduct(product)
                                    },
                                    onProductDoubleClick = { onProductDoubleClick() },
                                )
                            },
                        )
                    },
                ),
                sectionEmptyStateInfo =
                SectionEmptyStateInfo(
                    data = listOfProducts,
                    emptySectionTitle = stringResource(R.string.my_store_no_products),
                    emptySectionPainter = painterResource(id = R.drawable.plus_circled_icon),
                    onEmptyStateImageClicked = {
                        onEmptyStateImageClicked(
                            validateSection(
                                Section.PRODUCTS,
                            ),
                            R.string.my_store_register_product.toStringResource()
                        )
                    },
                ),
            )
            setShowToastState(productToastMessage, false)
        }
    }
}

@Composable
private fun HomeTransactions(
    listOfTransactions: List<Transaction> = listOf(),
    shouldItemBeVisible: Boolean,
    onClick: (Transaction) -> Unit = {},
    onLongClick: (Transaction) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
            .height(160.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        listOfTransactions.forEach { transaction ->
            TransactionComponent(
                transaction = transaction,
                shouldItemBeVisible = shouldItemBeVisible,
                onClick = { onClick(it) },
                onLongClick = { onLongClick(it) },
            )
        }
    }
}

@Composable
private fun HomeBody(
    resume: Resume,
    shouldItemBeVisible: Boolean,
) {
    RowComponent(
        leftSideText = stringResource(id = R.string.my_store_debits),
        rightSide = {
            TextCurrencyComponent(
                value = resume.debits.toString(),
                shouldItemBeVisible = shouldItemBeVisible,
                type = Type.CURRENCY_DEBIT_ONLY,
            )
        },
    )

    RowComponent(
        leftSideText = stringResource(id = R.string.my_store_stock_value),
        rightSide = {
            TextCurrencyComponent(
                value = resume.stockValue.toString(),
                shouldItemBeVisible = shouldItemBeVisible,
                type = Type.CURRENCY_ONLY,
            )
        },
    )

    RowComponent(
        leftSideText = stringResource(id = R.string.my_store_gross_revenue),
        rightSide = {
            TextCurrencyComponent(
                value = resume.grossRevenue.toString(),
                shouldItemBeVisible = shouldItemBeVisible,
                type = Type.CURRENCY_ONLY,
            )
        },
    )

    DividerComponent()

    RowComponent(
        leftSideText = stringResource(id = R.string.my_store_net_revenue),
        fontSize = 20.sp,
        rightSide = {
            TextCurrencyComponent(
                value = resume.netRevenue.toString(),
                shouldItemBeVisible = shouldItemBeVisible,
                type = Type.CURRENCY_ONLY,
                fontSize = 20.sp,
            )
        },
    )
}

@Preview
@Composable
fun ScreenPreview() {
    ScreenSectionComponent(
        body = { Text(text = "Posição consolidada") },
        title = "Posição consolidada",
    )
}
