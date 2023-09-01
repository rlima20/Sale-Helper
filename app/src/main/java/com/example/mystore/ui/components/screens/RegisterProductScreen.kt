package com.example.mystore.ui.components.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mystore.R
import com.example.mystore.model.Product
import com.example.mystore.setItemSize
import com.example.mystore.ui.components.commons.OutLinedTextFieldComponent
import com.example.mystore.ui.components.commons.Quantifier
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.components.commons.TextFormattedComponent
import com.example.mystore.viewmodel.screen.RegisterProductViewModel

@Composable
fun RegisterProductScreen(
    product: Product,
    isEditMode: Boolean = true,
    registerProductViewModel: RegisterProductViewModel,
) {
    registerProductViewModel.setScreenWidth(LocalConfiguration.current.screenWidthDp)

    val screenWidth by registerProductViewModel.screenWidth.collectAsState()

    ScreenSectionComponent(
        title = stringResource(id = R.string.my_store_product_2).setTitle(isEditMode),
        body = {
            RegisterProductScreenBody(
                product = product,
                screenWidth = screenWidth,
                isEditMode = isEditMode,
            )
        },
    )
}

// Dropdown TransactionType
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterProductScreenBody(
    product: Product,
    screenWidth: Int,
    isEditMode: Boolean = false,
) {
    // Title
    var titleSelectedText by remember { mutableStateOf(if (isEditMode) product.title else "") }
    val titleLabel = stringResource(id = R.string.my_store_product_title)
    val titleKeyboardController = LocalSoftwareKeyboardController.current
    val titleFocusManager = LocalFocusManager.current
    OutLinedTextFieldComponent(
        selectedText = titleSelectedText,
        label = titleLabel,
        keyboardController = titleKeyboardController,
        focusManager = titleFocusManager,
        onValueChanged = { titleSelectedText = it },
    )

    // Description
    var descriptionSelectedText by remember {
        mutableStateOf(if (isEditMode) product.description else "")
    }
    val descriptionLabel = stringResource(id = R.string.my_store_product_description)
    val descriptionKeyboardController = LocalSoftwareKeyboardController.current
    val desciptionFocusManager = LocalFocusManager.current
    OutLinedTextFieldComponent(
        selectedText = descriptionSelectedText,
        label = descriptionLabel,
        keyboardController = descriptionKeyboardController,
        focusManager = desciptionFocusManager,
        onValueChanged = { descriptionSelectedText = it },
    )

    Row {
        Column {
            // Image Url

            // Quantity
            TextFormattedComponent(
                leftSideText = stringResource(id = R.string.my_store_product_quantity),
                fontSize = 18.sp,
            )
            var quantity by remember { mutableStateOf(product.quantity) }
            Quantifier(
                modifier = Modifier
                    .width(screenWidth.setItemSize())
                    .padding(start = 8.dp, end = 4.dp),
                enabled = false,
                quantity = quantity,
                onQuantifierChange = { quantity = it },
            )
        }
        Column {
            // Max Quantity To Buy
            TextFormattedComponent(
                leftSideText = "Qty Max",
                fontSize = 18.sp,
            )
            var maxQuantityToBuy by remember {
                mutableStateOf(if (isEditMode) product.maxQuantityToBuy else 0)
            }
            Quantifier(
                modifier = Modifier
                    .width(screenWidth.setItemSize())
                    .padding(start = 4.dp, end = 8.dp),
                quantity = maxQuantityToBuy,
                onQuantifierChange = { maxQuantityToBuy = it },
            )
        }
    }

    // Purchase Price
    var purchasePriceSelectedText by remember {
        mutableStateOf(
            if (isEditMode) {
                product.purchasePrice.toString().addCurrency()
            } else {
                "R$ 0.00"
            },
        )
    }
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
            purchasePriceSelectedText = it.removeCurrency()
        },
        onDone = {
            purchasePriceSelectedText = purchasePriceSelectedText.removeCurrency().addCurrency()
        },
    )

    // Sale Price
    var salePriceSelectedText by remember {
        mutableStateOf(
            if (isEditMode) {
                product.salePrice.toString().addCurrency()
            } else {
                "R$ 0.00"
            },
        )
    }
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
            salePriceSelectedText = it.removeCurrency()
        },
        onDone = {
            salePriceSelectedText = salePriceSelectedText.removeCurrency().addCurrency()
        },
    )
}

fun String.removeCurrency() = this.replace("R$", "")

fun String.addCurrency() = "R$ ${
    String.format("%.2f", this.replace(",", ".").toDouble())
        .replace(".", ",")
}"

private fun String.setTitle(editMode: Boolean) =
    if (editMode) "$this | EDIÇÃO" else "$this | CRIAÇÃO"
