package com.example.mystore

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
