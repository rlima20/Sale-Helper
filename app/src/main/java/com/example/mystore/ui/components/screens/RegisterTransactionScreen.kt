package com.example.mystore.ui.components.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mystore.R
import com.example.mystore.Screens
import com.example.mystore.TransactionType
import com.example.mystore.Type
import com.example.mystore.limitTo
import com.example.mystore.model.DropdownInfo
import com.example.mystore.model.Product
import com.example.mystore.model.Transaction
import com.example.mystore.toTransactionType
import com.example.mystore.ui.components.commons.DropdownComponent
import com.example.mystore.ui.components.commons.Quantifier
import com.example.mystore.ui.components.commons.RowComponent
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.components.commons.SectionInfo
import com.example.mystore.ui.components.commons.TextCurrencyComponent
import com.example.mystore.ui.components.commons.ValidateSection
import com.example.mystore.viewmodel.screen.RegisterTransactionViewModel
import java.util.Calendar

@Composable
fun RegisterTransactionScreen(
    registerTransactionViewModel: RegisterTransactionViewModel,
    shouldItemBeVisible: Boolean,
) {
    val listOfProducts by registerTransactionViewModel.listOfProducts.collectAsState()
    val listOfTransactionTypes by registerTransactionViewModel.listOfTransactionType.collectAsState()
//    val quantity by registerTransactionViewModel.quantity.collectAsState()
//    val screenWidth by registerTransactionViewModel.screenWidth.collectAsState()
//    val total by registerTransactionViewModel.totalValue.collectAsState()
//

    val currentWidth = LocalConfiguration.current.screenWidthDp
    val quantity by remember { mutableStateOf(1) }
    val screenWidth by remember { mutableStateOf(currentWidth) }
    val total by remember { mutableStateOf(0.0) }

    val dropdownInfoTypeOfTransaction by remember { mutableStateOf(DropdownInfo()) }
    val dropdownInfoProduct by remember { mutableStateOf(DropdownInfo()) }

    Column {
        ValidateSection(
            screen = Screens.REGISTER_TRANSACTION,
            sectionInfo = SectionInfo(
                section = {
                    ScreenSectionComponent(
                        title = stringResource(id = R.string.my_store_register_transaction),
                        body = {
                            RegisterTransactionBody(
                                listOfTransactionTypes = listOfTransactionTypes,
                                listOfProducts = listOfProducts,
                                dropdownInfoProduct = dropdownInfoProduct,
                                dropdownInfoTypeOfTransaction = dropdownInfoTypeOfTransaction,
                                quantity = quantity,
                                total = total,
                                screenWidth = screenWidth,
                                shouldItemBeVisible = shouldItemBeVisible,
                                registerTransactionViewModel = registerTransactionViewModel,

                            )
                        },
                    )
                },
            ),
        )
    }
}

