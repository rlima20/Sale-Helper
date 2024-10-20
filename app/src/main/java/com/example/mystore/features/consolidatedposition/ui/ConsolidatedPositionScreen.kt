package com.example.mystore.features.consolidatedposition.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mystore.R
import com.example.mystore.enums.Screens
import com.example.mystore.enums.Section
import com.example.mystore.enums.TransactionType
import com.example.mystore.enums.Type
import com.example.mystore.features.homescreen.viewmodel.HomeViewModel
import com.example.mystore.features.registertransaction.model.Transaction
import com.example.mystore.toStringResource
import com.example.mystore.ui.components.commons.RowComponent
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.components.commons.SectionEmptyStateInfo
import com.example.mystore.ui.components.commons.SectionInfo
import com.example.mystore.ui.components.commons.TextCurrencyComponent
import com.example.mystore.ui.components.commons.ValidateSection
import com.example.mystore.ui.components.commons.validateSection

@Composable
fun ConsolidatedPositionScreen(
    homeViewModel: HomeViewModel,
    shouldItemBeVisible: Boolean,
    onEmptyStateImageClicked: (route: String, screen: String) -> Unit = { _, _ -> },
    onShowBottomBarExpanded: @Composable (shouldSee: Boolean) -> Unit = {},
) {

    val listOfSales = homeViewModel.commonViewState.listOfSales.value
    val listOfPurchases = homeViewModel.commonViewState.listOfPurchases.value

    Column(
        modifier = Modifier
            .padding(bottom = 1.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        // Sale Section
        ValidateSection(
            screen = Screens.CONSOLIDATED_POSITION,
            sectionInfo = SectionInfo(
                sectionName = Section.TRANSACTIONS,
                section =
                {
                    ScreenSectionComponent(
                        title = stringResource(id = R.string.my_store_sales),
                        body = {
                            ConsolidatedPosBody(
                                transactions = listOfSales,
                                shouldItemBeVisible = shouldItemBeVisible,
                            )
                        },
                    )
                },
            ),
            sectionEmptyStateInfo = SectionEmptyStateInfo(
                data = listOfSales,
                emptySectionTitle = stringResource(R.string.my_store_no_sales_done),
                emptySectionPainter = painterResource(id = R.drawable.my_store_plus_icon),
                onEmptyStateImageClicked = {
                    onEmptyStateImageClicked(
                        validateSection(Section.TRANSACTIONS),
                        R.string.my_store_register_transaction.toStringResource()
                    )
                },
            ),
        )

        // Purchase Section
        ValidateSection(
            screen = Screens.CONSOLIDATED_POSITION,
            sectionInfo = SectionInfo(
                sectionName = Section.TRANSACTIONS,
                section =
                {
                    ScreenSectionComponent(
                        title = stringResource(id = R.string.my_store_purchases),
                        body = {
                            ConsolidatedPosBody(
                                transactions = listOfPurchases,
                                shouldItemBeVisible = shouldItemBeVisible,
                            )
                        },
                    )
                },
            ),
            sectionEmptyStateInfo = SectionEmptyStateInfo(
                data = listOfPurchases,
                emptySectionTitle = stringResource(R.string.my_store_no_purchases_done),
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
        onShowBottomBarExpanded(true)
    }
}

@Composable
fun ConsolidatedPosBody(
    transactions: List<Transaction>,
    shouldItemBeVisible: Boolean,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(transactions) { transaction ->
            Surface(
                shape = RoundedCornerShape(15.dp),
                elevation = 12.dp,
            ) {
                Column(
                    modifier = Modifier
                        .background(colorResource(id = R.color.white)),
                ) {
                    RowComponent(
                        leftSideText = stringResource(id = R.string.my_store_product),
                        rightSide = {
                            TextCurrencyComponent(
                                value = transaction.product.title,
                                shouldItemBeVisible = shouldItemBeVisible,
                                type = Type.STRING_ONLY,
                            )
                        },
                    )
                    RowComponent(
                        leftSideText = stringResource(id = R.string.my_store_date),
                        rightSide = {
                            TextCurrencyComponent(
                                value = transaction.transactionDate,
                                shouldItemBeVisible = shouldItemBeVisible,
                                type = Type.DATE,
                            )
                        },
                    )
                    RowComponent(
                        leftSideText = stringResource(id = R.string.my_store_quantity),
                        rightSide = {
                            TextCurrencyComponent(
                                value = transaction.quantity.toString(),
                                shouldItemBeVisible = shouldItemBeVisible,
                                type = Type.QUANTITY,
                            )
                        },
                    )
                    RowComponent(
                        leftSideText = stringResource(id = R.string.my_store_unit_value),
                        rightSide = {
                            TextCurrencyComponent(
                                value = transaction.unitValue.toString(),
                                shouldItemBeVisible = shouldItemBeVisible,
                                type = Type.CURRENCY_ONLY,
                            )
                        },
                    )
                    RowComponent(
                        leftSideText = setLeftSideText(transaction),
                        rightSide = {
                            TextCurrencyComponent(
                                value = transaction.transactionAmount.toString(),
                                shouldItemBeVisible = shouldItemBeVisible,
                                type = Type.CURRENCY_ONLY,
                            )
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun setLeftSideText(transaction: Transaction) =
    if (transaction.transactionType == TransactionType.PURCHASE) {
        stringResource(id = R.string.my_store_purchase_value)
    } else {
        stringResource(id = R.string.my_store_sale_value)
    }
