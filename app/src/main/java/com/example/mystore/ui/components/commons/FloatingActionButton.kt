package com.example.mystore.ui.components.commons

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mystore.R

@Composable
fun FloatingActionButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
    colorId: Int = R.color.color_50,
    onClick: () -> Unit = {},
) {
    Icon(
        painterResource(id = R.drawable.my_store_save_icon),
        null,
        modifier.clickable(
            enabled = enabled,
            onClick = onClick,
        ),
        tint = colorResource(id = colorId),
    )
}

@Preview
@Composable
fun FloatingActionButtonPreview() {
    FloatingActionButton()
}
