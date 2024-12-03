package com.example.mystore.ui.components.commons

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import coil.size.Size
import com.example.mystore.R
import com.example.mystore.enums.Screens
import com.example.mystore.enums.Section
import com.example.mystore.enums.States
import com.example.mystore.enums.Type
import com.example.mystore.getAsyncImagePainter
import com.example.mystore.toCurrency
import com.example.mystore.toUnity
import com.example.mystore.toUnityOutOfStock
import com.example.mystore.ui.navigation.RegisterProductScreen
import com.example.mystore.ui.navigation.RegisterTransactionScreen

data class SectionInfo(
    val section: @Composable () -> Unit,
    val sectionName: Section = Section.NONE,
)

data class SectionEmptyStateInfo(
    val data: List<Any?> = listOf(),
    val emptySectionTitle: String = "",
    val emptySectionPainter: Painter,
    val onEmptyStateImageClicked: () -> Unit = {},
)

@Composable
fun setUnit(text: String, type: Type, shouldItemBeVisible: Boolean) =
    when (type) {
        Type.QUANTITY -> text.toInt().toUnity(shouldItemBeVisible)
        Type.QUANTITY_OOS -> text.toInt().toUnityOutOfStock(shouldItemBeVisible)
        Type.QUANTITY_TRANSACTION_DETAIL -> text.toInt().toUnity(shouldItemBeVisible)
        Type.STRING_ONLY -> text
        Type.DATE -> text
        else -> text.toDouble().toCurrency(shouldItemBeVisible)
    }

fun toDoubleTransaction(text: String, type: Type, shouldItemBeVisible: Boolean) =
    when (type) {
        Type.QUANTITY -> text.toInt().toUnity(shouldItemBeVisible)
        Type.QUANTITY_OOS -> text.toInt().toUnityOutOfStock(shouldItemBeVisible)
        Type.QUANTITY_TRANSACTION_DETAIL -> text.toInt().toUnity(shouldItemBeVisible)
        Type.STRING_ONLY -> text
        Type.DATE -> text
        else -> text.toDouble().toCurrency(shouldItemBeVisible)
    }

fun validateSection(section: Section): String {
    return when (section) {
        Section.RESUME -> {
            RegisterTransactionScreen.route
        }

        Section.PRODUCTS -> {
            RegisterProductScreen.route
        }

        Section.TRANSACTIONS -> {
            RegisterTransactionScreen.route
        }

        Section.SALE -> {
            RegisterTransactionScreen.route
        }

        Section.PURCHASE -> {
            RegisterTransactionScreen.route
        }

        Section.REGISTER -> {
            RegisterProductScreen.route
        }

        else -> ""
    }
}

@Composable
fun ValidateSection(
    screen: Screens = Screens.NONE,
    sectionInfo: SectionInfo = SectionInfo({}, Section.NONE),
    sectionEmptyStateInfo: SectionEmptyStateInfo = SectionEmptyStateInfo(
        emptySectionPainter = painterResource(id = R.drawable.empty_state),
    ),
) {
    if (sectionEmptyStateInfo.data.isNotEmpty()) {
        sectionInfo.section()
    } else {
        if (sectionInfo.sectionName != Section.NONE) {
            if (screen == Screens.REGISTER_PRODUCT || screen == Screens.REGISTER_TRANSACTION) {
                sectionInfo.section()
            } else if (sectionEmptyStateInfo.data.isEmpty()) {
                EmptyStateSectionComponent(
                    title = sectionEmptyStateInfo.emptySectionTitle,
                    onEmptyStateImageClicked = { sectionEmptyStateInfo.onEmptyStateImageClicked() },
                )
            } else {
                sectionInfo.section()
            }
        }
    }
}

@Composable
fun getPainter(
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
fun TotalComponent(
    salesValue: Double,
    purchasesValue: Double,
    shouldItemBeVisible: Boolean,
) {
    Column(
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
    ) {
        RowComponent(
            leftSideText = stringResource(id = R.string.my_store_total_purchases),
            rightSide = {
                TextCurrencyComponent(
                    value = purchasesValue.toString(),
                    shouldItemBeVisible = shouldItemBeVisible,
                    type = Type.CURRENCY_ONLY,
                )
            },
        )
        RowComponent(
            leftSideText = stringResource(id = R.string.my_store_total_sales),
            rightSide = {
                TextCurrencyComponent(
                    value = salesValue.toString(),
                    shouldItemBeVisible = shouldItemBeVisible,
                    type = Type.CURRENCY_ONLY,
                )
            },
        )
        RowComponent(
            leftSideText = stringResource(id = R.string.my_store_total),
            rightSide = {
                TextCurrencyComponent(
                    value = (salesValue - purchasesValue).toString(),
                    shouldItemBeVisible = shouldItemBeVisible,
                    type = Type.CURRENCY_PURCHASE,
                )
            },
        )
    }
}

@Composable
fun ShowAlertDialogComponent(
    showAlert: Boolean,
    title: String,
    alertDialogMessage: String,
    onDismissButtonClicked: () -> Unit = {},
    onConfirmButtonClicked: () -> Unit = {},
    onDismissRequest: () -> Unit = {},
) {
    if (showAlert) {
        AlertDialogComponent(
            title = title,
            content = {
                Text(
                    text = alertDialogMessage,
                    color = colorResource(id = R.color.color_700),
                )
            },
            onDismissRequest = { onDismissRequest() },
            confirmButton = {
                Button(onClick = { onConfirmButtonClicked() }) {
                    Text(
                        text = stringResource(R.string.my_store_ok),
                        color = colorResource(id = R.color.color_50),
                    )
                }
            },
            dismissButton = {
                Button(onClick = { onDismissButtonClicked() }) {
                    Text(
                        text = stringResource(R.string.my_store_cancel),
                        color = colorResource(id = R.color.color_50),
                    )
                }
            },
            color = colorResource(id = R.color.white),
        )
    }
}
