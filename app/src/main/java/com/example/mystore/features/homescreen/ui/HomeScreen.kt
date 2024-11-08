package com.example.mystore.features.homescreen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.example.mystore.features.homescreen.model.DisplayAlertDialogProductDeleteConfirmationProps
import com.example.mystore.features.homescreen.model.DisplayAlertDialogTransactionDeleteConfirmationProps
import com.example.mystore.features.homescreen.model.DisplayAlertDialogWithTransactionDetailsProps
import com.example.mystore.features.homescreen.model.HomeScreenProps
import com.example.mystore.features.homescreen.model.ProductSectionProps
import com.example.mystore.features.homescreen.model.Resume
import com.example.mystore.features.homescreen.model.ResumeSectionProps
import com.example.mystore.features.homescreen.model.TransactionProps
import com.example.mystore.features.homescreen.model.TransactionSectionProps
import com.example.mystore.features.homescreen.viewmodel.HomeViewModel
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
import com.example.mystore.ui.navigation.EditTransactionScreen

@Composable
fun HomeScreen(
    homeScreenProps: HomeScreenProps
) {
    with(homeScreenProps.homeViewModel) {

        getResume()
        getListOfSales()
        getListOfPurchases()

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

        DisplayConfirmationToast(showToast)

        with(homeScreenProps) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(bottom = 16.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                // AlertDialog with transaction details
                DisplayAlertDialogWithTransactionDetails(
                    displayAlertDialogWithTransactionDetailsProps = DisplayAlertDialogWithTransactionDetailsProps(
                        showAlertDialogTransactionDetail,
                        transaction,
                        shouldItemBeVisible
                    )
                )

                // AlertDialog Transaction delete confirmation
                DisplayAlertDialogTransactionDeleteConfirmation(
                    displayAlertDialogTransactionDeleteConfirmationProps = DisplayAlertDialogTransactionDeleteConfirmationProps(
                        showAlertDialogHomeScreen,
                        transactionToastMessage,
                        transaction
                    )
                )

                // AlertDialog Product delete confirmation
                DisplayAlertDialogProductDeleteConfirmation(
                    displayAlertDialogProductDeleteConfirmationProps = DisplayAlertDialogProductDeleteConfirmationProps(
                        showAlertDialogHomeScreenProduct,
                        productToastMessage,
                        product
                    )
                )

                // Total geral Section
                ResumeSection(
                    resumeSectionProps = ResumeSectionProps(
                        resume,
                        shouldItemBeVisible,
                        onEmptyStateImageClicked
                    )
                )

                // Transactions Section
                TransactionSection(
                    transactionSectionProps = TransactionSectionProps(
                        listOfTransactions,
                        shouldItemBeVisible,
                        onEmptyStateImageClicked,
                        onEditTransactionIconClick = { transaction ->
                            onNavigateToEditTransactionScreen(
                                EditTransactionScreen.route,
                                transaction
                            )
                        }
                    )
                )

                // Products Section
                ProductSection(
                    productSectionProps = ProductSectionProps(
                        listOfProducts,
                        shouldItemBeVisible,
                        onEditMode,
                        onProductClick,
                        onProductDoubleClick,
                        onEmptyStateImageClicked
                    )
                )

                // Sets visibility of Toast to false
                setShowToastState(productToastMessage, false)
            }
        }
    }
}


@Composable
private fun ResumeSection(
    resumeSectionProps: ResumeSectionProps
) {
    with(resumeSectionProps) {
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
    }
}

@Composable
private fun HomeViewModel.ProductSection(
    productSectionProps: ProductSectionProps
) {
    with(productSectionProps) {
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
    }
}

@Composable
private fun HomeViewModel.TransactionSection(
    transactionSectionProps: TransactionSectionProps
) {
    with(transactionSectionProps) {
        ValidateSection(
            screen = Screens.HOME,
            sectionInfo = SectionInfo(
                sectionName = Section.TRANSACTIONS,
                section = {
                    ScreenSectionComponent(
                        title = stringResource(id = R.string.my_store_transactions),
                        body = {
                            Transactions(
                                transactionProps = TransactionProps(
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
                                    onEditTransactionIconClick = {
                                        onEditTransactionIconClick(it)
                                    }
                                ),
                            )
                        },
                    )
                },
            ),
            sectionEmptyStateInfo = SectionEmptyStateInfo(
                data = listOfTransactions.ifEmpty { listOf() },
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
    }
}

@Composable
private fun HomeViewModel.DisplayAlertDialogProductDeleteConfirmation(
    displayAlertDialogProductDeleteConfirmationProps: DisplayAlertDialogProductDeleteConfirmationProps
) {
    with(displayAlertDialogProductDeleteConfirmationProps) {
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
            }
        )
    }
}

@Composable
private fun HomeViewModel.DisplayAlertDialogTransactionDeleteConfirmation(
    displayAlertDialogTransactionDeleteConfirmationProps: DisplayAlertDialogTransactionDeleteConfirmationProps
) {
    with(displayAlertDialogTransactionDeleteConfirmationProps) {
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
    }
}

@Composable
private fun HomeViewModel.DisplayAlertDialogWithTransactionDetails(
    displayAlertDialogWithTransactionDetailsProps: DisplayAlertDialogWithTransactionDetailsProps
) {
    with(displayAlertDialogWithTransactionDetailsProps) {
        if (showAlertDialogTransactionDetail) {
            AlertDialogComponent(
                size = setSize(),
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
    }
}

@Composable
private fun DisplayConfirmationToast(showToast: Pair<String, Boolean>) {
    if (showToast.second) ToastComponent(showToast.first)
}

@Composable
private fun Transactions(
    transactionProps: TransactionProps
) {
    with(transactionProps) {
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
                    onEditTransactionIconClick = { onEditTransactionIconClick(it) }
                )
            }
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

@Composable
private fun setSize() = Size(
    width = LocalConfiguration.current.screenWidthDp.dp.value * 1f,
    height = LocalConfiguration.current.screenHeightDp.dp.value * 0.58f,
)

@Preview
@Composable
fun ScreenPreview() {
    ScreenSectionComponent(
        body = { Text(text = "Posição consolidada") },
        title = "Posição consolidada",
    )
}
