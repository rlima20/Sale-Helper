package com.example.mystore.ui.components.commons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mystore.States
import com.example.mystore.model.screen.Product

@Composable
fun ProductCarouselComponent(
    listOfProducts: List<Product>,
    shouldItemBeVisible: Boolean,
    onImageRequestState: (state: States) -> Unit,
    onProductClick: (product: Product) -> Unit,
    onProductLongClick: (product: Product) -> Unit,
    onProductDoubleClick: () -> Unit,
) {
    LazyRow(
        modifier = Modifier.padding(
            start = 8.dp,
            end = 8.dp,
            bottom = 8.dp,
        ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(listOfProducts) { product ->
            ProductItemComponent(
                product = product,
                productPainter = getPainter(
                    imageUrl = product.imageUrl,
                    onImageRequestState = {
                        onImageRequestState(it)
                    },
                ),
                shouldItemBeVisible = shouldItemBeVisible,
                onProductClick = { onProductClick(product) },
                onProductLongClick = { onProductLongClick(product) },
                onProductDoubleClick = { onProductDoubleClick() },
            )
        }
    }
}
