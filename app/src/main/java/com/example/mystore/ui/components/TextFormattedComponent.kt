package com.example.mystore.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mystore.R

@Composable
fun TextFormattedComponent(stringId: Int) {
    Text(
        modifier = Modifier
            .padding(
                start = 16.dp,
                top = 8.dp,
                bottom = 8.dp,
                end = 8.dp,
            ),
        fontSize = 18.sp,
        color = colorResource(id = R.color.color_50),
        text = stringResource(stringId),
    )
}
