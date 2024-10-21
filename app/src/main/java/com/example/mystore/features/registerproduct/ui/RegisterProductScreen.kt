package com.example.mystore.features.registerproduct.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mystore.R
import com.example.mystore.features.registerproduct.model.Product
import com.example.mystore.features.registerproduct.viewmodel.RegisterProductViewModel
import com.example.mystore.setQuantifierSize
import com.example.mystore.ui.components.commons.AlertDialogComponent
import com.example.mystore.ui.components.commons.FloatingActionButton
import com.example.mystore.ui.components.commons.OutLinedTextFieldComponent
import com.example.mystore.ui.components.commons.Quantifier
import com.example.mystore.ui.components.commons.ScreenSectionComponent
import com.example.mystore.ui.components.commons.ShowAlertDialogComponent
import com.example.mystore.ui.components.commons.TextFormattedComponent
import com.example.mystore.ui.components.commons.ToastComponent
import com.example.mystore.ui.components.commons.getPainter

@Composable
fun RegisterProductScreen(
    product: Product = Product(),
    isEditMode: Boolean,
    registerProductViewModel: RegisterProductViewModel,
    onClearStates: (Boolean) -> Unit,
    onNavigateToHome: () -> Unit,
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
        setProduct(product)

        val productState by this.registerProductViewState.product.collectAsState()
        val listOfProducts by this.commonViewState.listOfProducts.collectAsState()
        val screenWidth by this.registerProductViewState.screenWidth.collectAsState()
        val titleSelectedText by this.registerProductViewState.titleSelectedText.collectAsState()
        val descriptionSelectedText by this.registerProductViewState.descriptionSelectedText.collectAsState()
        val purchasePriceSelectedText by this.registerProductViewState.purchasePriceSelectedText.collectAsState()
        val salePriceSelectedText by this.registerProductViewState.salePriceSelectedText.collectAsState()
        val quantity by this.registerProductViewState.quantity.collectAsState()
        val maxQuantityToBuy by this.registerProductViewState.maxQuantityToBuy.collectAsState()
        val showAlertDialogProductScreen by this.registerProductViewState.showAlertDialogProductScreen.collectAsState()
        val showAlertDialogImageUrl by this.registerProductViewState.showAlertDialogImageUrl.collectAsState()
        val showToastProductScreen by this.registerProductViewState.showToastProductScreen.collectAsState()

        ScreenSectionComponent(
            title = stringResource(id = R.string.my_store_product_2).setTitle(isEditMode),
            body = {
                RegisterProductScreenBody(
                    registerProductScreenBodyProps = RegisterProductScreenBodyProps(
                        product = productState,
                        isEditMode = isEditMode,
                        registerProductViewModel = registerProductViewModel,
                        screenWidth = screenWidth,
                        titleSelectedText = titleSelectedText,
                        descriptionSelectedText = descriptionSelectedText,
                        purchasePriceSelectedText = purchasePriceSelectedText,
                        salePriceSelectedText = salePriceSelectedText,
                        quantity = quantity,
                        maxQuantityToBuy = maxQuantityToBuy,
                        showAlertDialogProductScreen = showAlertDialogProductScreen,
                        showAlertDialogImageUrl = showAlertDialogImageUrl,
                        showToastProductScreen = showToastProductScreen,
                        onNavigateToHome = { onNavigateToHome() },
                        listOfProducts = listOfProducts
                    ),
                )
                onClearStates(false)
            },
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun RegisterProductScreenBody(
    registerProductScreenBodyProps: RegisterProductScreenBodyProps
) {
    with(registerProductScreenBodyProps) {
        if (showToastProductScreen) {
            ToastComponent(
                stringResource(
                    id = if (isEditMode) {
                        R.string.my_store_successful_edit
                    } else {
                        R.string.my_store_successful_registry
                    },
                ),
            )
            onNavigateToHome()
        }

        // AlertDialog with Url field to load an image from the net.
        if (showAlertDialogImageUrl) {
            AlertDialogComponent(
                color = colorResource(id = R.color.color_transaparent),
                size = setSize(),
                content = {
                    ScreenSectionComponent(
                        title = stringResource(id = R.string.my_store_image_product),
                        textColor = R.color.color_500,
                        backgroundColor = R.color.white,
                        body = {
                            Column(modifier = Modifier.fillMaxSize()) {
                                ImageUrlBody(
                                    registerProductViewModel = registerProductViewModel,
                                    imageUrl = product.imageUrl,
                                    onImageUrl = { imageUrl ->
                                        registerProductViewModel.setImageUrl(product, imageUrl)
                                    },
                                )
                            }
                        },
                    )
                },
                onDismissRequest = { registerProductViewModel.setShowAlertDialogImageUrl(false) },
            )
        }

        // AlertDialog with product creation or edition
        ShowAlertDialogComponent(
            showAlert = showAlertDialogProductScreen,
            title = setAlertDialogTitle(),
            alertDialogMessage = setAlertDialogMessage(),
            onDismissRequest = { registerProductViewModel.setShowAlertDialogProductScreen(false) },
            onDismissButtonClicked = {
                registerProductViewModel.setShowAlertDialogProductScreen(
                    false
                )
            },
            onConfirmButtonClicked = {
                registerProductViewModel.getAllProducts()
                registerProductViewModel.saveProduct(
                    product = Product(
                        productId = setProductId(isEditMode, listOfProducts, product),
                        title = titleSelectedText,
                        description = descriptionSelectedText,
                        purchasePrice = purchasePriceSelectedText.replaceCommaFromValue()
                            .toDouble(),
                        salePrice = salePriceSelectedText.replaceCommaFromValue().toDouble(),
                        quantity = quantity,
                        maxQuantityToBuy = maxQuantityToBuy,
                        imageUrl = product.imageUrl,
                    ),
                    isEditMode = isEditMode,
                )
                registerProductViewModel.setShowToastProductScreen(true)
                registerProductViewModel.setShowAlertDialogProductScreen(false)
                registerProductViewModel.getAllProducts()
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
            imageUrl = product.imageUrl,
            onProductClick = { registerProductViewModel.setShowAlertDialogImageUrl(true) },
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
                    purchasePriceSelectedText.removeCurrencyToProductValue(),
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
                    salePriceSelectedText.removeCurrencyToProductValue(),
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
                    enabled = !isEditMode,
                    shouldStartWithZero = true,
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
                    shouldStartWithZero = true,
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
                    colorId = R.color.color_800,
                    onClick = { registerProductViewModel.setShowAlertDialogProductScreen(true) },
                )
            }
        }
    }
}

@Composable
private fun RegisterProductScreenBodyProps.setAlertDialogMessage() =
    stringResource(
        if (isEditMode) R.string.my_store_edition_confirmation
        else R.string.my_store_creation_confirmation
    )

@Composable
private fun RegisterProductScreenBodyProps.setAlertDialogTitle() =
    if (isEditMode) {
        stringResource(R.string.my_store_registry_update)
    } else {
        stringResource(R.string.my_store_registry_creation)
    }

@Composable
private fun setSize() = Size(
    width = LocalConfiguration.current.screenWidthDp.dp.value * 1f,
    height = LocalConfiguration.current.screenHeightDp.dp.value * 0.47f,
)

private fun setProductId(
    isEditMode: Boolean,
    listOfProducts: List<Product>,
    product: Product,
) = if (!isEditMode) listOfProducts.size + 1 else product.productId


@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun ImageUrlBody(
    registerProductViewModel: RegisterProductViewModel,
    onImageUrl: (imageUrl: String) -> Unit = {},
    imageUrl: String,
) {
    val titleKeyboardController = LocalSoftwareKeyboardController.current
    val titleFocusManager = LocalFocusManager.current
    var imageUrlInternal by remember { mutableStateOf(imageUrl) }

    Column {
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
            imageUrl = imageUrlInternal,
            onProductClick = { registerProductViewModel.setShowAlertDialogImageUrl(true) },
        )

        OutLinedTextFieldComponent(
            selectedText = imageUrlInternal,
            label = stringResource(id = R.string.my_store_image_url),
            keyboardController = titleKeyboardController,
            focusManager = titleFocusManager,
            transactionDetailColors = Triple(
                R.color.color_500,
                R.color.color_500,
                R.color.white,
            ),
            onValueChanged = { imageUrlInternal = it },
        )

        Row {
            Button(
                modifier = Modifier.padding(start = 8.dp, end = 16.dp),
                onClick = {
                    onImageUrl(imageUrlInternal)
                    registerProductViewModel.setShowAlertDialogImageUrl(false)
                },
            ) {
                Text(
                    text = stringResource(id = R.string.my_store_confirmation),
                    color = colorResource(id = R.color.color_50),
                )
            }

            Button(
                modifier = Modifier.padding(start = 8.dp, end = 16.dp),
                onClick = {
                    registerProductViewModel.setShowAlertDialogImageUrl(false)
                },
            ) {
                Text(
                    text = stringResource(id = R.string.my_store_cancelation),
                    color = colorResource(id = R.color.color_50),
                )
            }
        }
    }
}

@Composable
fun ImageSection(
    modifier: Modifier = Modifier,
    registerProductViewModel: RegisterProductViewModel,
    imageUrl: String = "",
    onProductClick: () -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.background(colorResource(id = R.color.color_800)),
    ) {
        ImageComponent(
            modifier = modifier
                .fillMaxWidth()
                .height(100.dp),
            painterResource = getPainter(
                imageUrl = imageUrl,
                onImageRequestState = {
                    registerProductViewModel.setImageRequestState(it)
                },
            ),
            onProductClick = { onProductClick() },
        )
    }
}

private fun String.setTitle(editMode: Boolean) =
    if (editMode) "$this | EDIÇÃO" else "$this | CRIAÇÃO"

private fun String.removeCurrencyToProductValue() = this.replace("R$", "")

private fun String.replaceCommaFromValue() = this.replace(",", ".")

private fun String.addCurrencyToProductValue() = "R$ ${
    String.format("%.2f", this.replace(",", ".").toDouble())
        .replace(".", ",")
}"
