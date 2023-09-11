package com.company.kotlin_project

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.GridCells
//import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
//import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductsGrid(products: Products) {
    val mContext = LocalContext.current
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(top = 10.dp, bottom = 20.dp),

    ) {
        items(
            items = products.items,
            itemContent = { productItem: ProductItem ->
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .width(250.dp)
                        .height(200.dp)
//                        .border(2.dp, Color.Black)
                        .padding(top = 12.dp, bottom = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = productItem.image),
                        contentDescription = productItem.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.clickable {
                            //TODO start ProductActivity and pass the productItem details

                            var intent = Intent(mContext, ProductDESC::class.java)
                            intent.putExtra("title", productItem.title.toString())
                            intent.putExtra("price", productItem.price)
                            intent.putExtra("category", productItem.category.toString())
                            intent.putExtra("img", productItem.image)

                            mContext.startActivity(intent)
//                            mContext.startActivity(Intent(mContext, ProductDESC::class.java))
                        }
                            .width(150.dp)
                            .clip(RoundedCornerShape(10.dp))

                    )

                    Text(text = productItem.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                }
            },

        )
    }
}