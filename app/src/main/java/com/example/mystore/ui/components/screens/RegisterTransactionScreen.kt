package com.example.mystore.ui.components.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mystore.R
import com.example.mystore.Screens
import com.example.mystore.ui.components.commons.DropdownComponent
import com.example.mystore.ui.components.commons.Quantifier
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.components.commons.SectionInfo
import com.example.mystore.ui.components.commons.ValidateSection
import com.example.mystore.viewmodel.screen.RegisterTransactionViewModel

@Composable
fun RegisterTransactionScreen(
    registerTransactionViewModel: RegisterTransactionViewModel,
) {
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
    Column {
        val screenWidth = LocalConfiguration.current.screenWidthDp

        var selectedTextTransaction by remember { mutableStateOf("") }
        var isExpandedTransaction by remember { mutableStateOf(false) }
        val listOfProductsTransaction = listOf("VENDA", "COMPRA")
        var textFieldSizeTransaction by remember { mutableStateOf(Size.Zero) }

        var selectedText by remember { mutableStateOf("") }
        var isExpanded by remember { mutableStateOf(false) }
        val listOfProducts = listOf("Produto1", "Produto2", "Produto3")
        var textFieldSize by remember { mutableStateOf(Size.Zero) }

        Row(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
        ) {
            DropdownComponent(
                isExpanded = isExpandedTransaction,
                options = listOfProductsTransaction,
                selectedText = selectedTextTransaction,
                textFieldSize = textFieldSizeTransaction,
                label = "Tipo de transação",
                modifier = Modifier.fillMaxWidth(),
                onOutLinedTextFieldSize = { textFieldSizeTransaction = it },
                onOutLinedTextFieldValueChanged = { selectedTextTransaction = it },
                onTrailingIconClicked = { isExpandedTransaction = !isExpandedTransaction },
                onDropdownMenuDismissRequest = { isExpandedTransaction = false },
                onDropdownMenuItemClicked = { selectedTextTransaction = it },
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
        ) {
            DropdownComponent(
                isExpanded = isExpanded,
                options = listOfProducts,
                selectedText = selectedText,
                textFieldSize = textFieldSize,
                label = "Produto",
                modifier = Modifier.width(setItemSize(screenWidth)),
                onOutLinedTextFieldSize = { textFieldSize = it },
                onOutLinedTextFieldValueChanged = { selectedText = it },
                onTrailingIconClicked = { isExpanded = !isExpanded },
                onDropdownMenuDismissRequest = { isExpanded = false },
                onDropdownMenuItemClicked = { selectedText = it },
            )

            Quantifier(
                width = setItemSize(screenWidth),
                quantifier = 1,
                onQuantifierChange = {},
            )
        }
    }
}

private fun setItemSize(screenWidth: Int): Dp = ((screenWidth - 16) / 2).dp
