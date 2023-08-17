package com.example.mystore.ui.components.commons

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.example.mystore.R

@Composable
fun FloatingActionButton(
    modifier: Modifier = Modifier,
    clickable: Boolean = false,
    onClick: () -> Unit = {},
) {
    Icon(
        painterResource(id = R.drawable.empty_state),
        null,
        modifier.clickable(
            enabled = clickable,
            onClick = onClick,
        ),
        tint = colorResource(id = R.color.color_50),
    )
}
