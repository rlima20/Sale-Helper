package com.example.mystore.ui.components.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.mystore.R
import com.example.mystore.ui.model.DropdownAppearance
import com.example.mystore.ui.model.DropdownCallbacks
import com.example.mystore.ui.model.DropdownComponentProps
import com.example.mystore.ui.model.DropdownItem
import com.example.mystore.ui.model.DropdownState
import com.example.mystore.ui.model.OutLinedTextFieldComponentProps
import com.example.mystore.ui.model.TextFieldAppearance
import com.example.mystore.ui.model.TextFieldCallbacks

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DropdownComponent(
    dropdownComponentProps: DropdownComponentProps,
    outLinedTextFieldComponentProps: OutLinedTextFieldComponentProps
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val arrowPositionUp = dropdownComponentProps.state.isExpanded

    with(outLinedTextFieldComponentProps) {
        Column {
            OutLinedTextFieldComponent(
                outLinedTextFieldComponentProps = OutLinedTextFieldComponentProps(
                    appearance = TextFieldAppearance(
                        enabled = false,
                        modifier = appearance.modifier
                            .background(colorResource(id = appearance.transactionDetailColors.second))
                            .onGloballyPositioned { coordinates ->
                                callbacks.onOutLinedTextFieldSize(coordinates.size.toSize())
                            },
                        label = appearance.label,
                        transactionDetailColors = appearance.transactionDetailColors,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text) // Se necessário, ajuste aqui
                    ),
                    selectedText = selectedText,
                    keyboardController = keyboardController,
                    focusManager = focusManager,
                    callbacks = TextFieldCallbacks(
                        onValueChanged = { callbacks.onOutLinedTextFieldValueChanged(it) }, // Passar o valor atualizado
                        trailingIcon = {
                            Icon(
                                setIcon(arrowPositionUp),
                                "contentDescription",
                                Modifier.clickable { callbacks.onTrailingIconClicked() },
                                tint = colorResource(id = R.color.color_800),
                            )
                        },
                        onDone = { /* Adicione a lógica necessária se houver */ }
                    )
                )
            )
        }


        with(dropdownComponentProps) {
            DropdownMenu(
                expanded = state.isExpanded,
                onDismissRequest = { callbacks.onDropdownMenuDismissRequest() },
                modifier = Modifier
                    .width(with(LocalDensity.current) { appearance.textFieldSize.width.toDp() }),
            ) {
                items.items.forEach { label ->
                    DropdownMenuItem(onClick = {
                        callbacks.onDropdownMenuItemClicked(label)
                        callbacks.onDropdownMenuDismissRequest()
                    }) {
                        Text(text = label)
                    }
                }
            }
        }
    }
}

@Composable
private fun setIcon(isExpanded: Boolean) =
    if (isExpanded) Icons.Filled.KeyboardArrowUp
    else Icons.Filled.KeyboardArrowDown

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DropdownComponent(
        dropdownComponentProps = DropdownComponentProps(
            appearance = DropdownAppearance(
                modifier = Modifier.padding(16.dp),
                label = "Tipo de transação",
                textFieldSize = Size.Zero,
            ),
            state = DropdownState(
                isExpanded = false,
                selectedText = "A",
            ),
            items = DropdownItem(
                items = listOf("A", "B", "C"),
            ),
            callbacks = DropdownCallbacks(),
        ),
        outLinedTextFieldComponentProps = OutLinedTextFieldComponentProps(
            appearance = TextFieldAppearance(),
            focusManager = LocalFocusManager.current,
            callbacks = TextFieldCallbacks()
        )
    )
}
