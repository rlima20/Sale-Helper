package com.example.mystore.ui.components.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mystore.R
import com.example.mystore.Type
import com.example.mystore.ui.components.commons.ProductCarouselComponent
import com.example.mystore.ui.components.commons.RowComponent
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    shouldItemBeVisible: Boolean,
    onClick: () -> Unit = {},
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
                    onClick = { onClick() },
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
        textCurrency = resume.debits,
        shouldItemBeVisible = shouldItemBeVisible,
        stringId = R.string.my_store_debits,
        type = Type.CURRENCY,
    )

    RowComponent(
        textCurrency = resume.grossRevenue,
        shouldItemBeVisible = shouldItemBeVisible,
        stringId = R.string.my_store_gross_revenue,
        type = Type.CURRENCY,
    )

    RowComponent(
        textCurrency = resume.netRevenue,
        shouldItemBeVisible = shouldItemBeVisible,
        stringId = R.string.my_store_net_revenue,
        type = Type.CURRENCY,
    )

    RowComponent(
        textCurrency = resume.stockValue,
        shouldItemBeVisible = shouldItemBeVisible,
        stringId = R.string.my_store_stock_value,
        type = Type.CURRENCY,
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
