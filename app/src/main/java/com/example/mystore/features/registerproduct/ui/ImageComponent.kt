package com.example.mystore.features.registerproduct.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import com.example.mystore.features.registerproduct.model.Product

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageComponent(
    modifier: Modifier = Modifier,
    product: Product = Product(),
    painterResource: Painter,
    onProductClick: () -> Unit = {},
    onProductLongClick: (Product) -> Unit = {},
    onProductDoubleClick: () -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onProductClick() },
    ) {
        Image(
            painter = painterResource,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
