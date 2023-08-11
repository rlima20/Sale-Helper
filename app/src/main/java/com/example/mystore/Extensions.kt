package com.example.mystore

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val application = AppApplication.instance

fun Int.toStringResource(): String = application.getString(this)

fun Double.toCurrency(isVisible: Boolean): String {
    return if (isVisible) {
        "R$ ${"%.2f".format(this)}"
    } else {
        "R$ ${"%.2f".format(this).replace(Regex("[0-9]"), "-")}"
    }
}

fun Int.toUnity(isVisible: Boolean): String =
    if (isVisible) {
        "$this un"
    } else {
        "$this un".replace(Regex("[0-9]"), "-")
    }

fun setTextColor(value: Double): Int =
    if (value > 0) R.color.color_green_A900 else R.color.color_red_A1000

@Composable
fun ImageRequest.getAsyncImagePainter(
    onStateChanged: (state: States) -> Unit = {},
): Painter {
    if (rememberAsyncImagePainter(this).state is AsyncImagePainter.State.Success) {
        onStateChanged(States.SUCCESS)
    } else if (rememberAsyncImagePainter(this).state is AsyncImagePainter.State.Loading) {
        onStateChanged(States.LOADING)
    } else {
        onStateChanged(States.ERROR)
    }

    return if (rememberAsyncImagePainter(this).state is AsyncImagePainter.State.Success) {
        rememberAsyncImagePainter(this)
    } else {
        painterResource(id = R.drawable.placeholder)
    }
}

// Limit the number of characters in a string
fun String.limitTo(maxLength: Int): String {
    return if (this.length > maxLength) {
        this.substring(0, maxLength - 3) + "..."
    } else {
        this
    }
}

fun Date.toShortString(): String {
    val formatter = SimpleDateFormat("EEE MMM dd", Locale.getDefault())
    return formatter.format(this)
}
