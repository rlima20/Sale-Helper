package com.example.mystore.ui.components.commons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.mystore.R

@Composable
fun AlertDialogComponent(
    size: Size? = null,
    color: Color = colorResource(id = R.color.color_50),
    title: String = "",
    content: @Composable () -> Unit = {},
    confirmButton: @Composable () -> Unit = {},
    dismissButton: @Composable () -> Unit = {},
    onDismissRequest: () -> Unit = {},
) {
    Box {
        AlertDialog(
            modifier = if (size == null) {
                Modifier
            } else {
                Modifier.size(
                    width = size.width.dp,
                    height = size.height.dp,
                )
            },
            shape = MaterialTheme.shapes.large,
            properties = DialogProperties(
                decorFitsSystemWindows = false,
                usePlatformDefaultWidth = false,
            ),
            backgroundColor = color,
            title = {
                Text(
                    text = title,
                    color = colorResource(id = R.color.color_700),
                )
            },
            text = { content() },
            confirmButton = { confirmButton() },
            dismissButton = { dismissButton() },
            onDismissRequest = { onDismissRequest() },
        )
    }
}
