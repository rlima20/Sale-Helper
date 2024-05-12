package com.example.mystore.ui.components.commons

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.mystore.model.props.ColorProps

@Composable
fun TextFormattedComponent(
    leftContent: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit,
    fontColor: Int = ColorProps().rowComponentRightContentColor,
) {
    Text(
        modifier = modifier.padding(
            start = 8.dp,
            end = 8.dp,
        ),
        fontSize = fontSize,
        color = colorResource(id = fontColor),
        text = leftContent,
    )
}
