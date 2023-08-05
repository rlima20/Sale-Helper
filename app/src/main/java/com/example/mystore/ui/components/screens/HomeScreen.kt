package com.example.mystore.ui.components.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mystore.R
import com.example.mystore.ui.components.RowComponent
import com.example.mystore.ui.components.ScreenSectionComponent
import com.example.mystore.viewmodel.HomeViewModel
import com.example.mystore.viewmodel.Resume

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    shouldItemBeVisible: Boolean,
) {
    ScreenSectionComponent(
        body = {
            HomeBody(
                homeViewModel.getResume(),
                shouldItemBeVisible,
            )
        },
        title = "Total geral",
    )
}

@Composable
fun HomeBody(
    resume: Resume,
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
