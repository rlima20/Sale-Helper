package com.example.mystore.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mystore.R

@Composable
fun ScreenSectionComponent(
    body: @Composable () -> Unit,
    title: String,
) {
    Column(
        modifier = Modifier.padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = 12.dp,
        ) {
            Box(
                modifier = Modifier
                    .background(colorResource(id = R.color.color_900)),
            ) {
                Column(
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text(
                        modifier = Modifier
                            .padding(
                                start = 16.dp,
                                top = 8.dp,
                                bottom = 8.dp,
                                end = 8.dp,
                            ),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.color_50),
                        text = title,
                    )
                    body()
                }
            }
        }
    }
}
