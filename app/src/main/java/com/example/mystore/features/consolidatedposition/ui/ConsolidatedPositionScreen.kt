package com.example.mystore.features.consolidatedposition.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mystore.R
import com.example.mystore.enums.Screens
import com.example.mystore.enums.Section
import com.example.mystore.features.homescreen.viewmodel.HomeViewModel
import com.example.mystore.features.registertransaction.model.Transaction
import com.example.mystore.toStringResource
import com.example.mystore.ui.components.commons.ConsolidatedPositionScreenSection
import com.example.mystore.ui.components.commons.SectionEmptyStateInfo
import com.example.mystore.ui.components.commons.SectionInfo
import com.example.mystore.ui.components.commons.ValidateSection
import com.example.mystore.ui.components.commons.validateSection
import com.example.mystore.ui.model.ColorProps
import com.example.mystore.ui.navigation.EditTransactionScreen

@Composable
fun ConsolidatedPositionScreen(
    homeViewModel: HomeViewModel,
    shouldItemBeVisible: Boolean,
    onNavigateToEditTransactionScreen: (route: String, transaction: Transaction) -> Unit,
    onEmptyStateImageClicked: (route: String, screen: String) -> Unit = { _, _ -> },
    onShowBottomBarExpanded: @Composable (shouldSee: Boolean) -> Unit = {},
) {

    val listOfSales = homeViewModel.commonViewState.listOfSales.value
    val listOfPurchases = homeViewModel.commonViewState.listOfPurchases.value

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(bottom = 1.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        // Sale Section
        ValidateSection(
            screen = Screens.CONSOLIDATED_POSITION,
            sectionInfo = SectionInfo(
                sectionName = Section.TRANSACTIONS,
                section = {
                    ConsolidatedPositionScreenSection(
                        title = stringResource(id = R.string.my_store_sales),
                        transactions = listOfSales,
                        shouldItemBeVisible = shouldItemBeVisible,
                        colorProps = ColorProps(),
                        onNavigateToTransactionEditScreen = { transaction ->
                            onNavigateToEditTransactionScreen(
                                EditTransactionScreen.route,
                                transaction
                            )
                        },
                    )
                },
            ),
            sectionEmptyStateInfo = SectionEmptyStateInfo(
                data = listOfSales,
                emptySectionTitle = stringResource(R.string.my_store_no_sales_done),
                emptySectionPainter = painterResource(id = R.drawable.my_store_plus_icon),
                onEmptyStateImageClicked = {
                    onEmptyStateImageClicked(
                        validateSection(Section.TRANSACTIONS),
                        R.string.my_store_register_transaction.toStringResource()
                    )
                },
            ),
        )

        // Purchase Section
        ValidateSection(
            screen = Screens.CONSOLIDATED_POSITION,
            sectionInfo = SectionInfo(
                sectionName = Section.TRANSACTIONS,
                section = {
                    ConsolidatedPositionScreenSection(
                        title = stringResource(id = R.string.my_store_purchases),
                        transactions = listOfPurchases,
                        shouldItemBeVisible = shouldItemBeVisible,
                        colorProps = ColorProps(),
                        onNavigateToTransactionEditScreen = { transaction ->
                            onNavigateToEditTransactionScreen(
                                EditTransactionScreen.route,
                                transaction
                            )
                        },
                    )
                },
            ),
            sectionEmptyStateInfo = SectionEmptyStateInfo(
                data = listOfPurchases,
                emptySectionTitle = stringResource(R.string.my_store_no_purchases_done),
                emptySectionPainter = painterResource(id = R.drawable.my_store_plus_icon),
                onEmptyStateImageClicked = {
                    onEmptyStateImageClicked(
                        validateSection(
                            Section.TRANSACTIONS,
                        ),
                        R.string.my_store_register_transaction.toStringResource()
                    )
                },
            ),
        )
        onShowBottomBarExpanded(true)
    }
}
