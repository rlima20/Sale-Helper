package com.example.mystore.ui.components.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.request.ImageRequest
import coil.size.Size
import com.example.mystore.R
import com.example.mystore.States
import com.example.mystore.Type
import com.example.mystore.getAsyncImagePainter
import com.example.mystore.listOfProductsLocal
import com.example.mystore.model.Product
import com.example.mystore.setTextColor
import com.example.mystore.ui.components.commons.RowComponent
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.components.commons.setUnit
import com.example.mystore.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    shouldItemBeVisible: Boolean,
) {
    Column {
        ScreenSectionComponent(
            body = {
                HomeBody(
                    homeViewModel.getResume(),
                    shouldItemBeVisible,
                )
            },
            title = "Total geral",
        )

        ScreenSectionComponent(
            body = {
                ProductBody(
                    // homeViewModel,
                    shouldItemBeVisible,
                )
            },
            title = "Produtos",
        )
    }
}

@Composable
fun ProductBody(shouldItemBeVisible: Boolean) {
    var imageRequestState by remember { mutableStateOf(States.LOADING) }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(listOfProductsLocal) { product ->
            ProductItem(
                product = product,
                painter = getPainter(
                    imageUrl = product.imageUrl,
                    onImageRequestState = {
                        imageRequestState = it
                    },
                ),
                shouldItemBeVisible = shouldItemBeVisible,
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

@Composable
fun ProductItem(
    product: Product,
    painter: Painter,
    shouldItemBeVisible: Boolean,
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
                    .width(140.dp),
            ) {
                Image(
                    modifier = Modifier
                        .width(140.dp)
                        .height(100.dp),
                    painter = painter,
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
                        product.quantity.toDouble(),
                        shouldItemBeVisible,
                    ),
                )
            }
        }
    }
}

fun getAsyncImage(
    context: Context,
    imageUrl: String,
): ImageRequest {
    return ImageRequest.Builder(context)
        .data(imageUrl)
        .size(Size.ORIGINAL)
        .crossfade(true)
        .build()
}

@Composable
fun HomeBody(
    resume: HomeViewModel.Resume,
    shouldItemBeVisible: Boolean,
) {
    RowComponent(
        resume.debits,
        shouldItemBeVisible,
        R.string.my_store_debits,
    )

    RowComponent(
        resume.grossRevenue,
        shouldItemBeVisible,
        R.string.my_store_gross_revenue,
    )

    RowComponent(
        resume.netRevenue,
        shouldItemBeVisible,
        R.string.my_store_net_revenue,
    )

    RowComponent(
        resume.stockValue,
        shouldItemBeVisible,
        R.string.my_store_stock_value,
    )
}

@Preview
@Composable
fun ScreenPreview() {
    ScreenSectionComponent(
        body = { Text(text = "Posição consolidada") },
        title = "Posição consolidada",
    )
}

@Preview
@Composable
fun ProductSection() {
    ProductBody(shouldItemBeVisible = false)
}
