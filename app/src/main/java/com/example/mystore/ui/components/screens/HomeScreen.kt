package com.example.mystore.ui.components.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
import com.example.mystore.listOfProductsLocal
import com.example.mystore.listOfTransactions
import com.example.mystore.model.Product
import com.example.mystore.model.Resume
import com.example.mystore.model.Transaction
import com.example.mystore.ui.components.commons.ProductCarouselComponent
import com.example.mystore.ui.components.commons.RowComponent
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.components.commons.SectionEmptyStateInfo
import com.example.mystore.ui.components.commons.SectionInfo
import com.example.mystore.ui.components.commons.TextCurrencyComponent
import com.example.mystore.ui.components.commons.TransactionComponent
import com.example.mystore.ui.components.commons.ValidateSection
import com.example.mystore.ui.components.commons.validateSection
import com.example.mystore.viewmodel.screen.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    shouldItemBeVisible: Boolean,
    onProductClick: (product: Product) -> Unit = {},
    onProductLongClick: () -> Unit = {},
    onProductDoubleClick: () -> Unit = {},
    onEmptyStateImageClicked: (route: String) -> Unit = {},
) {
    homeViewModel.getResume()
    homeViewModel.getListOfSales()
    homeViewModel.getListOfPurchases()

    val resume by homeViewModel.resume.collectAsState()
    val listOfProducts by homeViewModel.listOfProducts.collectAsState()
    val listOfTransaction by homeViewModel.transactions.collectAsState()

    Column(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .verticalScroll(rememberScrollState()),
    ) {
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
                    )
                },
            ),
            screen = Screens.HOME,
        )

        ValidateSection(
            sectionInfo = SectionInfo {
                ScreenSectionComponent(
                    title = stringResource(id = R.string.my_store_products),
                    body = {
                        ProductCarouselComponent(
                            listOfProductsLocal = listOfProductsLocal,
                            shouldItemBeVisible = shouldItemBeVisible,
                            onImageRequestState = { state ->
                                homeViewModel.setImageRequestState(state)
                            },
                            onProductClick = { onProductClick(it) },
                            onProductLongClick = { onProductLongClick() },
                            onProductDoubleClick = { onProductDoubleClick() },
                        )
                    },
                )
            },
            sectionEmptyStateInfo =
            SectionEmptyStateInfo(
                data = listOfProducts,
                emptySectionTitle = stringResource(R.string.my_store_no_products),
                emptySectionPainter = painterResource(id = R.drawable.my_store_plus_icon),
                onEmptyStateImageClicked = {
                    onEmptyStateImageClicked(
                        validateSection(
                            Section.PRODUCTS,
                        ),
                    )
                },
            ),
            screen = Screens.HOME,
        )

        ValidateSection(
            sectionInfo = SectionInfo(
                section = {
                    ScreenSectionComponent(
                        title = stringResource(id = R.string.my_store_transactions),
                        body = {
                            HomeTransactions(
                                listOfTransactions = listOfTransaction,
                            )
                        },
                    )
                },
            ),
            sectionEmptyStateInfo = SectionEmptyStateInfo(
                data = listOf(listOfTransactions),
                emptySectionTitle = stringResource(R.string.my_store_no_transactions_done),
                emptySectionPainter = painterResource(id = R.drawable.my_store_plus_icon),
                onEmptyStateImageClicked = {
                    onEmptyStateImageClicked(
                        validateSection(
                            Section.TRANSACTIONS,
                        ),
                    )
                },
            ),
            screen = Screens.HOME,
        )
    }
}

@Composable
fun HomeTransactions(
    listOfTransactions: List<Transaction> = listOf(),
) {
    Column(
        modifier = Modifier
            .height(200.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        listOfTransactions.forEach { transaction ->
            TransactionComponent(
                transaction = transaction,
            )
        }
    }
}

@Composable
fun HomeBody(
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
                type = Type.CURRENCY,
            )
        },
    )

    RowComponent(
        leftSideText = stringResource(id = R.string.my_store_gross_revenue),
        rightSide = {
            TextCurrencyComponent(
                value = resume.grossRevenue.toString(),
                shouldItemBeVisible = shouldItemBeVisible,
                type = Type.CURRENCY,
            )
        },
    )

    // Horizontal divider
    Divider(
        color = colorResource(id = R.color.color_300),
        thickness = 1.dp,
        modifier = Modifier.padding(8.dp),
    )

    RowComponent(
        leftSideText = stringResource(id = R.string.my_store_net_revenue),
        fontSize = 20.sp,
        rightSide = {
            TextCurrencyComponent(
                value = resume.netRevenue.toString(),
                shouldItemBeVisible = shouldItemBeVisible,
                type = Type.CURRENCY,
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
