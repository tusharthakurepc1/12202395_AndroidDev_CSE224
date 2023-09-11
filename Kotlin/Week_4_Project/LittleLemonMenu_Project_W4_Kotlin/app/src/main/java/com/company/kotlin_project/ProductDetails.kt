package com.company.kotlin_project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
//import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductDetails(productItem: ProductItem) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = productItem.image),
            contentDescription = productItem.title,
            modifier = Modifier.size(300.dp)
                .clip(RoundedCornerShape(5.dp))
        )
        Text(text = productItem.title,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
//            modifier = Modifier.padding(start = 70.dp)
//                .fillMaxWidth()
        )
        Text(text = "Category: ${productItem.category}",
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 70.dp, top = 15.dp)
                .fillMaxWidth()
        )
        Text(text = "Price: $${productItem.price}",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 70.dp)
                .fillMaxWidth()
        )
    }
}