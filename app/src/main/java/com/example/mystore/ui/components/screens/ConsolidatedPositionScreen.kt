package com.example.mystore.ui.components.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.mystore.ui.components.commons.ScreenSectionComponent

@Composable
fun TransactionScreen(shouldItemBeVisible: Boolean) {
    ScreenSectionComponent(
        body = { Text(text = "Posição consolidada") },
        title = "Posição consolidada",
    )
}
