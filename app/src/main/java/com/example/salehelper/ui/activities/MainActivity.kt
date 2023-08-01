package com.example.salehelper.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.salehelper.ui.components.DropDownComponent
import com.example.salehelper.ui.theme.Purple700
import com.example.salehelper.ui.theme.SaleAdvisorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SaleHelperApp()
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SaleHelperApp() {
    SaleAdvisorTheme {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen = screens.find { it.route == currentDestination?.route } ?: HomeScreen

        var mExpanded by remember { mutableStateOf(false) }
        val mCities =
            listOf("Delhi", "Mumbai", "Chennai", "Kolkata", "Hyderabad", "Bengaluru", "Pune")
        var mSelectedText by remember { mutableStateOf("") }
        var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier
                        .background(Purple700)
                        .padding(12.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Icon(
                        Icons.Rounded.Home,
                        contentDescription = null,
                        tint = Color.White,
                    )

                    Text(
                        text = "Alura",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 22.sp,
                    )

                    DropDownComponent()
                }
            },
        ) {
            // todo - content of the nagigation
        }
    }
}





























@Composable
fun Menu(
    allScreens: List<SaleHelperDestination>,
    onTabSelected: (SaleHelperDestination) -> Unit,
    currentScreen: SaleHelperDestination,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Button(
            modifier = Modifier.width(50.dp),
            onClick = {
                onTabSelected(HomeScreen)
            },
        ) {
            Text("homescreen")
        }
        Button(
            modifier = Modifier.width(50.dp),
            onClick = {
                onTabSelected(RegisterScreen)
            },
        ) {
            Text("registerscreen")
        }
        Button(
            modifier = Modifier.width(50.dp),
            onClick = {
                onTabSelected(TransactionScreen)
            },
        ) {
            Text("transactionscreen")
        }
        Button(
            modifier = Modifier.width(50.dp),
            onClick = {
                onTabSelected(RegisterTransactionScreen)
            },
        ) {
            Text("registertransactionscreen")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SaleHelperApp()
}

@Composable
fun Dropdown(
    mExpanded: Boolean,
    mCities: List<String>,
    mTextFieldSize: Size,
    onDismissRequest: () -> Unit = {},
    onDropdownMenuItemClicked: (String) -> Unit = {},
) {
    Column {
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = {
                onDismissRequest()
            },
            modifier = Modifier
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() }),
        ) {
            mCities.forEach { label ->
                DropdownMenuItem(
                    onClick = {
                        onDropdownMenuItemClicked(label)
                    },
                    content = {
                        Column {
                            Text(
                                text = label,
                                fontSize = 16.sp,
                            )
                        }
                    },
                )
            }
        }
    }
}

// Creating a composable function
// to create an Outlined Text Field
// Calling this function as content
// in the above function
/*    @Composable
    fun MyContent() {
        // Declaring a boolean value to store
        // the expanded state of the Text Field
        var mExpanded by remember { mutableStateOf(false) }

        // Create a list of cities
        val mCities =
            listOf("Delhi", "Mumbai", "Chennai", "Kolkata", "Hyderabad", "Bengaluru", "Pune")

        // Create a string value to store the selected city
        var mSelectedText by remember { mutableStateOf("") }

        var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

        // Up Icon when expanded and down icon when collapsed
        val icon = if (mExpanded) {
            Icons.Filled.KeyboardArrowUp
        } else {
            Icons.Filled.KeyboardArrowDown
        }

        Column(Modifier.padding(top = 70.dp)) {
            // Create an Outlined Text Field
            // with icon and not expanded
            OutlinedTextField(
                value = mSelectedText,
                onValueChange = { mSelectedText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        // This value is used to assign to
                        // the DropDown the same width
                        mTextFieldSize = coordinates.size.toSize()
                    },
                label = { Text("Label") },
                trailingIcon = {
                    Icon(
                        icon,
                        "contentDescription",
                        Modifier.clickable { mExpanded = !mExpanded },
                    )
                },
            )

            // Create a drop-down menu with list of cities,
            // when clicked, set the Text Field text as the city selected
            DropdownMenu(
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() }),
            ) {
                mCities.forEach { label ->
                    DropdownMenuItem(onClick = {
                        mSelectedText = label
                        mExpanded = false
                    }) {
                        Text(text = label)
                    }
                }
            }
        }
    }*/

/*
    *//*            drawerContent = {
                MyContent()
                *//**//*Menu(
                    allScreens = screens,
                    onTabSelected = { newScreen ->
                        navController.navigate(newScreen.route)
                    },
                    currentScreen = currentScreen,
                )*//**//*
            }*//*
    { innerPadding ->
        RallyNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
        )
    }*/

