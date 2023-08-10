package com.example.mystore.ui.components.commons

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.example.mystore.Section
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
