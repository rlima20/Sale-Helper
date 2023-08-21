package com.example.mystore.ui.components.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mystore.R
import com.example.mystore.Screens
import com.example.mystore.TransactionType
import com.example.mystore.Type
import com.example.mystore.limitTo
import com.example.mystore.model.Product
import com.example.mystore.model.Transaction
import com.example.mystore.toTransactionType
import com.example.mystore.ui.components.commons.DropdownComponent
import com.example.mystore.ui.components.commons.FloatingActionButton
import com.example.mystore.ui.components.commons.Quantifier
import com.example.mystore.ui.components.commons.RowComponent
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.components.commons.SectionInfo
import com.example.mystore.ui.components.commons.TextCurrencyComponent
import com.example.mystore.ui.components.commons.ValidateSection
import com.example.mystore.viewmodel.screen.RegisterTransactionViewModel
import java.util.Calendar

// Todo - Quando navegar para fora, para outra tela, limpar os states
// Todo - Bug to Total. Quando seleciona o tipo de transação, o total não é atualizado.
// Todo - Fazer testes regressivos
// Todo - dar continuidade nas outras funcionalidades
@Composable
fun RegisterTransactionScreen(
    registerTransactionViewModel: RegisterTransactionViewModel,
    shouldItemBeVisible: Boolean,
) {
    registerTransactionViewModel.setScreenWidth(LocalConfiguration.current.screenWidthDp)

    val listOfProducts by registerTransactionViewModel.listOfProducts.collectAsState()
    val listOfTransactionTypes by registerTransactionViewModel.listOfTransactionType.collectAsState()
    val screenWidth by registerTransactionViewModel.screenWidth.collectAsState()
    val quantity by registerTransactionViewModel.quantity.collectAsState()
    val maxQuantity by registerTransactionViewModel.maxQuantity.collectAsState()
    val total by registerTransactionViewModel.totalValue.collectAsState()
    val transaction by registerTransactionViewModel.transactionValue.collectAsState()

    Column {
        ValidateSection(
            screen = Screens.REGISTER_TRANSACTION,
            sectionInfo = SectionInfo(
                section = {
                    ScreenSectionComponent(
                        title = stringResource(id = R.string.my_store_register_transaction),
                        body = {
                            RegisterTransactionBody(
                                screenWidth = screenWidth,
                                shouldItemBeVisible = shouldItemBeVisible,
                                listOfProducts = listOfProducts,
                                listOfTransactionTypes = listOfTransactionTypes,
                                quantity = quantity,
                                maxQuantity = maxQuantity,
                                total = total,
                                transaction = transaction,
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
private fun RegisterTransactionBody(
    screenWidth: Int,
    shouldItemBeVisible: Boolean = true,
    listOfProducts: List<Product>,
    listOfTransactionTypes: List<TransactionType>,
    quantity: Int,
    maxQuantity: Int,
    total: Double,
    transaction: Transaction,
    registerTransactionViewModel: RegisterTransactionViewModel,
) {
    Column {
        var selectedTextTransaction: String by remember { mutableStateOf("") }
        var isExpandedTransaction: Boolean by remember { mutableStateOf(false) }
        var textFieldSizeTransaction: Size by remember { mutableStateOf(Size.Zero) }

        var selectedTextProduct: String by remember { mutableStateOf("") }
        var isExpandedProduct: Boolean by remember { mutableStateOf(false) }
        var textFieldSizeProduct: Size by remember { mutableStateOf(Size.Zero) }

        Row(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
        ) {
            DropdownComponent(
                isExpanded = isExpandedTransaction,
                items = listOfTransactionTypes.map { it.name },
                selectedText = selectedTextTransaction,
                textFieldSize = textFieldSizeTransaction,
                label = stringResource(id = R.string.my_store_transaction_type),
                modifier = Modifier.fillMaxWidth(),
                onOutLinedTextFieldSize = { textFieldSizeTransaction = it },
                onOutLinedTextFieldValueChanged = { selectedTextTransaction = it },
                onTrailingIconClicked = { isExpandedTransaction = !isExpandedTransaction },
                onDropdownMenuDismissRequest = { isExpandedTransaction = false },
                onDropdownMenuItemClicked = { itemSelected ->
                    setScreenStates(
                        listOfProducts = listOfProducts,
                        selectedTextTransaction = selectedTextTransaction,
                        quantity = quantity,
                        productName = selectedTextProduct,
                        maxQuantity = maxQuantity,
                        registerTransactionViewModel = registerTransactionViewModel,
                        itemSelected = itemSelected,
                        onSelectedTextTransaction = { selectedTextTransaction = it },
                        onSetQuantity = { quantity, newTransaction ->
                            registerTransactionViewModel.setQuantity(
                                if (quantity > newTransaction.product.quantity) {
                                    newTransaction.product.quantity
                                } else {
                                    quantity
                                },
                            )
                        },
                    )
                },
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
        ) {
            DropdownComponent(
                isExpanded = isExpandedProduct,
                items = listOfProducts.map { it.title.limitTo(20) },
                selectedText = selectedTextProduct,
                textFieldSize = textFieldSizeProduct,
                label = stringResource(id = R.string.my_store_product_2),
                modifier = Modifier.width(setItemSize(screenWidth)),
                onOutLinedTextFieldSize = { textFieldSizeProduct = it },
                onOutLinedTextFieldValueChanged = { selectedTextProduct = it },
                onTrailingIconClicked = { isExpandedProduct = !isExpandedProduct },
                onDropdownMenuDismissRequest = { isExpandedProduct = false },
                onDropdownMenuItemClicked = { itemSelected ->
                    setScreenStates(
                        listOfProducts = listOfProducts,
                        selectedTextTransaction = selectedTextTransaction,
                        quantity = quantity,
                        productName = itemSelected,
                        maxQuantity = maxQuantity,
                        registerTransactionViewModel = registerTransactionViewModel,
                        itemSelected = itemSelected,
                        onSelectedTextTransaction = { selectedTextProduct = it },
                    )
                },
            )

            Quantifier(
                maxQuantity = setMaxValue(
                    selectedTextTransaction = selectedTextTransaction,
                    transaction = transaction,
                    maxQuantity = maxQuantity,
                ),
                width = setItemSize(screenWidth),
                quantity = quantity,
                onQuantifierChange = { quantifierQuantity ->
                    setScreenStates(
                        listOfProducts = listOfProducts,
                        selectedTextTransaction = selectedTextTransaction,
                        quantity = quantity,
                        productName = selectedTextProduct,
                        maxQuantity = maxQuantity,
                        registerTransactionViewModel = registerTransactionViewModel,
                        quantifierQuantity = quantifierQuantity,
                        onQuantifierQuantity = { registerTransactionViewModel.setQuantity(it) },
                    )
                },
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
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
            Box(modifier = Modifier.padding(end = 22.dp)) {
                FloatingActionButton(
                    enabled = total > 0.0,
                    modifier = Modifier.size(36.dp),
                    colorId = if (total > 0.0) R.color.color_50 else R.color.color_400,
                    onClick = {
                        registerTransactionViewModel.saveTransaction(
                            registerTransactionViewModel
                                .transactionValue.value,
                        )

                        registerTransactionViewModel.updateProductQuantity(
                            registerTransactionViewModel.transactionValue.value.product,
                            quantity,
                            registerTransactionViewModel.transactionValue.value,
                        )

                        // Clear all fields
                        clearStates(
                            registerTransactionViewModel = registerTransactionViewModel,
                            onChangeSelectedTextTransaction = { selectedTextTransaction = it },
                            onChangeSelectedTextProduct = { selectedTextProduct = it },
                        )
                    },
                )
            }
        }
    }
}

private fun clearStates(
    registerTransactionViewModel: RegisterTransactionViewModel,
    onChangeSelectedTextTransaction: (String) -> Unit = {},
    onChangeSelectedTextProduct: (String) -> Unit = {},
) {
    registerTransactionViewModel.clearAll()
    onChangeSelectedTextTransaction("")
    onChangeSelectedTextProduct("")
}

private fun setMaxQuantityByTransactionType(
    selectedTextTransaction: String,
    maxQuantity: Int,
    newTransaction: Transaction,
): Int {
    return if (selectedTextTransaction.toTransactionType() == TransactionType
            .SALE && maxQuantity > newTransaction.product.quantity
    ) {
        newTransaction.product.quantity + 1
    } else if (selectedTextTransaction.toTransactionType() == TransactionType
            .PURCHASE
    ) {
        newTransaction.product.maxQuantityToBuy
    } else {
        newTransaction.product.quantity
    }
}

@Composable
private fun setMaxValue(
    selectedTextTransaction: String,
    transaction: Transaction,
    maxQuantity: Int,
) = if (selectedTextTransaction.toTransactionType() == TransactionType.SALE) {
    transaction.product.quantity
} else {
    maxQuantity
}

private fun stringToProduct(
    listOfProducts: List<Product>,
    productName: String,
): Product {
    return listOfProducts.find {
        it.title.limitTo(20) == productName
    } ?: Product()
}

private fun setStates(
    registerTransactionViewModel: RegisterTransactionViewModel,
    selectedTextTransaction: String,
    newTransaction: Transaction,
    maxQuantity: Int,
) {
    registerTransactionViewModel.setTotalValue(newTransaction.transactionAmount)
    registerTransactionViewModel.setTransactionValue(newTransaction)
    registerTransactionViewModel.setMaxQuantity(
        setMaxQuantityByTransactionType(
            selectedTextTransaction = selectedTextTransaction,
            maxQuantity = maxQuantity,
            newTransaction = newTransaction,
        ),
    )
}

private fun createTransaction(
    product: Product,
    transactionType: TransactionType,
    quantity: Int,
): Transaction {
    val unitValue =
        if (transactionType == TransactionType.SALE) product.salePrice else product.purchasePrice
    return Transaction(
        product = product,
        transactionType = transactionType,
        unitValue = unitValue,
        transactionDate = Calendar.getInstance().time,
        quantity = quantity,
        transactionAmount = quantity * unitValue,
    )
}

private fun setItemSize(screenWidth: Int): Dp = ((screenWidth - 16) / 2).dp

private fun setScreenStates(
    listOfProducts: List<Product>,
    selectedTextTransaction: String,
    quantity: Int,
    maxQuantity: Int,
    productName: String,
    registerTransactionViewModel: RegisterTransactionViewModel,
    itemSelected: String = "",
    onSelectedTextTransaction: (String) -> Unit = {},
    onSetQuantity: (Int, Transaction) -> Unit = { _, _ -> },
    quantifierQuantity: Int? = null,
    onQuantifierQuantity: (Int) -> Unit = {},
) {
    onQuantifierQuantity(quantifierQuantity ?: 0)
    onSelectedTextTransaction(itemSelected)

    val newTransaction = createTransaction(
        product = stringToProduct(
            listOfProducts = listOfProducts,
            productName = productName,
        ),
        transactionType = selectedTextTransaction.toTransactionType(),
        quantity = quantifierQuantity ?: quantity,
    )
    setStates(
        registerTransactionViewModel = registerTransactionViewModel,
        selectedTextTransaction = selectedTextTransaction,
        newTransaction = newTransaction,
        maxQuantity = maxQuantity,
    )

    onSetQuantity(quantity, newTransaction)
}
