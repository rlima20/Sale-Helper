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
import com.example.mystore.Section
import com.example.mystore.Type
import com.example.mystore.toShortString
import com.example.mystore.ui.components.commons.RowComponent
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.components.commons.SectionEmptyStateInfo
import com.example.mystore.ui.components.commons.SectionInfo
import com.example.mystore.ui.components.commons.TextCurrencyComponent
import com.example.mystore.ui.components.commons.ValidateSection
import com.example.mystore.ui.components.commons.validateSection
import com.example.mystore.viewmodel.ConsolidatedPosViewModel

// todo - Se não tiver compras nem vendas não faz sentido mostrar a bottomBar expandida.
@Composable
fun ConsolidatedPositionScreen(
    consolidatedPosViewModel: ConsolidatedPosViewModel,
    shouldItemBeVisible: Boolean,
    onEmptyStateImageClicked: (route: String) -> Unit = {},
    onShowBottomBarExpanded: (shouldSee: Boolean) -> Unit = {},
) {
    val sales = consolidatedPosViewModel.getListOfSales()
    val purchases = consolidatedPosViewModel.getPurchases()

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
                            consolidatedPosViewModel.getListOfSales(),
                            shouldItemBeVisible,
                        )
                    },
                )
            },
            sectionEmptyStateInfo = SectionEmptyStateInfo(
                data = sales,
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
        )
        ValidateSection(
            sectionInfo = SectionInfo {
                ScreenSectionComponent(
                    title = "Compras",
                    body = {
                        ConsolidatedPosBody(
                            purchases,
                            shouldItemBeVisible,
                        )
                    },
                )
            },
            sectionEmptyStateInfo = SectionEmptyStateInfo(
                data = purchases,
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
        )
        onShowBottomBarExpanded(true)
    }
}

@Composable
fun ConsolidatedPosBody(
    transactions: List<ConsolidatedPosViewModel.Transaction>,
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
                        leftSideText = stringResource(id = R.string.my_store_sale_value),
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