@Composable
fun RegisterTransactionBody(
    shouldItemBeVisible: Boolean = true,
    screenWidth: Int,
    listOfTransactionTypes: List<TransactionType>,
    total: Double,
    quantity: Int,
    dropdownInfoProduct: DropdownInfo,
    dropdownInfoTypeOfTransaction: DropdownInfo,
    registerTransactionViewModel: RegisterTransactionViewModel,
    listOfProducts: List<Product>,
) {
    Column {
        Row(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
        ) {
            DropdownComponent(
                isExpanded = dropdownInfoTypeOfTransaction.isExpanded,
                items = listOfTransactionTypes.map { it.name },
                selectedText = dropdownInfoTypeOfTransaction.selectedText,
                textFieldSize = dropdownInfoTypeOfTransaction.textFieldSize,
                label = stringResource(id = R.string.my_store_transaction_type),
                modifier = Modifier.fillMaxWidth(),
                onOutLinedTextFieldSize = { dropdownInfoTypeOfTransaction.setTextFieldSize(it) },
                onOutLinedTextFieldValueChanged = {
                    dropdownInfoTypeOfTransaction.setSelectedText(it)
                },
                onTrailingIconClicked = {
                    dropdownInfoTypeOfTransaction.setExpanded(
                        !dropdownInfoTypeOfTransaction.isExpanded,
                    )
                },
                onDropdownMenuDismissRequest = {
                    dropdownInfoTypeOfTransaction.setExpanded(
                        false,
                    )
                },
                onDropdownMenuItemClicked = {
                    dropdownInfoTypeOfTransaction.setSelectedText(it)
                    val transaction = setTotalValue(
                        product = toProduct(
                            listOfProducts = listOfProducts,
                            productName = dropdownInfoTypeOfTransaction.selectedText,
                        ),
                        transactionType = dropdownInfoTypeOfTransaction.selectedText.toTransactionType(),
                        quantity = quantity,
                    )
                    registerTransactionViewModel.setTotalValue(transaction.transactionAmount)
                },
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
        ) {
            DropdownComponent(

                isExpanded = dropdownInfoProduct.isExpanded,
                items = listOfProducts.map { it.title.limitTo(20) },
                selectedText = dropdownInfoProduct.selectedText,
                textFieldSize = dropdownInfoProduct.textFieldSize,
                label = stringResource(id = R.string.my_store_product_2),
                modifier = Modifier.width(setItemSize(screenWidth)),
                onOutLinedTextFieldSize = { dropdownInfoProduct.setTextFieldSize(it) },
                onOutLinedTextFieldValueChanged = {
                    dropdownInfoProduct.setSelectedText(it)
                },
                onTrailingIconClicked = { dropdownInfoProduct.setExpanded(!dropdownInfoProduct.isExpanded) },
                onDropdownMenuDismissRequest = { dropdownInfoProduct.setExpanded(false) },
                onDropdownMenuItemClicked = {
                    dropdownInfoProduct.setSelectedText(it)
                    val transaction = setTotalValue(
                        product = toProduct(
                            listOfProducts = listOfProducts,
                            productName = dropdownInfoProduct.selectedText,
                        ),
                        transactionType = dropdownInfoProduct.selectedText.toTransactionType(),
                        quantity = quantity,
                    )
                    registerTransactionViewModel.setTotalValue(transaction.transactionAmount)
                },
            )

            Quantifier(
                maxValue = 100,
                width = setItemSize(screenWidth),
                quantifier = quantity,
                onQuantifierChange = {
                    registerTransactionViewModel.setQuantity(it)
                    val transaction = setTotalValue(
                        product = toProduct(
                            listOfProducts = listOfProducts,
                            productName = dropdownInfoProduct.selectedText,
                        ),
                        transactionType = dropdownInfoTypeOfTransaction.selectedText.toTransactionType(),
                        quantity = quantity,
                    )
                    registerTransactionViewModel.setTotalValue(transaction.transactionAmount)
                },
            )
        }

        RowComponent(
            leftSideText = stringResource(id = R.string.my_store_total),
            rightSide = {
                TextCurrencyComponent(
                    value = total.toString(),
                    shouldItemBeVisible = shouldItemBeVisible,
                    type = Type.CURRENCY,
                )
            },
        )
    }
}

private fun toProduct(
    listOfProducts: List<Product>,
    productName: String,
): Product {
    return listOfProducts.find {
        it.title.limitTo(20) == productName
    } ?: Product()
}

private fun setTotalValue(
    product: Product,
    transactionType: TransactionType,
    quantity: Int,
): Transaction {
    val price = if (transactionType == TransactionType.SALE) {
        product.salePrice
    } else {
        product.purchasePrice
    }
    return Transaction(
        product = product,
        transactionType = transactionType,
        unitValue = price,
        transactionDate = Calendar.getInstance().time,
        quantity = quantity,
        transactionAmount = quantity * price,
    )
}

private fun setItemSize(screenWidth: Int): Dp = ((screenWidth - 16) / 2).dp
