package com.example.mystore.ui.model

import androidx.compose.ui.geometry.Size

data class TopBarComponentProps(
    val visualProperties: VisualProperties,
    val menuActions: MenuActions,
    val dropdownMenuProperties: DropdownMenuProperties
)

// Propriedades visuais relacionadas ao componente de TopBar
data class VisualProperties(
    var screenTitle: String,
    val isIconVisible: Boolean = true,
    val isIconLined: Boolean,
    val onIconVisibilityClicked: () -> Unit
)

// Ações relacionadas ao comportamento do menu
data class MenuActions(
    val isMenuExpanded: Boolean,
    val onMenuIconClicked: () -> Unit,
    val onDismissRequest: () -> Unit
)

// Propriedades e ações do menu suspenso (dropdown)
data class DropdownMenuProperties(
    val dropdownMenuWidth: Size,
    val onDropDownMenuItemClicked: (screen: String) -> Unit,
    val onChangeDropdownMenuWidth: (size: Size) -> Unit
)