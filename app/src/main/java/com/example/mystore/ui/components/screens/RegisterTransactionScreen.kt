package com.example.mystore.ui.components.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.stringResource
import com.example.mystore.R
import com.example.mystore.Screens
import com.example.mystore.ui.components.commons.DropdownComponent
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.components.commons.SectionInfo
import com.example.mystore.ui.components.commons.ValidateSection

@Composable
fun RegisterTransactionScreen() {
    Column {
        ValidateSection(
            sectionInfo = SectionInfo(
                section = {
                    ScreenSectionComponent(
                        title = stringResource(id = R.string.my_store_register_transaction),
                        body = {
                            Body()
                        },
                    )
                },
            ),
            screen = Screens.REGISTER_TRANSACTION,
        )
    }
}

@Composable
fun Body() {
    var selectedText by remember { mutableStateOf("") }
    var isExpanded by remember { mutableStateOf(false) }
    val listOfProducts = listOf("Produto1", "Produto2", "Produto3")
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    DropdownComponent(
        isExpanded = isExpanded,
        options = listOfProducts,
        selectedText = selectedText,
        textFieldSize = textFieldSize,
        onOutLinedTextFieldSize = { textFieldSize = it },
        onOutLinedTextFieldValueChanged = { selectedText = it },
        onTrailingIconClicked = { isExpanded = !isExpanded },
        onDropdownMenuDismissRequest = { isExpanded = false },
        onDropdownMenuItemClicked = { selectedText = it },
    )
}
