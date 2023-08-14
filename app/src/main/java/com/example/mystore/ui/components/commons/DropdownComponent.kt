package com.example.mystore.ui.components.commons

import android.util.Size
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DropdownComponent(
    isExpanded: Boolean,
    options: List<String>,
    selectedText: String,
    textFieldSize: Size = Size(0, 0),
    onOutLinedTextFieldSize: (coordinates: LayoutCoordinates) -> Unit = {}, // todo textFieldSize = coordinates.size.toSize()
    onOutLinedTextFieldValueChanged: () -> Unit = {}, // todo selectedText = it
    onTrailingIconClicked: () -> Unit = {}, // todo - isExpanded = !isExpanded
    onDropdownMenuDismissRequest: () -> Unit = {}, // todo - isExpanded = false
    onDropdownMenuItemClicked: (String) -> Unit = {}, // todo - selectedText = label
) {
    val icon = if (isExpanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Column(Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { onOutLinedTextFieldValueChanged() },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    onOutLinedTextFieldSize(coordinates)
                },
            label = { Text("Label") },
            trailingIcon = {
                Icon(
                    icon,
                    "contentDescription",
                    Modifier.clickable { onTrailingIconClicked() },
                )
            },
        )

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { onDropdownMenuDismissRequest() },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() }),
        ) {
            options.forEach { label ->
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

// For displaying preview in
// the Android Studio IDE emulator
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DropdownComponent(
        isExpanded = false,
        options = listOf("A", "B", "C"),
        selectedText = "A",
    )
}
