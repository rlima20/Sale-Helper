package com.example.mystore.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mystore.R
import com.example.mystore.ui.theme.MyStoreTheme

@Suppress("DEPRECATION")
@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java).apply {
            }
            startActivity(intent)
            finish()
        }, 2000)

        setContent {
            SplashComponent()
        }
    }

    @Composable
    private fun SplashComponent() {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.mcgpalette0_300)),
            color = colorResource(id = R.color.mcgpalette0_300),
        ) {
            Box(
                contentAlignment = androidx.compose.ui.Alignment.Center,
                modifier = Modifier.size(500.dp),
            ) {
                Image(
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(280.dp)
                        .background(color = colorResource(id = R.color.mcgpalette0_300)),
                    painter = painterResource(id = R.drawable.my_store_launcher_image),
                    contentDescription = null,
                )
            }
        }
    }

    @Preview
    @Composable
    fun SplashPreview() {
        MyStoreTheme {
            SplashComponent()
        }
    }
}
