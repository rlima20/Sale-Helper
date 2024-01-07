package com.example.mystore.ui.components.commons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.mystore.screenList

@Composable
fun MenuComponent(
    modifier: Modifier = Modifier,
    screens: List<String>,
    isMenuExpanded: Boolean,
    textFieldSize: Size,
    onMenuIconClicked: () -> Unit = {},
    onDismissRequest: () -> Unit = {},
    onDropDownMenuItemClicked: (String) -> Unit = {},
    onChangeTextFieldSize: (size: Size) -> Unit = {},
) {
    Column(modifier = modifier) {
        Icon(
            imageVector = Icons.Rounded.Menu,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.clickable(
                onClick = {
                    onMenuIconClicked()
                },
            ),
        )

        DropdownMenu(
            expanded = isMenuExpanded,
            onDismissRequest = { onDismissRequest() },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    onChangeTextFieldSize(coordinates.size.toSize())
                }
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() }),
        ) {
            screens.forEach { screenName ->
                DropdownMenuItem(
                    onClick = {
                        onDropDownMenuItemClicked(screenName)
                    },
                    content = {
                        Column {
                            Text(
                                text = screenName,
                                fontSize = 16.sp,
                            )
                        }
                    },
                )
            }
        }
    }
}

@Composable
@Preview
fun DropDownComponentPreview() {
    MenuComponent(
        screens = screenList,
        isMenuExpanded = false,
        textFieldSize = Size(0f, 0f),
    )
}
