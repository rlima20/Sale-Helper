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
import com.example.mystore.model.props.DropdownComponentCallbackProps
import com.example.mystore.model.props.DropdownComponentVisualProps
import com.example.mystore.model.props.OutLinedTextFieldComponentCallbackProps
import com.example.mystore.model.props.OutLinedTextFieldComponentVisualProps


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DropdownComponent(
    visualProps: DropdownComponentVisualProps,
    callBack: DropdownComponentCallbackProps
) {
    Column {
        OutLinedTextFieldComponent(
            OutLinedTextFieldComponentVisualProps(
                enabled = false,
                modifier = visualProps.modifier
                    .background(colorResource(id = visualProps.transactionDetailColors.second))
                    .onGloballyPositioned { coordinates ->
                        callBack.onOutLinedTextFieldSize(coordinates.size.toSize())
                    },
                selectedText = visualProps.selectedText,
                label = visualProps.label,
                transactionDetailColors = visualProps.transactionDetailColors,
                keyboardController = LocalSoftwareKeyboardController.current,
                focusManager = LocalFocusManager.current,
            ),
            OutLinedTextFieldComponentCallbackProps(
                onValueChanged = { callBack.onOutLinedTextFieldValueChanged(visualProps.selectedText) },
                trailingIcon = {
                    Icon(
                        setIcon(visualProps.isExpanded),
                        "contentDescription",
                        Modifier.clickable { callBack.onTrailingIconClicked() },
                        tint = colorResource(id = R.color.color_800),
                    )
                },
            ),
        )

        DropdownMenu(
            expanded = visualProps.isExpanded,
            onDismissRequest = { callBack.onDropdownMenuDismissRequest() },
            modifier = Modifier
                .width(with(LocalDensity.current) { visualProps.textFieldSize.width.toDp() }),
        ) {
            visualProps.items.forEach { label ->
                DropdownMenuItem(onClick = {
                    callBack.onDropdownMenuItemClicked(label)
                    callBack.onDropdownMenuDismissRequest()
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
        visualProps = DropdownComponentVisualProps(
            isExpanded = false,
            modifier = Modifier.padding(16.dp),
            label = "Tipo de transação",
            items = listOf("A", "B", "C"),
            textFieldSize = Size.Zero,
            selectedText = "A",
        ),
        callBack = DropdownComponentCallbackProps(),
    )
}
