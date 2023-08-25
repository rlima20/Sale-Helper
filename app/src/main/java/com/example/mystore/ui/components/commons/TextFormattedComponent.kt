package com.example.mystore.ui.components.commons

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.mystore.R

@Composable
fun TextFormattedComponent(
    leftSideText: String,
    fontSize: TextUnit,
) {
    Text(
        modifier = Modifier.padding(
            start = 8.dp,
            end = 8.dp,
        ),
        fontSize = fontSize,
        color = colorResource(id = R.color.color_50),
        text = leftSideText,
    )
}
