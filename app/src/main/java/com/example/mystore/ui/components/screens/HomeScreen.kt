package com.example.mystore.ui.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mystore.R
import com.example.mystore.toCurrency
import com.example.mystore.toUnity
import com.example.mystore.viewmodel.HomeViewModel
import com.example.mystore.viewmodel.Resume

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    shouldItemBeVisible: Boolean,
) {
    Column(
        modifier = Modifier.padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        HomeScreenSection(
            title = "Total geral",
            resume = homeViewModel.getResume(),
            shouldItemBeVisible = shouldItemBeVisible,
        )
    }
}

@Composable
fun HomeScreenSection(
    title: String,
    resume: Resume,
    shouldItemBeVisible: Boolean,
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
                    fontSize = 18.sp,
                    fontWeight = MaterialTheme.typography.h4.fontWeight,
                    color = colorResource(id = R.color.color_50),
                    text = title,
                )

                Row {
                    Text(
                        modifier = Modifier
                            .padding(
                                start = 16.dp,
                                top = 8.dp,
                                bottom = 8.dp,
                                end = 8.dp,
                            ),
                        fontSize = 18.sp,
                        fontWeight = MaterialTheme.typography.h5.fontWeight,
                        color = colorResource(id = R.color.color_50),
                        text = stringResource(id = R.string.my_store_debits),
                    )

                    Text(
                        modifier = Modifier
                            .padding(
                                start = 8.dp,
                                top = 8.dp,
                                bottom = 8.dp,
                                end = 8.dp,
                            ),
                        fontSize = 18.sp,
                        fontWeight = MaterialTheme.typography.h5.fontWeight,
                        color = colorResource(id = R.color.color_50),
                        text = resume.debits.toCurrency(shouldItemBeVisible),
                    )
                }

                Row {
                    Text(
                        modifier = Modifier
                            .padding(
                                start = 16.dp,
                                top = 8.dp,
                                bottom = 8.dp,
                                end = 8.dp,
                            ),
                        fontSize = 18.sp,
                        fontWeight = MaterialTheme.typography.h5.fontWeight,
                        color = colorResource(id = R.color.color_50),
                        text = stringResource(id = R.string.my_store_gross_revenue),
                    )
                    Text(
                        modifier = Modifier
                            .padding(
                                start = 8.dp,
                                top = 8.dp,
                                bottom = 8.dp,
                                end = 8.dp,
                            ),
                        fontSize = 18.sp,
                        fontWeight = MaterialTheme.typography.h5.fontWeight,
                        color = colorResource(id = R.color.color_50),
                        text = resume.grossRevenue.toCurrency(shouldItemBeVisible),
                    )
                }
                Row {
                    Text(
                        modifier = Modifier
                            .padding(
                                start = 16.dp,
                                top = 8.dp,
                                bottom = 8.dp,
                                end = 8.dp,
                            ),
                        fontSize = 18.sp,
                        fontWeight = MaterialTheme.typography.h5.fontWeight,
                        color = colorResource(id = R.color.color_50),
                        text = stringResource(id = R.string.my_store_net_revenue),
                    )
                    Text(
                        modifier = Modifier
                            .padding(
                                start = 8.dp,
                                top = 8.dp,
                                bottom = 8.dp,
                                end = 8.dp,
                            ),
                        fontSize = 18.sp,
                        fontWeight = MaterialTheme.typography.h5.fontWeight,
                        color = colorResource(id = R.color.color_50),
                        text = resume.netRevenue.toCurrency(shouldItemBeVisible),
                    )
                }
                Row {
                    Text(
                        modifier = Modifier
                            .padding(
                                start = 16.dp,
                                top = 8.dp,
                                bottom = 8.dp,
                                end = 8.dp,
                            ),
                        fontSize = 18.sp,
                        fontWeight = MaterialTheme.typography.h5.fontWeight,
                        color = colorResource(id = R.color.color_50),
                        text = stringResource(id = R.string.my_store_stock_value),
                    )
                    Text(
                        modifier = Modifier
                            .padding(
                                start = 8.dp,
                                top = 8.dp,
                                bottom = 8.dp,
                                end = 8.dp,
                            ),
                        fontSize = 18.sp,
                        fontWeight = MaterialTheme.typography.h5.fontWeight,
                        color = colorResource(id = R.color.color_50),
                        text = resume.inStock.toUnity(shouldItemBeVisible),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ScreenPreview() {
    HomeScreenSection("This is a title", Resume(), false)
}
