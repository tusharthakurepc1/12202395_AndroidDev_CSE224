package com.company.kotlin_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.company.kotlin_project.ui.theme.Kotlin_ProjectTheme

class ProductDESC : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val productItem = ProductItem(intent.getStringExtra("title").toString(),
            intent.getDoubleExtra("price", 0.0),
            intent.getStringExtra("category").toString(),
            intent.getIntExtra("img", -1));

        setContent {
            ProductDetails(productItem = productItem);
        }
    }
}
