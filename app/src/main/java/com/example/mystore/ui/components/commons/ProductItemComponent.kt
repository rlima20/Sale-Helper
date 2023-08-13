package com.example.mystore.ui.components.commons

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mystore.R
import com.example.mystore.Type
import com.example.mystore.model.Product
import com.example.mystore.setTextColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductItemComponent(
    product: Product,
    productPainter: Painter,
    shouldItemBeVisible: Boolean,
    onProductClick: () -> Unit,
    onProductLongClick: () -> Unit,
    onProductDoubleClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = 12.dp,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .background(colorResource(id = R.color.color_800))
                    .width(140.dp)
                    .combinedClickable(
                        enabled = true,
                        onClick = { onProductClick() },
                        onLongClick = { onProductLongClick() },
                        onDoubleClick = { onProductDoubleClick() },
                    ),
            ) {
                Image(
                    modifier = Modifier
                        .width(140.dp)
                        .height(100.dp),
                    painter = productPainter,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                )
                Text(
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(
                        top = 8.dp,
                        start = 8.dp,
                        end = 8.dp,
                        bottom = 0.dp,
                    ),
                    maxLines = 1,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.color_50),
                    text = product.title,
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 18.sp,
                    fontWeight = MaterialTheme.typography.h5.fontWeight,
                    color = colorResource(setTextColor(product.quantity.toDouble())),
                    text = setUnit(
                        Type.QUANTITY,
                        product.quantity.toString(),
                        shouldItemBeVisible,
                    ),
                )
            }
        }
    }
}
