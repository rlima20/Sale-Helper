package com.example.mystore.ui.components.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mystore.R
import com.example.mystore.model.Product
import com.example.mystore.model.RegisterProductProps
import com.example.mystore.setQuantifierSize
import com.example.mystore.ui.components.commons.FloatingActionButton
import com.example.mystore.ui.components.commons.ImageComponent
import com.example.mystore.ui.components.commons.OutLinedTextFieldComponent
import com.example.mystore.ui.components.commons.Quantifier
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.components.commons.TextFormattedComponent
import com.example.mystore.ui.components.commons.getPainter
import com.example.mystore.viewmodel.screen.RegisterProductViewModel

@Composable
fun RegisterProductScreen(
    product: Product = Product(),
    isEditMode: Boolean = true,
    registerProductViewModel: RegisterProductViewModel,
    stateCleared: Boolean,
    onClearStates: (Boolean) -> Unit,
) {
    registerProductViewModel.setScreenWidth(LocalConfiguration.current.screenWidthDp)

    with(registerProductViewModel) {
        val props = RegisterProductProps(
            screenWidth = screenWidth.collectAsState().value,
            titleSelectedText = titleSelectedText.collectAsState().value,
            descriptionSelectedText = descriptionSelectedText.collectAsState().value,
            purchasePriceSelectedText = purchasePriceSelectedText.collectAsState().value,
            salePriceSelectedText = salePriceSelectedText.collectAsState().value,
            quantity = quantity.collectAsState().value,
            maxQuantityToBuy = maxQuantityToBuy.collectAsState().value,
        )

        ScreenSectionComponent(
            title = stringResource(id = R.string.my_store_product_2).setTitle(isEditMode),
            body = {
                setInitialState(stateCleared)
                RegisterProductScreenBody(
                    product = product,
                    screenWidth = props.screenWidth,
                    isEditMode = isEditMode,
                    registerProductViewModel = registerProductViewModel,
                    props = RegisterProductProps(
                        screenWidth = screenWidth.collectAsState().value,
                        titleSelectedText = titleSelectedText.collectAsState().value,
                        descriptionSelectedText = descriptionSelectedText.collectAsState().value,
                        purchasePriceSelectedText = purchasePriceSelectedText.collectAsState().value,
                        salePriceSelectedText = salePriceSelectedText.collectAsState().value,
                        quantity = quantity.collectAsState().value,
                        maxQuantityToBuy = maxQuantityToBuy.collectAsState().value,
                    ),
                    stateCleared = stateCleared,
                    onClearStates = { onClearStates(it) },
                )
            },
        )
    }
}

