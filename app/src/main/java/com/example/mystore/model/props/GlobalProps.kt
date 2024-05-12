package com.example.mystore.model.props

import androidx.compose.ui.Modifier
import com.example.mystore.model.screen.Product

data class GlobalProps(
    val modifier: Modifier = Modifier,
    val currencyVisibility: Boolean,
    val isEditMode: Boolean,
    val onExpandBottomBar: (Boolean) -> Unit = {},
    val onShowBottomBarExpanded: (sales: Double, purchase: Double) -> Unit = { _: Double, _: Double -> },
    val onUpdateTopBarText: (text: String) -> Unit = {},
    val onEditMode: (Boolean, Product) -> Unit = { _, _ -> },
)