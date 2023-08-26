package com.example.mystore.ui.components.commons

import androidx.compose.foundation.layout.Box
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.mystore.R

@Composable
fun AlertDialogComponent(
    title: String,
    text: String,
    onDismissRequest: () -> Unit = {},
    onConfirmButtonClicked: () -> Unit = {},
    onCancelButtonClicked: () -> Unit = {},
) {
    Box {
        AlertDialog(
            title = { Text(title) },
            text = { Text(text) },
            confirmButton = {
                Button(onClick = { onCancelButtonClicked() }) {
                    Text(text = stringResource(R.string.my_store_cancel))
                }
            },
            dismissButton = {
                Button(onClick = { onConfirmButtonClicked() }) {
                    Text(text = stringResource(R.string.my_store_ok))
                }
            },
            onDismissRequest = { onDismissRequest() },
        )
    }
}