// Dropdown TransactionType
@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun RegisterProductScreenBody(
    product: Product,
    screenWidth: Int,
    isEditMode: Boolean = false,
    registerProductViewModel: RegisterProductViewModel,
    props: RegisterProductProps,
    stateCleared: Boolean,
    onClearStates: (Boolean) -> Unit,
) {
    // Image Section
    ImageSection(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                enabled = true,
                onClick = { },
                onLongClick = { },
                onDoubleClick = { },
            ),
        registerProductViewModel = registerProductViewModel,
        product = product,
    )

    // Title
    val titleLabel = stringResource(id = R.string.my_store_product_title)
    val titleKeyboardController = LocalSoftwareKeyboardController.current
    val titleFocusManager = LocalFocusManager.current
    OutLinedTextFieldComponent(
        selectedText = if (isEditMode && !stateCleared) product.title else props.titleSelectedText,
        label = titleLabel,
        keyboardController = titleKeyboardController,
        focusManager = titleFocusManager,
        onValueChanged = { registerProductViewModel.setTitleSelectedText(it) },
    )

    // Description
    val descriptionLabel = stringResource(id = R.string.my_store_product_description)
    val descriptionKeyboardController = LocalSoftwareKeyboardController.current
    val descriptionFocusManager = LocalFocusManager.current
    OutLinedTextFieldComponent(
        selectedText = if (isEditMode && !stateCleared) {
            product.description
        } else {
            props
                .descriptionSelectedText
        },
        label = descriptionLabel,
        keyboardController = descriptionKeyboardController,
        focusManager = descriptionFocusManager,
        onValueChanged = { registerProductViewModel.setDescriptionSelectedText(it) },
    )

    // Purchase Price
    val purchasePriceLabel = stringResource(id = R.string.my_store_product_purchase_price)
    val purchasePriceKeyboardController = LocalSoftwareKeyboardController.current
    val purchasePriceFocusManager = LocalFocusManager.current
    OutLinedTextFieldComponent(
        selectedText = if (isEditMode && !stateCleared) {
            product.purchasePrice.toString()
        } else {
            props
                .purchasePriceSelectedText
        },
        label = purchasePriceLabel,
        keyboardController = purchasePriceKeyboardController,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        focusManager = purchasePriceFocusManager,
        onValueChanged = { registerProductViewModel.setPurchasePriceSelectedText(it.removeCurrency()) },
        onDone = {
            registerProductViewModel.setPurchasePriceSelectedText(
                props.purchasePriceSelectedText.removeCurrency()
                    .addCurrency(),
            )
        },
    )

    // Sale Price
    val salePriceLabel = stringResource(id = R.string.my_store_product_sell_price)
    val salePriceKeyboardController = LocalSoftwareKeyboardController.current
    val salePriceFocusManager = LocalFocusManager.current
    OutLinedTextFieldComponent(
        selectedText = if (isEditMode && !stateCleared) {
            product.salePrice.toString()
        } else {
            props
                .salePriceSelectedText
        },
        label = salePriceLabel,
        keyboardController = salePriceKeyboardController,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        focusManager = salePriceFocusManager,
        onValueChanged = { registerProductViewModel.setSalePriceSelectedText(it.removeCurrency()) },
        onDone = {
            registerProductViewModel.setSalePriceSelectedText(
                props.salePriceSelectedText.removeCurrency().addCurrency(),
            )
        },
    )

    Row {
        Column {
            // Quantity
            TextFormattedComponent(
                leftSideText = stringResource(id = R.string.my_store_product_quantity),
                fontSize = 18.sp,
            )
            Quantifier(
                modifier = Modifier
                    .width(screenWidth.setQuantifierSize())
                    .padding(start = 8.dp, end = 4.dp),
                enabled = false,
                quantity = props.quantity,
                onQuantifierChange = { registerProductViewModel.setQuantity(it) },
            )
        }
        Column {
            // Max Quantity To Buy
            TextFormattedComponent(
                leftSideText = "Qty Max",
                fontSize = 18.sp,
            )
            registerProductViewModel.setMaxQuantityToBuy(
                if (isEditMode && !stateCleared) {
                    product
                        .maxQuantityToBuy
                } else {
                    0
                },
            )
            Quantifier(
                modifier = Modifier
                    .width(screenWidth.setQuantifierSize())
                    .padding(start = 4.dp, end = 8.dp),
                quantity = props.maxQuantityToBuy,
                onQuantifierChange = { registerProductViewModel.setMaxQuantityToBuy(it) },
            )
        }

        // Save button
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier
                .offset(y = (12).dp)
                .height(56.dp)
                .fillMaxWidth()
                .padding(top = 8.dp),
        ) {
            FloatingActionButton(
                enabled = true,
                modifier = Modifier.size(36.dp),
                colorId = if (true) {
                    R.color.color_50
                } else {
                    R.color.color_400
                },
                onClick = {
                    // todo - clearAll
                },
            )
        }
    }
    onClearStates(false)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSection(
    modifier: Modifier = Modifier,
    registerProductViewModel: RegisterProductViewModel,
    product: Product = Product(),
    onProductClick: () -> Unit = {},
    onProductLongClick: (Product) -> Unit = {},
    onProductDoubleClick: () -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.background(colorResource(id = R.color.color_800)),
    ) {
        ImageComponent(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            painterResource = getPainter(
                imageUrl = product.imageUrl,
                onImageRequestState = {
                    registerProductViewModel.setImageRequestState(it)
                },
            ),
        )
    }
}

private fun clearAllFields(
    onClearTitleSelectedText: () -> Unit,
    onClearDescriptionSelectedText: () -> Unit,
    onClearPurchasePriceSelectedText: () -> Unit,
    onClearSalePriceSelectedText: () -> Unit,
    onClearQuantity: () -> Unit,
    onClearMaxQuantityToBuy: () -> Unit,
) {
    onClearTitleSelectedText()
    onClearDescriptionSelectedText()
    onClearPurchasePriceSelectedText()
    onClearSalePriceSelectedText()
    onClearQuantity()
    onClearMaxQuantityToBuy()
}

@Composable
fun setInitialState(clearAllStates: Boolean = false) {
    if (clearAllStates) {
        clearAllFields(
            onClearTitleSelectedText = { "" },
            onClearDescriptionSelectedText = {},
            onClearPurchasePriceSelectedText = {},
            onClearSalePriceSelectedText = {},
            onClearQuantity = {},
            onClearMaxQuantityToBuy = {},
        )
    }
}

private fun String.setTitle(editMode: Boolean) =
    if (editMode) "$this | EDIÇÃO" else "$this | CRIAÇÃO"

private fun setInitialState(
    isEditMode: Boolean,
    price: Double,
) = if (isEditMode) price.toString().addCurrency() else "R$ 0.00"

fun String.removeCurrency() = this.replace("R$", "")

fun String.addCurrency() = "R$ ${
    String.format("%.2f", this.replace(",", ".").toDouble())
        .replace(".", ",")
}"
