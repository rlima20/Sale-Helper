package com.example.mystore.ui.components.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mystore.R
import com.example.mystore.Section
import com.example.mystore.Type
import com.example.mystore.listOfProductsLocal
import com.example.mystore.model.Product
import com.example.mystore.ui.components.commons.ProductCarouselComponent
import com.example.mystore.ui.components.commons.RowComponent
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.components.commons.SectionEmptyStateInfo
import com.example.mystore.ui.components.commons.SectionInfo
import com.example.mystore.ui.components.commons.TextCurrencyComponent
import com.example.mystore.ui.components.commons.ValidateSection
import com.example.mystore.ui.components.commons.validateSection
import com.example.mystore.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    shouldItemBeVisible: Boolean,
    onClick: (product: Product) -> Unit = {},
    onLongClick: () -> Unit = {},
    onDoubleClick: () -> Unit = {},
    onEmptyStateImageClicked: (route: String) -> Unit = {},
) {
    val resume = homeViewModel.getResume()

    Column {
        ValidateSection(
            sectionInfo = SectionInfo(
                section = {
                    ScreenSectionComponent(
                        title = "Total geral",
                        body = {
                            HomeBody(
                                resume,
                                shouldItemBeVisible,
                            )
                        },
                    )
                },
            ),
            sectionEmptyStateInfo = SectionEmptyStateInfo(
                data = listOf(resume),
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
        )

        ValidateSection(
            // data = listOfProductsLocal,
            sectionInfo = SectionInfo {
                ScreenSectionComponent(
                    title = "Produtos",
                    body = {
                        ProductCarouselComponent(
                            listOfProductsLocal = listOfProductsLocal,
                            shouldItemBeVisible = shouldItemBeVisible,
                            onImageRequestState = { state ->
                                homeViewModel.setImageRequestState(state)
                            },
                            onClick = { onClick(it) },
                            onLongClick = { onLongClick() },
                            onDoubleClick = { onDoubleClick() },
                        )
                    },
                )
            },
            sectionEmptyStateInfo =
            SectionEmptyStateInfo(
                data = listOf(),
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
        )
    }
}

@Composable
fun HomeBody(
    resume: HomeViewModel.Resume,
    shouldItemBeVisible: Boolean,
) {
    RowComponent(
        leftSideText = stringResource(id = R.string.my_store_debits),
        rightSide = {
            TextCurrencyComponent(
                value = resume.debits.toString(),
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

    RowComponent(
        leftSideText = stringResource(id = R.string.my_store_net_revenue),
        rightSide = {
            TextCurrencyComponent(
                value = resume.netRevenue.toString(),
                shouldItemBeVisible = shouldItemBeVisible,
                type = Type.CURRENCY,
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
}

@Preview
@Composable
fun ScreenPreview() {
    ScreenSectionComponent(
        body = { Text(text = "Posição consolidada") },
        title = "Posição consolidada",
    )
}
