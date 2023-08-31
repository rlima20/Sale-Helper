package com.example.mystore.ui.components.commons

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mystore.R

@Composable
internal fun Quantifier(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    maxQuantity: Int = 9,
    quantity: Int,
    onQuantifierChange: (Int) -> Unit,
) {
    var isLeftIconEnabled = false
    var isRightIconEnabled = false
    if (enabled) {
        isLeftIconEnabled = quantity > 1
        isRightIconEnabled = quantity < maxQuantity
    }

    Surface(
        modifier = modifier.height(40.dp),
        elevation = 4.dp,
        shape = setRoundedCornersShape(),
        color = colorResource(id = R.color.color_50),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(
                enabled = isLeftIconEnabled,
                onClick = { onQuantifierChange(quantity - 1) },
            ) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = SetIconButtonColor(isLeftIconEnabled),
                )
            }

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = quantity.toString(),
                fontSize = 18.dp.value.sp,
                color = colorResource(id = R.color.color_400),
            )

            IconButton(
                enabled = isRightIconEnabled,
                onClick = { onQuantifierChange(quantity + 1) },
            ) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = SetIconButtonColor(isRightIconEnabled),
                )
            }
        }
    }
}

private fun setRoundedCornersShape(): RoundedCornerShape =
    RoundedCornerShape(
        topStart = 25.dp,
        topEnd = 25.dp,
        bottomStart = 25.dp,
        bottomEnd = 25.dp,
    )

@SuppressLint("ComposableNaming")
@Composable
private fun SetIconButtonColor(condition: Boolean): Color =
    if (condition) {
        colorResource(id = R.color.color_400)
    } else {
        colorResource(id = R.color.color_100)
    }

@Preview
@Composable
private fun QuantifierPreview() {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Quantifier(
            Modifier,
            true,
            9,
            1,
        ) {}
    }
}
