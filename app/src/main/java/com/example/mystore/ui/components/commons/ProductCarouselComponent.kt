package com.example.mystore.ui.components.commons

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import coil.size.Size
import com.example.mystore.States
import com.example.mystore.getAsyncImagePainter
import com.example.mystore.listOfProductsLocal
import com.example.mystore.model.Product

@Composable
fun ProductCarouselComponent(
    shouldItemBeVisible: Boolean,
    onImageRequestState: (state: States) -> Unit,
    onClick: (product: Product) -> Unit,
    onLongClick: () -> Unit,
    onDoubleClick: () -> Unit,
) {
    LazyRow(
        modifier = Modifier.padding(
            start = 8.dp,
            end = 8.dp,
            bottom = 8.dp,
        ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(listOfProductsLocal) { product ->
            ProductItemComponent(
                product = product,
                painter = getPainter(
                    imageUrl = product.imageUrl,
                    onImageRequestState = {
                        onImageRequestState(it)
                    },
                ),
                shouldItemBeVisible = shouldItemBeVisible,
                onClick = { onClick(product) },
                onLongClick = { onLongClick() },
                onDoubleClick = { onDoubleClick() },
            )
        }
    }
}

@Composable
private fun getPainter(
    imageUrl: String,
    onImageRequestState: (state: States) -> Unit,
): Painter {
    val painter = getAsyncImage(
        context = LocalContext.current,
        imageUrl = imageUrl,
    ).getAsyncImagePainter(
        onStateChanged = {
            onImageRequestState(it)
        },
    )
    return painter
}

private fun getAsyncImage(
    context: Context,
    imageUrl: String,
): ImageRequest {
    return ImageRequest.Builder(context)
        .data(imageUrl)
        .size(Size.ORIGINAL)
        .crossfade(true)
        .build()
}
