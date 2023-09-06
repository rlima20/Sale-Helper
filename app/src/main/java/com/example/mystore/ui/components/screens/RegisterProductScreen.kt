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
import com.example.mystore.setQuantifierSize
import com.example.mystore.ui.components.commons.FloatingActionButton
import com.example.mystore.ui.components.commons.ImageComponent
import com.example.mystore.ui.components.commons.OutLinedTextFieldComponent
import com.example.mystore.ui.components.commons.Quantifier
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.components.commons.TextFormattedComponent
import com.example.mystore.ui.components.commons.ToastComponent
import com.example.mystore.ui.components.commons.getPainter
import com.example.mystore.ui.components.commons.showAlertDialogComponent
import com.example.mystore.viewmodel.screen.RegisterProductViewModel

@Composable
fun RegisterProductScreen(
    product: Product = Product(),
    isEditMode: Boolean,
    registerProductViewModel: RegisterProductViewModel,
    onClearStates: (Boolean) -> Unit,
) {
    with(registerProductViewModel) {
        setScreenWidth(LocalConfiguration.current.screenWidthDp)
        setTitleSelectedText(product.title)
        setDescriptionSelectedText(product.description)
        setPurchasePriceSelectedText(product.purchasePrice.toString())
        setSalePriceSelectedText(product.salePrice.toString())
        setQuantity(product.quantity)
        setMaxQuantityToBuy(product.maxQuantityToBuy)
        setShowToastProductScreen(false)

        ScreenSectionComponent(
            title = stringResource(id = R.string.my_store_product_2).setTitle(isEditMode),
            body = {
                RegisterProductScreenBody(
                    product = product,
                    isEditMode = isEditMode,
                    registerProductViewModel = registerProductViewModel,
                    screenWidth = screenWidth.collectAsState().value,
                    titleSelectedText = titleSelectedText.collectAsState().value,
                    descriptionSelectedText = descriptionSelectedText.collectAsState().value,
                    purchasePriceSelectedText = purchasePriceSelectedText.collectAsState().value,
                    salePriceSelectedText = salePriceSelectedText.collectAsState().value,
                    quantity = quantity.collectAsState().value,
                    maxQuantityToBuy = maxQuantityToBuy.collectAsState().value,
                    showAlertDialogProductScreen = showAlertDialogProductScreen.collectAsState()
                        .value,
                    showToastProductScreen = showToastProductScreen.collectAsState().value,
                )
                onClearStates(false)
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
    titleSelectedText: String,
    descriptionSelectedText: String,
    purchasePriceSelectedText: String,
    salePriceSelectedText: String,
    quantity: Int,
    maxQuantityToBuy: Int,
    showAlertDialogProductScreen: Boolean,
    showToastProductScreen: Boolean,
) {
    if (showToastProductScreen) {
        ToastComponent("Produto cadastrado com sucesso!")
    }

    // AlertDialog with delete confirmation
    showAlertDialogComponent(
        showAlert = showAlertDialogProductScreen,
        title = if (isEditMode) {
            stringResource(R.string.my_store_registry_update)
        } else {
            stringResource(R.string.my_store_registry_creation)
        },
        alertDialogMessage = stringResource(R.string.my_store_creation_confirmation),
        onDismissRequest = { registerProductViewModel.setShowAlertDialogProductScreen(false) },
        onDismissButtonClicked = {
            registerProductViewModel.setShowAlertDialogProductScreen(false)
        },
        onConfirmButtonClicked = {
            registerProductViewModel.setShowToastProductScreen(true)
            /*registerProductViewModel.saveProduct(
                product = Product(
                    title = titleSelectedText,
                    description = descriptionSelectedText,
                    purchasePrice = purchasePriceSelectedText.toDouble(),
                    salePrice = salePriceSelectedText.toDouble(),
                    quantity = quantity,
                    maxQuantityToBuy = maxQuantityToBuy,
                ),
                isEditMode = isEditMode,
            )*/
            registerProductViewModel.setShowAlertDialogProductScreen(false)
        },
    )

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
        selectedText = titleSelectedText,
        label = titleLabel,
        keyboardController = titleKeyboardController,
        focusManager = titleFocusManager,
        onValueChanged = {
            registerProductViewModel.setTitleSelectedText(it)
        },
    )

    // Description
    val descriptionLabel = stringResource(id = R.string.my_store_product_description)
    val descriptionKeyboardController = LocalSoftwareKeyboardController.current
    val descriptionFocusManager = LocalFocusManager.current
    OutLinedTextFieldComponent(
        selectedText = descriptionSelectedText,
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
        selectedText = purchasePriceSelectedText,
        label = purchasePriceLabel,
        keyboardController = purchasePriceKeyboardController,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        focusManager = purchasePriceFocusManager,
        onValueChanged = {
            registerProductViewModel.setPurchasePriceSelectedText(it.removeCurrencyToProductValue())
        },
        onDone = {
            registerProductViewModel.setPurchasePriceSelectedText(
                purchasePriceSelectedText.removeCurrencyToProductValue()
                    .addCurrencyToProductValue(),
            )
        },
    )

    // Sale Price
    val salePriceLabel = stringResource(id = R.string.my_store_product_sell_price)
    val salePriceKeyboardController = LocalSoftwareKeyboardController.current
    val salePriceFocusManager = LocalFocusManager.current
    OutLinedTextFieldComponent(
        selectedText = salePriceSelectedText,
        label = salePriceLabel,
        keyboardController = salePriceKeyboardController,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        focusManager = salePriceFocusManager,
        onValueChanged = {
            registerProductViewModel.setSalePriceSelectedText(it.removeCurrencyToProductValue())
        },
        onDone = {
            registerProductViewModel.setSalePriceSelectedText(
                salePriceSelectedText.removeCurrencyToProductValue()
                    .addCurrencyToProductValue(),
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
                quantity = quantity,
                onQuantifierChange = { registerProductViewModel.setQuantity(it) },
            )
        }
        Column {
            // Max Quantity To Buy
            TextFormattedComponent(
                leftSideText = stringResource(id = R.string.my_store_product_max_quantity),
                fontSize = 18.sp,
            )
            Quantifier(
                modifier = Modifier
                    .width(screenWidth.setQuantifierSize())
                    .padding(start = 4.dp, end = 8.dp),
                quantity = maxQuantityToBuy,
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
                colorId = R.color.color_50,
                onClick = { registerProductViewModel.setShowAlertDialogProductScreen(true) },
            )
        }
    }
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

private fun String.setTitle(editMode: Boolean) =
    if (editMode) "$this | EDIÇÃO" else "$this | CRIAÇÃO"

private fun String.removeCurrencyToProductValue() = this.replace("R$", "")

private fun String.addCurrencyToProductValue() = "R$ ${
    String.format("%.2f", this.replace(",", ".").toDouble())
        .replace(".", ",")
}"
