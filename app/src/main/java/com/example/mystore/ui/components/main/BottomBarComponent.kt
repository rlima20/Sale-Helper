package com.example.mystore.ui.components.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mystore.R
import com.example.mystore.ui.model.BottomBarComponentProps
import com.example.mystore.ui.theme.mcpalette0_A900

@Composable
internal fun BottomBarComponent(
    bottomBarComponentProps: BottomBarComponentProps
) {
    with(bottomBarComponentProps) {
        Column {
            SetExpandedBottomBar()
            Row(
                modifier = Modifier
                    .background(mcpalette0_A900)
                    .padding(12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                SetHomeIcon()
                SetPositionConsolidatedIcon()
                SetRegisterTransactionIcon()
                SetRegisterProductIcon()
            }
        }
    }
}

@Composable
private fun BottomBarComponentProps.SetRegisterProductIcon() {
    Icon(
        modifier = Modifier
            .padding(end = 16.dp)
            .clickable(
                onClick = {
                    onRegisterProductIconClicked()
                },
            ),
        imageVector = Icons.Rounded.Add,
        contentDescription = null,
        tint = Color.Black,
    )
}

@Composable
private fun BottomBarComponentProps.SetRegisterTransactionIcon() {
    Icon(
        modifier = Modifier
            .size(20.dp)
            .clickable(
                onClick = {
                    onRegisterTransactionIconClicked()
                },
            ),
        painter = painterResource(id = R.drawable.my_store_transaction_icon),
        contentDescription = null,
        tint = Color.Black,
    )
}

@Composable
private fun BottomBarComponentProps.SetPositionConsolidatedIcon() {
    Icon(
        modifier = Modifier
            .padding(start = 16.dp)
            .size(20.dp)
            .clickable(
                onClick = {
                    onPositionConsolidateIconClicked()
                },
            ),
        painter = painterResource(id = R.drawable.my_store_consolidated_position_icon),
        contentDescription = null,
        tint = Color.Black,
    )
}

@Composable
private fun BottomBarComponentProps.SetHomeIcon() {
    Icon(
        modifier = Modifier
            .padding(end = 16.dp)
            .clickable(
                onClick = {
                    onHomeIconClicked()
                },
            ),
        imageVector = Icons.Rounded.Home,
        contentDescription = null,
        tint = Color.Black,
    )
}

@Composable
private fun BottomBarComponentProps.SetExpandedBottomBar() {
    if (expandedBottomBar) {
        Row(
            modifier = Modifier
                .background(mcpalette0_A900)
                .fillMaxWidth(),
        ) {
            expandedBottomBarContent()
        }
    }
}
