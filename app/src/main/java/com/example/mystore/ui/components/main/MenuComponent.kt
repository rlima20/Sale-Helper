package com.example.mystore.ui.components.main

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
import com.example.mystore.commons.constants.screenList
import com.example.mystore.ui.model.Callbacks
import com.example.mystore.ui.model.MenuComponentProps

@Composable
fun MenuComponent(
    menuComponentProps: MenuComponentProps
) {
    with(menuComponentProps) {
        Column(modifier = modifier) {
            Icon(
                imageVector = Icons.Rounded.Menu,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.clickable(
                    onClick = {
                        callbacks.onMenuIconClicked()
                    },
                ),
            )

            DropdownMenu(
                expanded = isMenuExpanded,
                onDismissRequest = { callbacks.onDismissRequest() },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        callbacks.onChangeTextFieldSize(coordinates.size.toSize())
                    }
                    .width(with(LocalDensity.current) { menuDropdownWidth.width.toDp() }),
            ) {
                screens.forEach { screenName ->
                    DropdownMenuItem(
                        onClick = {
                            callbacks.onDropDownMenuItemClicked(screenName)
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
}

@Composable
@Preview
fun DropDownComponentPreview() {
    MenuComponent(
        menuComponentProps = MenuComponentProps(
            screens = screenList,
            isMenuExpanded = false,
            menuDropdownWidth = Size(0f, 0f),
            callbacks = Callbacks()
        )
    )
}
