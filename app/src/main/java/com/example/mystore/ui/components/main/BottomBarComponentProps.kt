package com.example.mystore.ui.components.main

import androidx.compose.runtime.Composable

data class BottomBarComponentProps(
    val onPositionConsolidateIconClicked: () -> Unit,
    val onRegisterTransactionIconClicked: () -> Unit,
    val onRegisterProductIconClicked: () -> Unit,
    val onHomeIconClicked: () -> Unit,
    val expandedBottomBar: Boolean = false,
    val expandedBottomBarContent: @Composable () -> Unit = {}
)