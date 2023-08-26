package com.example.mystore.ui.components.commons

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.mystore.R

@Composable
fun ToastComponent() {
    Toast.makeText(
        LocalContext.current,
        stringResource(R.string.my_store_successfull_transaction_saved),
        Toast.LENGTH_SHORT,
    ).show()
}
