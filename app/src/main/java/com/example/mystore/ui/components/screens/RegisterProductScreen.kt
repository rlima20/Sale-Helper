package com.example.mystore.ui.components.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import com.example.mystore.R
import com.example.mystore.ui.components.commons.OutLinedTextFieldComponent
import com.example.mystore.ui.components.commons.ScreenSectionComponent

@Composable
fun RegisterProductScreen(
    isEditMode: Boolean = false,
) {
    ScreenSectionComponent(
        title = stringResource(id = R.string.my_store_product_2).setTitle(isEditMode),
        body = { RegisterProductScreenBody() },
    )
}

// Dropdown TransactionType
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterProductScreenBody() {
    var titleSelectedText by remember { mutableStateOf("") }
    val titleLabel = stringResource(id = R.string.my_store_product_title)
    val transactionDetailColors: Triple<Int, Int, Int> =
        Triple(R.color.color_50, R.color.color_900, R.color.color_50)
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    // Title
    OutLinedTextFieldComponent(
        selectedText = titleSelectedText,
        label = titleLabel,
        transactionDetailColors = transactionDetailColors,
        keyboardController = keyboardController,
        focusManager = focusManager,
        onValueChanged = { titleSelectedText = it },
    )

    // Description
    OutLinedTextFieldComponent(
        selectedText = titleSelectedText,
        label = titleLabel,
        transactionDetailColors = transactionDetailColors,
        keyboardController = keyboardController,
        focusManager = focusManager,
        onValueChanged = { titleSelectedText = it },
    )

    // Quantity

    // Max Quantity To Buy

    // Purchase Price

    // Sale Price

    // Image Url
}

fun String.setTitle(editMode: Boolean) = if (editMode) "$this | EDIÇÃO" else "$this | CRIAÇÃO"
