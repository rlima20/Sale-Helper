package com.example.mystore.ui.components.commons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mystore.R

@Composable
fun FloatingActionButton(
    modifier: Modifier = Modifier.size(32.dp),
    onClick: () -> Unit = {},
) {
    Icon(
        painterResource(id = R.drawable.empty_state),
        null,
        Modifier.clickable { },
        tint = colorResource(id = R.color.color_50),
    )
}
