package com.example.mystore.ui.components.commons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.mystore.R

@Composable
fun AlertDialogComponent(
    size: Size = Size(0.0f, 0.0f),
    title: String = "",
    content: @Composable () -> Unit = {},
    onDismissRequest: () -> Unit = {},
    onConfirmButtonClicked: () -> Unit = {},
    onCancelButtonClicked: () -> Unit = {},
) {
    Box() {
        AlertDialog(
            modifier = Modifier.size(
                width = size.width.dp,
                height = size.height.dp,
            ),
            shape = MaterialTheme.shapes.large,
            properties = DialogProperties(
                decorFitsSystemWindows = false,
                usePlatformDefaultWidth = false,
            ),
            backgroundColor = colorResource(id = R.color.color_50),
            title = {
                Text(
                    text = title,
                    color = colorResource(id = R.color.color_700),
                )
            },
            text = {
                content()
            },
            confirmButton = {
                Button(onClick = { onCancelButtonClicked() }) {
                    Text(
                        text = stringResource(R.string.my_store_cancel),
                        color = colorResource(id = R.color.color_50),
                    )
                }
            },
            dismissButton = {
                Button(onClick = { onConfirmButtonClicked() }) {
                    Text(
                        text = stringResource(R.string.my_store_ok),
                        color = colorResource(id = R.color.color_50),
                    )
                }
            },
            onDismissRequest = { onDismissRequest() },
        )
    }
}
