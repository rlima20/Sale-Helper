package com.example.mystore.ui.components.commons

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mystore.R
import com.example.mystore.Type

@Composable
fun TextFormattedComponent(
    stringId: Int,
    type: Type,
    modifier: Modifier,
) {
    Text(
        modifier = SetPadding(modifier, type),
        fontSize = 18.sp,
        color = colorResource(id = R.color.color_50),
        text = stringResource(stringId),
    )
}

// todo - dá pra criar uma extension function para isso
@Composable
private fun SetPadding(modifier: Modifier, type: Type): Modifier {
    if (type == Type.CURRENCY) {
        modifier.padding(
            start = 16.dp,
            top = 8.dp,
            bottom = 8.dp,
            end = 8.dp,
        )
    }
    return modifier.padding(
        start = 0.dp,
        top = 0.dp,
        bottom = 0.dp,
        end = 0.dp,
    )
}
