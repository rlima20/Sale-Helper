package com.example.mystore.ui.components.screens

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
import com.example.mystore.Screens
import com.example.mystore.Section
import com.example.mystore.TransactionType
import com.example.mystore.Type
import com.example.mystore.model.SectionEmptyStateInfo
import com.example.mystore.model.SectionInfo
import com.example.mystore.model.screen.Transaction
import com.example.mystore.toStringResource
import com.example.mystore.ui.components.commons.RowComponent
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.components.commons.TextCurrencyComponent
import com.example.mystore.ui.components.commons.ValidateSection
import com.example.mystore.ui.components.commons.validateSection
import com.example.mystore.viewmodel.screen.HomeViewModel

@Composable
fun ConsolidatedPositionScreen(
    homeViewModel: HomeViewModel,
    shouldItemBeVisible: Boolean,
    onEmptyStateImageClicked: (route: String, screen: String) -> Unit = { _, _ -> },
    onShowBottomBarExpanded: (shouldSee: Boolean) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .padding(bottom = 1.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        // Sale Section
        ValidateSection(
            screen = Screens.CONSOLIDATED_POSITION,
            sectionInfo = SectionInfo(
                {
                    ScreenSectionComponent(
                        title = stringResource(id = R.string.my_store_sales),
                        body = {
                            ConsolidatedPosBody(
                                transactions = homeViewModel.listOfSales.value,
                                shouldItemBeVisible = shouldItemBeVisible,
                            )
                        },
                    )
                },
            ),
            sectionEmptyStateInfo = SectionEmptyStateInfo(
                data = homeViewModel.listOfSales.value,
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
                {
                    ScreenSectionComponent(
                        title = stringResource(id = R.string.my_store_purchases),
                        body = {
                            ConsolidatedPosBody(
                                transactions = homeViewModel.listOfPurchases.value,
                                shouldItemBeVisible = shouldItemBeVisible,
                            )
                        },
                    )
                },
            ),
            sectionEmptyStateInfo = SectionEmptyStateInfo(
                data = homeViewModel.listOfPurchases.value,
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
                        leftContent = stringResource(id = R.string.my_store_product),
                        rightContent = {
                            TextCurrencyComponent(
                                value = transaction.product.title,
                                currencyVisibility = shouldItemBeVisible,
                                type = Type.STRING_ONLY,
                            )
                        },
                    )
                    RowComponent(
                        leftContent = stringResource(id = R.string.my_store_date),
                        rightContent = {
                            TextCurrencyComponent(
                                value = transaction.transactionDate,
                                currencyVisibility = shouldItemBeVisible,
                                type = Type.DATE,
                            )
                        },
                    )
                    RowComponent(
                        leftContent = stringResource(id = R.string.my_store_quantity),
                        rightContent = {
                            TextCurrencyComponent(
                                value = transaction.quantity.toString(),
                                currencyVisibility = shouldItemBeVisible,
                                type = Type.QUANTITY,
                            )
                        },
                    )
                    RowComponent(
                        leftContent = stringResource(id = R.string.my_store_unit_value),
                        rightContent = {
                            TextCurrencyComponent(
                                value = transaction.unitValue.toString(),
                                currencyVisibility = shouldItemBeVisible,
                                type = Type.CURRENCY_ONLY,
                            )
                        },
                    )
                    RowComponent(
                        leftContent = setLeftSideText(transaction),
                        rightContent = {
                            TextCurrencyComponent(
                                value = transaction.transactionAmount.toString(),
                                currencyVisibility = shouldItemBeVisible,
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
