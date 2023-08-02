package com.example.mystore.ui.components.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mystore.R
import com.example.mystore.screenList
import com.example.mystore.toParse
import com.example.mystore.ui.theme.secondary

@Composable
internal fun TopBarComponent(
    screenTitle: String,
    shouldItemBeVisible: Boolean,
    isMenuExpanded: Boolean,
    textFieldSize: Size,
    onHomeIconClicked: () -> Unit,
    onIconVisibilityClicked: () -> Unit,
    onMenuIconClicked: () -> Unit,
    onDismissRequest: () -> Unit,
    onDropDownMenuItemClicked: (screen: String) -> Unit,
    onChangeTextFieldSize: (size: Size) -> Unit,
) {
    Row(
        modifier = Modifier
            .background(secondary)
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
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
            tint = Color.White,
        )

        Text(
            text = screenTitle,
            color = Color.White,
            fontSize = 18.sp,
        )

        Icon(
            modifier = Modifier
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

        MenuComponent(
            screens = screenList.toParse(),
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

@Composable
private fun setPainter(shouldItemBeVisible: Boolean) = if (shouldItemBeVisible) {
    painterResource(
        id = R.drawable
            .my_store_show_icon,
    )
} else {
    painterResource(id = R.drawable.my_store_hidden_icon)
}
