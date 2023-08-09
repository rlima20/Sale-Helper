package com.example.mystore.ui.components.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mystore.R
import com.example.mystore.Type
import com.example.mystore.model.Product
import com.example.mystore.ui.components.commons.ProductCarouselComponent
import com.example.mystore.ui.components.commons.RowComponent
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.components.commons.TextCurrencyComponent
import com.example.mystore.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    shouldItemBeVisible: Boolean,
    onClick: (product: Product) -> Unit = {},
    onLongClick: () -> Unit = {},
    onDloubleClick: () -> Unit = {},
) {
    Column {
        ScreenSectionComponent(
            title = "Total geral",
            body = {
                HomeBody(
                    homeViewModel.getResume(),
                    shouldItemBeVisible,
                )
            },
        )

        ScreenSectionComponent(
            title = "Produtos",
            body = {
                ProductCarouselComponent(
                    shouldItemBeVisible = shouldItemBeVisible,
                    onImageRequestState = { state ->
                        homeViewModel.setImageRequestState(state)
                    },
                    onClick = { onClick(it) },
                    onLongClick = { onLongClick() },
                    onDoubleClick = { onDloubleClick() },
                )
            },
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
