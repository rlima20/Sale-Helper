package com.example.mystore.model

import androidx.compose.ui.graphics.painter.Painter

data class SectionEmptyStateInfo(
    val data: List<Any?> = listOf(),
    val emptySectionTitle: String = "",
    val emptySectionPainter: Painter,
    val onEmptyStateImageClicked: () -> Unit = {},
)