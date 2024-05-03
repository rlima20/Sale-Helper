package com.example.mystore.model.props

import androidx.navigation.NavHostController

data class NavigationProps(
    val navController: NavHostController,
    val onNavigateToHome: () -> Unit = {},
)