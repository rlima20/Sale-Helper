package com.example.mystore.features.homescreen.ui

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
import com.example.mystore.enums.Type
import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.setTextColor
import com.example.mystore.ui.components.commons.setUnit

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductItemComponent(
    product: Product,
    productPainter: Painter,
    shouldItemBeVisible: Boolean,
    onProductClick: (Product) -> Unit,
    onProductLongClick: (Product) -> Unit,
    onProductDoubleClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = 4.dp,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .background(colorResource(id = R.color.color_50))
                    .width(140.dp)
                    .combinedClickable(
                        enabled = true,
                        onClick = { onProductClick(product) },
                        onLongClick = { onProductLongClick(product) },
                        onDoubleClick = { onProductDoubleClick() },
                    ),
            ) {
                Image(
                    painter = productPainter,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
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
                    fontSize = 14.sp,
                    fontWeight = MaterialTheme.typography.h5.fontWeight,
                    color = colorResource(setTextColor(product.quantity.toDouble())),
                    text = setUnit(
                        product.quantity.toString(),
                        Type.QUANTITY_OOS,
                        shouldItemBeVisible,
                    ),
                )
            }
        }
    }
}
