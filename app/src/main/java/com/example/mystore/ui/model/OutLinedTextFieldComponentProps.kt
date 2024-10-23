package com.example.mystore.ui.model

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import com.example.mystore.R

data class OutLinedTextFieldComponentProps @OptIn(ExperimentalComposeUiApi::class) constructor(
    val appearance: TextFieldAppearance,
    val selectedText: String = "",
    val keyboardController: SoftwareKeyboardController? = null,
    val focusManager: FocusManager,
    val callbacks: TextFieldCallbacks = TextFieldCallbacks()
)

data class TextFieldAppearance(
    val modifier: Modifier = Modifier,
    val enabled: Boolean = true,
    val label: String = "",
    val transactionDetailColors: Triple<Int, Int, Int> =
        Triple(R.color.color_900, R.color.white, R.color.white),
    val keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
)

data class TextFieldCallbacks(
    val onValueChanged: (String) -> Unit = {},
    val trailingIcon: @Composable () -> Unit = {},
    val onDone: () -> Unit = {},
    val onOutLinedTextFieldSize: (size: Size) -> Unit = {},
    val onOutLinedTextFieldValueChanged: (String) -> Unit = {},
    val onTrailingIconClicked: () -> Unit = {}
)