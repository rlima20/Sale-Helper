package com.example.salehelper.ui.components

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

@Composable
fun DropDownComponent() {
    var mExpanded by remember { mutableStateOf(false) }
    val mCities =
        listOf(
            "Home",
            "Cadastro de produtos",
            "Registro de transação",
            "Posição consolidada",
            )
    var mSelectedText by remember { mutableStateOf("") }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    Column {
        Icon(
            Icons.Rounded.Menu,
            "contentDescription",
            Modifier.clickable(
                onClick = {
                    mExpanded = !mExpanded
                },
            ),
        )

        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = {
                mExpanded = false
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                }
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() }),
        ) {
            mCities.forEach { label ->
                DropdownMenuItem(
                    onClick = {
                        mSelectedText = label
                        mExpanded = false
                    },
                    content = {
                        Column {
                            Text(
                                text = label,
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
fun Dropdown(
    mExpanded: Boolean,
    mCities: List<String>,
    mTextFieldSize: Size,
    onDismissRequest: () -> Unit = {},
    onDropdownMenuItemClicked: (String) -> Unit = {},
) {
    Column {
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = {
                onDismissRequest()
            },
            modifier = Modifier
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() }),
        ) {
            mCities.forEach { label ->
                DropdownMenuItem(
                    onClick = {
                        onDropdownMenuItemClicked(label)
                    },
                    content = {
                        Column {
                            Text(
                                text = label,
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
    DropDownComponent()
}
