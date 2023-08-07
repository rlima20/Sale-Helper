package com.example.mystore.ui.components.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mystore.R
import com.example.mystore.ui.components.commons.ProductCarouselComponent
import com.example.mystore.ui.components.commons.RowComponent
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    shouldItemBeVisible: Boolean,
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
        resume.debits,
        shouldItemBeVisible,
        R.string.my_store_debits,
    )

    RowComponent(
        resume.grossRevenue,
        shouldItemBeVisible,
        R.string.my_store_gross_revenue,
    )

    RowComponent(
        resume.netRevenue,
        shouldItemBeVisible,
        R.string.my_store_net_revenue,
    )

    RowComponent(
        resume.stockValue,
        shouldItemBeVisible,
        R.string.my_store_stock_value,
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
