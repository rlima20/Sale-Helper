package com.example.mystore.ui.components.commons

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
    title: String,
    textColor: Int = R.color.color_900,
    backgroundColor: Int = R.color.white,
    body: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier.padding(top = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = 4.dp,
        ) {
            Box(
                modifier = Modifier
                    .background(colorResource(backgroundColor)),
            ) {
                Column(
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp, end = 8.dp),
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
                        color = colorResource(textColor),
                        text = title,
                    )
                    body()
                }
            }
        }
    }
}
