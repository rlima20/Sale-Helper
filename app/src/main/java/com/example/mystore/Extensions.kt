package com.example.mystore

fun List<Int>.toParse(): List<String> {
    return this.map { it.toString() }
}

fun Double.toCurrency(): String {
    return "R$ ${"%.2f".format(this)}"
}
