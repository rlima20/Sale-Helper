package com.example.mystore.ui.components.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mystore.R
import com.example.mystore.commons.screenList
import com.example.mystore.ui.theme.mcpalette0_A800

@Composable
internal fun TopBarComponent(
    screenTitle: String,
    shouldItemBeVisible: Boolean,
    isMenuExpanded: Boolean,
    textFieldSize: Size,
    shouldDisplayIcon: Boolean = true,
    onIconVisibilityClicked: () -> Unit,
    onMenuIconClicked: () -> Unit,
    onDismissRequest: () -> Unit,
    onDropDownMenuItemClicked: (screen: String) -> Unit,
    onChangeTextFieldSize: (size: Size) -> Unit,
) {
    Row(
        modifier = Modifier
            .background(mcpalette0_A800)
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = screenTitle,
            color = Color.White,
            fontSize = 18.sp,
        )
        Row(
            modifier = Modifier
                .background(mcpalette0_A800)
                .padding(top = 12.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            if (shouldDisplayIcon) {
                Icon(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(20.dp)
                        .clickable(
                            onClick = {
                                onIconVisibilityClicked()
                            },
                        ),
                    painter = setPainter(shouldItemBeVisible),
                    contentDescription = null,
                    tint = Color.White,
                )
            }

            MenuComponent(
                screens = screenList,
                isMenuExpanded = isMenuExpanded,
                textFieldSize = textFieldSize,
                onMenuIconClicked = {
                    onMenuIconClicked()
                },
                onDismissRequest = {
                    onDismissRequest()
                },
                onDropDownMenuItemClicked = { screen ->
                    onDropDownMenuItemClicked(screen)
                },
                onChangeTextFieldSize = { size ->
                    onChangeTextFieldSize(size)
                },
            )
        }
    }
}

@Composable
private fun setPainter(shouldItemBeVisible: Boolean) = if (shouldItemBeVisible) {
    painterResource(
        id = R.drawable
            .my_store_show_icon,
    )
} else {
    painterResource(id = R.drawable.my_store_hidden_icon)
}
