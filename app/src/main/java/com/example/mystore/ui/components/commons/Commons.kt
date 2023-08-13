package com.example.mystore.ui.components.commons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mystore.R
import com.example.mystore.Section
import com.example.mystore.Type
import com.example.mystore.toCurrency
import com.example.mystore.toUnity
import com.example.mystore.ui.navigation.RegisterProductScreen
import com.example.mystore.ui.navigation.RegisterTransactionScreen

data class SectionInfo(
    val section: @Composable () -> Unit,
)

data class SectionEmptyStateInfo(
    val data: List<Any>,
    val emptySectionTitle: String,
    val emptySectionPainter: Painter,
    val onEmptyStateImageClicked: () -> Unit = {},
)

@Composable
fun setUnit(
    type: Type,
    value: String,
    shouldItemBeVisible: Boolean,
) = when (type) {
    Type.CURRENCY -> value.toDouble().toCurrency(shouldItemBeVisible)
    Type.PURCHASE_CURRENCY -> value.toDouble().toCurrency(shouldItemBeVisible)
    Type.QUANTITY -> value.toInt().toUnity(shouldItemBeVisible)
    Type.STRING -> value
    Type.DATE -> value
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
    }
}

@Composable
fun ValidateSection(
    sectionInfo: SectionInfo,
    sectionEmptyStateInfo: SectionEmptyStateInfo,
) {
    if (sectionEmptyStateInfo.data.isEmpty()) {
        EmptyStateSectionComponent(
            title = sectionEmptyStateInfo.emptySectionTitle,
            painter = sectionEmptyStateInfo.emptySectionPainter,
            onEmptyStateImageClicked = { sectionEmptyStateInfo.onEmptyStateImageClicked() },
        )
    } else {
        sectionInfo.section()
    }
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
                    type = Type.CURRENCY,
                )
            },
        )
        RowComponent(
            leftSideText = stringResource(id = R.string.my_store_total_sales),
            rightSide = {
                TextCurrencyComponent(
                    value = salesValue.toString(),
                    shouldItemBeVisible = shouldItemBeVisible,
                    type = Type.CURRENCY,
                )
            },
        )
        RowComponent(
            leftSideText = stringResource(id = R.string.my_store_total),
            rightSide = {
                TextCurrencyComponent(
                    value = (salesValue - purchasesValue).toString(),
                    shouldItemBeVisible = shouldItemBeVisible,
                    type = Type.PURCHASE_CURRENCY,
                )
            },
        )
    }
}
