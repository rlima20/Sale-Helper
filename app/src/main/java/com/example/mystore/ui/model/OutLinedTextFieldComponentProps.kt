package com.example.mystore.ui.model

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import com.example.mystore.R

data class OutLinedTextFieldComponentProps
@OptIn(ExperimentalComposeUiApi::class) constructor(
    val modifier: Modifier = Modifier,
    val enabled: Boolean = true,
    val selectedText: String = "",
    val label: String = "",
    val transactionDetailColors: Triple<Int, Int, Int> =
        Triple(R.color.color_900, R.color.white, R.color.white),
    val keyboardController: SoftwareKeyboardController? = null,
    val keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    val focusManager: FocusManager,
    val onValueChanged: (String) -> Unit = {},
    val trailingIcon: @Composable () -> Unit = {},
    val onDone: () -> Unit = {},
)