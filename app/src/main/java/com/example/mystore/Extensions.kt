package com.example.mystore

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.mystore.commons.AppApplication
import com.example.mystore.enums.States
import com.example.mystore.enums.TransactionType
import com.example.mystore.ui.navigation.ConsolidatedPositionScreen
import com.example.mystore.ui.navigation.HomeScreen
import com.example.mystore.ui.navigation.MyStoreDestinationInterface
import com.example.mystore.ui.navigation.RegisterProductScreen
import com.example.mystore.ui.navigation.RegisterTransactionScreen
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

fun Int.toUnityOutOfStock(isVisible: Boolean): String =
    if (this == 0) {
        application.getString(R.string.my_store_out_of_stock)
    } else {
        if (isVisible) {
            "$this un"
        } else {
            "$this un".replace(Regex("[0-9]"), "-")
        }
    }

fun String.toTransactionString(): String {
    return when (this) {
        TransactionType.SALE.name -> "VENDA"
        TransactionType.PURCHASE.name -> "COMPRA"
        else -> "VENDA"
    }
}

fun setTextColor(value: Double): Int =
    if (value > 0) R.color.color_green_A900 else R.color.color_red_A1000

@Composable
fun ImageRequest.getAsyncImagePainter(
    onStateChanged: (state: States) -> Unit = {},
): Painter {
    when (rememberAsyncImagePainter(this).state) {
        is AsyncImagePainter.State.Success -> {
            onStateChanged(States.SUCCESS)
        }
        is AsyncImagePainter.State.Loading -> {
            onStateChanged(States.LOADING)
        }
        else -> {
            onStateChanged(States.ERROR)
        }
    }

    return if (rememberAsyncImagePainter(this).state is AsyncImagePainter.State.Success) {
        rememberAsyncImagePainter(this)
    } else {
        painterResource(id = R.drawable.placeholder)
    }
}

// Limits the number of characters in a string
fun String.limitTo(maxLength: Int): String {
    return if (this.length > maxLength) {
        this.substring(0, maxLength - 3) + "..."
    } else {
        this
    }
}

fun String.toTransactionType(): TransactionType = when (this) {
    TransactionType.PURCHASE.name -> TransactionType.PURCHASE
    TransactionType.SALE.name -> TransactionType.SALE
    else -> TransactionType.PURCHASE
}

fun Date.toShortDateString(): String {
    val formatter = SimpleDateFormat("EEE MMM dd", Locale.getDefault())
    return formatter.format(this)
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
            saveState = true
        }

        launchSingleTop = true
        restoreState = true
    }

@Composable
fun TransactionType.colorTransactionType() =
    if (this == TransactionType.SALE) {
        colorResource(id = R.color.color_green_A900)
    } else {
        colorResource(id = R.color.color_red_A1000)
    }

fun Int.setItemSize() = ((this - 16) / 2).dp

fun Int.setQuantifierSize() = ((this - 74) / 2).dp

fun String.transformStringToInterfaceObject(
    application: AppApplication,
): MyStoreDestinationInterface {
    return when (this) {
        application.getString(R.string.my_store_home) -> HomeScreen
        application.getString(R.string.my_store_register_product) -> RegisterProductScreen
        application.getString(R.string.my_store_register_transaction) -> RegisterTransactionScreen
        application.getString(R.string.my_store_consolidated_position) -> ConsolidatedPositionScreen
        else -> HomeScreen
    }
}

