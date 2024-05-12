package com.example.mystore.ui.components.screens

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
import com.example.mystore.Screens
import com.example.mystore.Section
import com.example.mystore.Type
import com.example.mystore.model.SectionEmptyStateInfo
import com.example.mystore.model.SectionInfo
import com.example.mystore.model.props.ColorProps
import com.example.mystore.model.props.ShowAlertDialogComponentCallbackProps
import com.example.mystore.model.screen.Product
import com.example.mystore.model.screen.Resume
import com.example.mystore.model.screen.Transaction
import com.example.mystore.toStringResource
import com.example.mystore.ui.components.TransactionDetailsComponent
import com.example.mystore.ui.components.commons.AlertDialogComponent
import com.example.mystore.ui.components.commons.DividerComponent
import com.example.mystore.ui.components.commons.ProductCarouselComponent
import com.example.mystore.ui.components.commons.RowComponent
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.components.commons.TextCurrencyComponent
import com.example.mystore.ui.components.commons.ToastComponent
import com.example.mystore.ui.components.commons.TransactionComponent
import com.example.mystore.ui.components.commons.UnifiedAlertDialog
import com.example.mystore.ui.components.commons.ValidateSection
import com.example.mystore.ui.components.commons.validateSection
import com.example.mystore.viewmodel.screen.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    currencyVisibility: Boolean,
    onProductClick: (product: Product) -> Unit = {},
    onProductDoubleClick: () -> Unit = {},
    onEmptyStateImageClicked: (route: String, screen: String) -> Unit = { _: String, _: String -> },
    onEditMode: (Boolean, Product) -> Unit = { _, _ -> },
) {
    with(homeViewModel) {

        getResume()
        getListOfProducts()
        getListOfSales()
        getListOfPurchases()
        getShowAlertDialogHomeScreen()

        val resume by resume.collectAsState()
        val listOfProducts by listOfProducts.collectAsState()
        val listOfTransactions by listOfTransactions.collectAsState()
        val transactionDeleteConfirmationDialogVisibility by transactionDeleteConfirmationDialogVisibility.collectAsState()
        val productDeleteConfirmationDialogVisibility by productDeleteConfirmationDialogVisibility.collectAsState()
        val transactionDetailsDialogVisibility by transactionDetailsDialogVisibility.collectAsState()
        val showToast by showToast.collectAsState()
        val transaction by transaction.collectAsState()
        val product by product.collectAsState()
        val transactionToastMessage =
            stringResource(R.string.my_store_successfull_transaction_removed)
        val productToastMessage = stringResource(R.string.my_store_successfull_product_removed)

        if (showToast.second) ToastComponent(showToast.first)

        Column(
            modifier = Modifier
                .padding(bottom = 12.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            // AlertDialog with transaction details
            UnifiedAlertDialog(
                alertDialogVisibility = transactionDetailsDialogVisibility,
                alertDialogSize = Size(
                    width = LocalConfiguration.current.screenWidthDp.dp.value * 1f,
                    height = LocalConfiguration.current.screenHeightDp.dp.value * 0.58f,
                ),
                content = {
                    TransactionDetailsComponent(
                        transaction = transaction,
                        currencyVisibility = currencyVisibility,
                        onCloseAlertDialogTransactionDetail = {
                            setTransactionDetailsDialogVisibility(false)
                        },
                    )
                },
                alertDialogColor = colorResource(id = R.color.color_transaparent),
                alertDialogCallback = ShowAlertDialogComponentCallbackProps(
                    onDismissRequest = { setTransactionDetailsDialogVisibility(false) },
                ),
            )


            if (transactionDetailsDialogVisibility) {
                AlertDialogComponent(
                    content = {
                        TransactionDetailsComponent(
                            transaction = transaction,
                            currencyVisibility = currencyVisibility,
                            onCloseAlertDialogTransactionDetail = {
                                setTransactionDetailsDialogVisibility(false)
                            },
                        )
                    },
                )
            }

            // AlertDialog Transaction delete confirmation
            if (transactionDeleteConfirmationDialogVisibility) {
                AlertDialogComponent(
                    title = stringResource(R.string.my_store_registry_removal),
                    content = {
                        Text(
                            text = stringResource(R.string.my_store_removal_confirmation),
                            color = colorResource(id = ColorProps().alertDialogTextColor),
                        )
                    },
                    onDismissRequest = { setShowAlertDialogHomeScreen(false) },
                    confirmButton = {
                        setShowToastState(transactionToastMessage, true)
                        deleteTransaction(transaction)
                        setShowAlertDialogHomeScreen(false)
                        getListOfTransactions()
                    },
                    dismissButton = { setShowAlertDialogHomeScreen(false) },
                    color = colorResource(id = R.color.white),
                )
            }

            // AlertDialog Product delete confirmation
            if (productDeleteConfirmationDialogVisibility) {
                AlertDialogComponent(

                )
            }
            AlertDialogComponent(
                alertDialogVisibility = productDeleteConfirmationDialogVisibility,
                alertDialogTitle = stringResource(R.string.my_store_registry_removal),
                alertDialogMessage = stringResource(R.string.my_store_removal_product_confirmation),
                callback = ShowAlertDialogComponentCallbackProps(
                    onDismissRequest = { setShowAlertDialogHomeScreenProduct(false) },
                    onDismissButtonClicked = { setShowAlertDialogHomeScreenProduct(false) },
                    onConfirmButtonClicked = {
                        setShowToastState(productToastMessage, true)
                        deleteProduct(product)
                        setShowAlertDialogHomeScreenProduct(false)
                        getListOfProducts()
                    },
                )
            )

            // Total geral Section
            ValidateSection(
                screen = Screens.HOME,
                sectionInfo = SectionInfo(
                    section = {
                        ScreenSectionComponent(
                            title = stringResource(id = R.string.my_store_overall_total),
                            body = {
                                resume?.let {
                                    HomeBody(
                                        it,
                                        currencyVisibility,
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
                                    isItemVisible = currencyVisibility,
                                    onClick = {
                                        setTransactionDetailsDialogVisibility(true)
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
                sectionInfo = SectionInfo(
                    {
                        ScreenSectionComponent(
                            title = stringResource(id = R.string.my_store_products),
                            body = {
                                ProductCarouselComponent(
                                    listOfProducts = listOfProducts,
                                    shouldItemBeVisible = currencyVisibility,
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
    isItemVisible: Boolean,
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
                shouldItemBeVisible = isItemVisible,
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
        leftContent = stringResource(id = R.string.my_store_debits),
        rightContent = {
            TextCurrencyComponent(
                value = resume.debits.toString(),
                currencyVisibility = shouldItemBeVisible,
                type = Type.CURRENCY_DEBIT_ONLY,
            )
        },
    )

    RowComponent(
        leftContent = stringResource(id = R.string.my_store_stock_value),
        rightContent = {
            TextCurrencyComponent(
                value = resume.stockValue.toString(),
                currencyVisibility = shouldItemBeVisible,
                type = Type.CURRENCY_ONLY,
            )
        },
    )

    RowComponent(
        leftContent = stringResource(id = R.string.my_store_gross_revenue),
        rightContent = {
            TextCurrencyComponent(
                value = resume.grossRevenue.toString(),
                currencyVisibility = shouldItemBeVisible,
                type = Type.CURRENCY_ONLY,
            )
        },
    )

    DividerComponent()

    RowComponent(
        leftContent = stringResource(id = R.string.my_store_net_revenue),
        fontSize = 20.sp,
        rightContent = {
            TextCurrencyComponent(
                value = resume.netRevenue.toString(),
                currencyVisibility = shouldItemBeVisible,
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
