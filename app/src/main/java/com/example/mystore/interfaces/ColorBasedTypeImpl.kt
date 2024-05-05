package com.example.mystore.interfaces

import androidx.compose.runtime.Composable
import com.example.mystore.R
import com.example.mystore.Type
import com.example.mystore.ui.components.commons.setTextColor

class ColorBasedTypeImpl : ColorBasedTypeInterface {
    @Composable
    override fun setColorBasedType(type: Type, color: Int, value: String): Int {
        return when (type) {
            Type.CURRENCY_DEBIT_ONLY -> R.color.color_red_A1000
            Type.STRING -> color
            Type.CURRENCY_TRANSACTION_DETAIL -> R.color.color_900
            Type.QUANTITY_TRANSACTION_DETAIL -> R.color.color_900
            Type.DATE -> color
            Type.STRING_ONLY -> color
            else -> setTextColor(value.toDouble())
        }
    }
}