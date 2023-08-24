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
import com.example.mystore.model.Transaction
import com.example.mystore.toShortString
import com.example.mystore.ui.components.commons.RowComponent
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.components.commons.SectionEmptyStateInfo
import com.example.mystore.ui.components.commons.SectionInfo
import com.example.mystore.ui.components.commons.TextCurrencyComponent
import com.example.mystore.ui.components.commons.ValidateSection
import com.example.mystore.ui.components.commons.validateSection
import com.example.mystore.viewmodel.screen.HomeViewModel

@Composable
fun ConsolidatedPositionScreen(
    homeViewModel: HomeViewModel,
    shouldItemBeVisible: Boolean,
    onEmptyStateImageClicked: (route: String) -> Unit = {},
    onShowBottomBarExpanded: (shouldSee: Boolean) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .padding(bottom = 1.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        ValidateSection(
            sectionInfo = SectionInfo {
                ScreenSectionComponent(
                    title = "Vendas",
                    body = {
                        ConsolidatedPosBody(
                            homeViewModel.listOfSales.value,
                            shouldItemBeVisible,
                        )
                    },
                )
            },
            sectionEmptyStateInfo = SectionEmptyStateInfo(
                data = homeViewModel.listOfSales.value,
                emptySectionTitle = stringResource(R.string.my_store_no_sales_done),
                emptySectionPainter = painterResource(id = R.drawable.my_store_plus_icon),
                onEmptyStateImageClicked = {
                    onEmptyStateImageClicked(
                        validateSection(
                            Section.REGISTER,
                        ),
                    )
                },
            ),
            screen = Screens.CONSOLIDATED_POSITION,
        )
        ValidateSection(
            sectionInfo = SectionInfo {
                ScreenSectionComponent(
                    title = "Compras",
                    body = {
                        ConsolidatedPosBody(
                            homeViewModel.listOfPurchases.value,
                            shouldItemBeVisible,
                        )
                    },
                )
            },
            sectionEmptyStateInfo = SectionEmptyStateInfo(
                data = homeViewModel.listOfPurchases.value,
                emptySectionTitle = stringResource(R.string.my_store_no_sales_done),
                emptySectionPainter = painterResource(id = R.drawable.my_store_plus_icon),
                onEmptyStateImageClicked = {
                    onEmptyStateImageClicked(
                        validateSection(
                            Section.REGISTER,
                        ),
                    )
                },
            ),
            screen = Screens.CONSOLIDATED_POSITION,
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
                        .background(colorResource(id = R.color.color_800)),
                ) {
                    RowComponent(
                        leftSideText = stringResource(id = R.string.my_store_product),
                        rightSide = {
                            TextCurrencyComponent(
                                value = transaction.product.title,
                                shouldItemBeVisible = shouldItemBeVisible,
                                type = Type.STRING,
                            )
                        },
                    )
                    RowComponent(
                        leftSideText = stringResource(id = R.string.my_store_date),
                        rightSide = {
                            TextCurrencyComponent(
                                value = transaction.transactionDate.toShortString(),
                                shouldItemBeVisible = shouldItemBeVisible,
                                type = Type.STRING,
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
                                type = Type.CURRENCY,
                            )
                        },
                    )
                    RowComponent(
                        leftSideText = setLeftSideText(transaction),
                        rightSide = {
                            TextCurrencyComponent(
                                value = transaction.transactionAmount.toString(),
                                shouldItemBeVisible = shouldItemBeVisible,
                                type = Type.CURRENCY,
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
