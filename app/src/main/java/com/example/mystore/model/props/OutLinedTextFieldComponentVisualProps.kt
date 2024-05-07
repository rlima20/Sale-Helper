package com.example.mystore.model.props

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import com.example.mystore.R

@OptIn(ExperimentalComposeUiApi::class)
class OutLinedTextFieldComponentVisualProps(
    val modifier: Modifier = Modifier,
    val enabled: Boolean = true,
    val selectedText: String = "",
    val label: String = "",
    val transactionDetailColors: Triple<Int, Int, Int> = Triple(
        R.color.color_900,
        R.color.white,
        R.color.white
    ),
    val keyboardController: SoftwareKeyboardController? = null,
    val keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    val focusManager: FocusManager,
)
