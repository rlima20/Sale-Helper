package com.example.mystore

fun List<Int>.toParse(): List<String> {
    return this.map { it.toString() }
}
