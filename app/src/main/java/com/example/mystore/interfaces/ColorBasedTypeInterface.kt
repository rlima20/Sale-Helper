package com.example.mystore.interfaces

import androidx.compose.runtime.Composable
import com.example.mystore.Type

interface ColorBasedTypeInterface {
    @Composable
    fun setColorBasedType(type: Type, color: Int, value: String): Int
}