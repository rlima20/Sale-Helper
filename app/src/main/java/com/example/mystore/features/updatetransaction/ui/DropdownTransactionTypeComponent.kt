package com.example.mystore.features.updatetransaction.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mystore.R
import com.example.mystore.enums.TransactionType
import com.example.mystore.toTransactionString
import com.example.mystore.ui.components.commons.DropdownComponent
import com.example.mystore.ui.model.DropdownAppearance
import com.example.mystore.ui.model.DropdownCallbacks
import com.example.mystore.ui.model.DropdownColors
import com.example.mystore.ui.model.DropdownComponentProps
import com.example.mystore.ui.model.DropdownItem
import com.example.mystore.ui.model.DropdownState
import com.example.mystore.ui.model.OutLinedTextFieldComponentProps
import com.example.mystore.ui.model.TextFieldAppearance
import com.example.mystore.ui.model.TextFieldCallbacks

@Composable
fun DropdownTransactionTypeComponent(
    transactionTypeSelected: String,
    textFieldSizeTransaction: Size,
    isDropdownExpanded: Boolean,
    listOfTransactionTypes: List<TransactionType>,
    onSetTextFieldSizeTransaction: (textFieldSize: Size) -> Unit,
    onSetIsDropdownExpanded: (isDropdownExpanded: Boolean) -> Unit,
    onDropdownMenuItemClicked: (selectedTextTransaction: String) -> Unit
) {
    Row(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
    ) {
        DropdownComponent(
            dropdownComponentProps = DropdownComponentProps(
                appearance = DropdownAppearance(
                    textFieldSize = textFieldSizeTransaction,
                    label = stringResource(id = R.string.my_store_transaction_type),
                    modifier = Modifier.fillMaxWidth(),
                    colors = DropdownColors(
                        transactionDetailColors = Triple(
                            R.color.color_500,
                            R.color.color_500,
                            R.color.white,
                        ),
                    )
                ),
                state = DropdownState(
                    isExpanded = isDropdownExpanded,
                    selectedText = transactionTypeSelected.toTransactionString(),
                ),
                items = DropdownItem(
                    items = listOfTransactionTypes.map { it.name },
                ),
                callbacks = DropdownCallbacks(
                    onDropdownMenuDismissRequest = { onSetIsDropdownExpanded(false) },
                    onDropdownMenuItemClicked = { itemSelected ->
                        onDropdownMenuItemClicked(itemSelected.toTransactionString())
                    },
                ),
            ),
            outLinedTextFieldComponentProps = OutLinedTextFieldComponentProps(
                appearance = TextFieldAppearance(
                    label = stringResource(id = R.string.my_store_transaction_type),
                ),
                selectedText = transactionTypeSelected.toTransactionString(),
                focusManager = LocalFocusManager.current,
                callbacks = TextFieldCallbacks(
                    onOutLinedTextFieldSize = { onSetTextFieldSizeTransaction(it) },
                    onOutLinedTextFieldValueChanged = { onDropdownMenuItemClicked(it) },
                    onTrailingIconClicked = {
                        onSetIsDropdownExpanded(!isDropdownExpanded)
                    },
                ),
            )
        )
    }
}