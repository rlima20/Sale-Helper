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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mystore.commons.constants.screenList
import com.example.mystore.setPainter
import com.example.mystore.ui.model.TopBarComponentProps
import com.example.mystore.ui.theme.mcpalette0_A800

@Composable
internal fun TopBarComponent(
    topBarComponentProps: TopBarComponentProps
) {
    with(topBarComponentProps) {
        Row(
            modifier = Modifier
                .background(mcpalette0_A800)
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = visualProperties.screenTitle,
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
                SetIconVisibility()
                MenuComponent(
                    screens = screenList,
                    isMenuExpanded = menuActions.isMenuExpanded,
                    menuDropdownWidth = dropdownMenuProperties.dropdownMenuWidth,
                    onMenuIconClicked = { menuActions.onMenuIconClicked() },
                    onDismissRequest = { menuActions.onDismissRequest() },
                    onDropDownMenuItemClicked = { screen ->
                        dropdownMenuProperties.onDropDownMenuItemClicked(
                            screen
                        )
                    },
                    onChangeTextFieldSize = { size ->
                        dropdownMenuProperties.onChangeDropdownMenuWidth(
                            size
                        )
                    },
                )
            }
        }
    }
}

@Composable
private fun TopBarComponentProps.SetIconVisibility() {
    with(visualProperties) {
        if (isIconVisible) {
            Icon(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(20.dp)
                    .clickable(
                        onClick = { onIconVisibilityClicked() },
                    ),
                painter = isIconLined.setPainter(),
                contentDescription = null,
                tint = Color.White,
            )
        }
    }
}