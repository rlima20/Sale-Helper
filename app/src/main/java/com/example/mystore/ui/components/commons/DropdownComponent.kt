package com.example.mystore.ui.components.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.mystore.R

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
    val icon = if (isExpanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Column {
        OutlinedTextField(
            enabled = false,
            value = selectedText,
            onValueChange = { onOutLinedTextFieldValueChanged(selectedText) },
            modifier = modifier
                .background(colorResource(id = transactionDetailColors.second))
                .onGloballyPositioned { coordinates ->
                    onOutLinedTextFieldSize(coordinates.size.toSize())
                },
            label = {
                Text(
                    color = colorResource(id = transactionDetailColors.first),
                    text = label,
                )
            },
            trailingIcon = {
                Icon(
                    icon,
                    "contentDescription",
                    Modifier.clickable { onTrailingIconClicked() },
                    tint = colorResource(id = R.color.color_50),
                )
            },
            colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.color_50),
                unfocusedBorderColor = colorResource(id = R.color.color_100),
                disabledBorderColor = colorResource(id = R.color.color_200),
                focusedLabelColor = colorResource(id = R.color.color_50),
                unfocusedLabelColor = colorResource(id = R.color.color_500),
                disabledLabelColor = colorResource(id = R.color.color_50),
                cursorColor = colorResource(id = R.color.color_50),
                textColor = colorResource(id = R.color.color_50),
                disabledTextColor = colorResource(id = transactionDetailColors.third),
                placeholderColor = colorResource(id = R.color.color_50),
            ),
            shape = RoundedCornerShape(15.dp),
            maxLines = 1,
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
