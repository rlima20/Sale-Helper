package com.example.mystore.ui.components.commons

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ToastComponent(
    message: String,
) {
    Toast.makeText(
        LocalContext.current,
        message,
        Toast.LENGTH_SHORT,
    ).show()
}
