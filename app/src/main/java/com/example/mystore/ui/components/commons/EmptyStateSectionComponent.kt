package com.example.mystore.ui.components.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mystore.R

@Composable
fun EmptyStateSectionComponent(
    title: String,
    painter: Painter,
    onEmptyStateImageClicked: () -> Unit = {},
) {
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = 12.dp,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(colorResource(id = R.color.white)),
        ) {
            Surface(
                modifier = Modifier
                    .background(color = colorResource(id = R.color.white)),
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(15.dp),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(200.dp),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .size(100.dp)
                                .background(color = colorResource(id = R.color.white))
                                .clickable(onClick = onEmptyStateImageClicked),
                            painter = painterResource(id = R.drawable.plus_circled_icon),
                            contentDescription = null,
                        )
                        Text(
                            modifier = Modifier
                                .padding(
                                    start = 16.dp,
                                    top = 8.dp,
                                    bottom = 8.dp,
                                    end = 8.dp,
                                ),
                            fontWeight = FontWeight.Light,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            color = colorResource(id = R.color.color_300),
                            text = title,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun EmptyStateComponentPreview() {
    EmptyStateSectionComponent(
        title = stringResource(R.string.my_store_no_transactions_done),
        painter = painterResource(id = R.drawable.my_store_plus_icon),
    )
}
