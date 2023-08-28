package com.example.mystore.ui.components.commons

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.mystore.R

@Composable
fun DividerComponent() {
    Divider(
        color = colorResource(id = R.color.color_300),
        thickness = 1.dp,
        modifier = Modifier.padding(8.dp),
    )
}
