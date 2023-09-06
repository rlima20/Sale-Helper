package com.example.mystore.ui.components.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.mystore.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DropdownComponent(
    items: List<String>,
    isExpanded: Boolean,
    selectedText: String,
    textFieldSize: Size,
    label: String,
    modifier: Modifier = Modifier,
    transactionDetailColors: Triple<Int, Int, Int> = Triple(
        first = R.color.color_50,
        second = R.color.color_900,
        third = R.color.color_50,
    ),
    onOutLinedTextFieldSize: (size: Size) -> Unit = {},
    onOutLinedTextFieldValueChanged: (String) -> Unit = {},
    onTrailingIconClicked: () -> Unit = {},
    onDropdownMenuDismissRequest: () -> Unit = {},
    onDropdownMenuItemClicked: (String) -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column {
        OutLinedTextFieldComponent(
            enabled = false,
            modifier = modifier
                .background(colorResource(id = transactionDetailColors.second))
                .onGloballyPositioned { coordinates ->
                    onOutLinedTextFieldSize(coordinates.size.toSize())
                },
            selectedText = selectedText,
            label = label,
            transactionDetailColors = transactionDetailColors,
            keyboardController = keyboardController,
            focusManager = focusManager,
            onValueChanged = { onOutLinedTextFieldValueChanged(selectedText) },
            trailingIcon = {
                Icon(
                    setIcon(isExpanded),
                    "contentDescription",
                    Modifier.clickable { onTrailingIconClicked() },
                    tint = colorResource(id = R.color.color_50),
                )
            },
        )

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { onDropdownMenuDismissRequest() },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() }),
        ) {
            items.forEach { label ->
                DropdownMenuItem(onClick = {
                    onDropdownMenuItemClicked(label)
                    onDropdownMenuDismissRequest()
                }) {
                    Text(text = label)
                }
            }
        }
    }
}

@Composable
private fun setIcon(isExpanded: Boolean) = if (isExpanded) {
    Icons.Filled.KeyboardArrowUp
} else {
    Icons.Filled.KeyboardArrowDown
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DropdownComponent(
        isExpanded = false,
        modifier = Modifier.padding(16.dp),
        label = "Tipo de transação",
        items = listOf("A", "B", "C"),
        textFieldSize = Size.Zero,
        selectedText = "A",
    )
}
