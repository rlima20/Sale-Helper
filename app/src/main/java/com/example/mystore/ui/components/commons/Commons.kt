package com.example.mystore.ui.components.commons

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import coil.request.ImageRequest
import com.example.mystore.R
import com.example.mystore.Screens
import com.example.mystore.Section
import com.example.mystore.States
import com.example.mystore.Type
import com.example.mystore.getAsyncImagePainter
import com.example.mystore.model.SectionEmptyStateInfo
import com.example.mystore.model.SectionInfo
import com.example.mystore.model.props.ColorProps
import com.example.mystore.model.props.ShowAlertDialogComponentCallbackProps
import com.example.mystore.toCurrency
import com.example.mystore.toUnity
import com.example.mystore.toUnityOutOfStock
import com.example.mystore.ui.navigation.RegisterProductScreen
import com.example.mystore.ui.navigation.RegisterTransactionScreen
import coil.size.Size as SizeCoil


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
    val isSectionEmpty = sectionEmptyStateInfo.data.isEmpty()
    val isRegisterScreen =
        screen == Screens.REGISTER_PRODUCT || screen == Screens.REGISTER_TRANSACTION
    val isTransactionSection = sectionInfo.sectionName == Section.TRANSACTIONS

    if (isSectionEmpty || (!isTransactionSection && isRegisterScreen)) {
        sectionInfo.section()
    } else {
        EmptyStateSectionComponent(
            title = sectionEmptyStateInfo.emptySectionTitle,
            painter = sectionEmptyStateInfo.emptySectionPainter,
            onEmptyStateImageClicked = { sectionEmptyStateInfo.onEmptyStateImageClicked() },
        )
    }
}

@Composable
fun getPainter(imageUrl: String, onImageRequestState: (state: States) -> Unit): Painter =
    getAsyncImage(context = LocalContext.current, imageUrl = imageUrl)
        .getAsyncImagePainter(onStateChanged = { onImageRequestState(it) })

@Composable
fun getAsyncImage(context: Context, imageUrl: String): ImageRequest =
    ImageRequest.Builder(context)
        .data(imageUrl)
        .size(SizeCoil.ORIGINAL)
        .crossfade(true)
        .build()

// Todo - Criar classes Props pra esse componente e deixá-lo separado num arquivo .kt
// Todo - Existem 2 AlertDialogComponent - Unificar os dois componentes
@Composable
fun AlertDialogComponent(
    alertDialogVisibility: Boolean,
    alertDialogTitle: String,
    alertDialogMessage: String,
    callback: ShowAlertDialogComponentCallbackProps
) {
    if (alertDialogVisibility) {
        AlertDialogComponent(
            title = alertDialogTitle,
            content = {
                Text(
                    text = alertDialogMessage,
                    color = colorResource(id = R.color.color_700),
                )
            },
            onDismissRequest = { callback.onDismissRequest() },
            confirmButton = {
                Button(onClick = { callback.onConfirmButtonClicked() }) {
                    Text(
                        text = stringResource(R.string.my_store_ok),
                        color = colorResource(id = R.color.color_50),
                    )
                }
            },
            dismissButton = {
                Button(onClick = { callback.onDismissButtonClicked() }) {
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

@Composable
fun UnifiedAlertDialog(
    alertDialogVisibility: Boolean,
    alertDialogTitle: String = "",
    content: @Composable () -> Unit = {},
    alertDialogSize: Size? = null,
    alertDialogColor: Color = colorResource(id = ColorProps().focusedBorderColor),
    alertDialogCallback: ShowAlertDialogComponentCallbackProps
) {
    if (alertDialogVisibility) {
        Box {
            AlertDialog(
                modifier = if (alertDialogSize == null) {
                    Modifier
                } else {
                    Modifier.size(
                        width = alertDialogSize.width.dp,
                        height = alertDialogSize.height.dp,
                    )
                },
                shape = MaterialTheme.shapes.large,
                properties = DialogProperties(
                    decorFitsSystemWindows = false,
                    usePlatformDefaultWidth = false,
                ),
                backgroundColor = alertDialogColor,
                title = {
                    Text(
                        text = alertDialogTitle,
                        color = colorResource(id = R.color.color_700),
                    )
                },
                text = { content() },
                confirmButton = {
                    Button(onClick = { alertDialogCallback.onConfirmButtonClicked() }) {
                        Text(
                            text = stringResource(R.string.my_store_ok),
                            color = colorResource(id = R.color.color_50),
                        )
                    }
                },
                dismissButton = {
                    Button(onClick = { alertDialogCallback.onDismissButtonClicked() }) {
                        Text(
                            text = stringResource(R.string.my_store_cancel),
                            color = colorResource(id = R.color.color_50),
                        )
                    }
                },
                onDismissRequest = { alertDialogCallback.onDismissRequest() },
            )
        }
    }
}