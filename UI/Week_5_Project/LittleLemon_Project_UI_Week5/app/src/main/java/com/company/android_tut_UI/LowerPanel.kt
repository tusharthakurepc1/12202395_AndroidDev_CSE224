package com.company.android_tut_UI


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import java.nio.file.WatchEvent

@Composable
fun LowerPanel(navController: NavHostController, dishes: List<Dish> = listOf()) {
    Column(

    ) {
        WeeklySpecialCard()
        LazyColumn (
        ){
            itemsIndexed(dishes) { _, dish ->
                MenuDish(navController, dish)
            }
        }
    }
}

@Composable
fun WeeklySpecialCard() {
    Row(
        modifier = Modifier
//            .height(1000.dp)
            .background(Color.White)
    ) {
        Text(
            text = stringResource(R.string.weekly_special),
//            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .padding(8.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuDish(navController: NavHostController? = null, dish: Dish) {
    Card(onClick = {
        Log.d("AAA", "Click ${dish.id}")
        navController?.navigate(DishDetails.route + "/${dish.id}")
    },
    ) {
        Box(
            modifier = Modifier
                .background(Color.White)
                .height(170.dp),

        ) {

            Row(
                Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
                    .fillMaxWidth()
            ){
                Column (
                    Modifier.padding(start = 20.dp)
                ){
                    Text(text = "${dish.name}",
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 5.dp),

                        )

                    Text(text = "${dish.description}",
                        Modifier
                            .width(230.dp)
                            .padding(end = 20.dp),
                        fontSize = 15.sp,
                        lineHeight = 20.sp,

                    )

                    Text(text = "$${dish.price}",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,

                        )
                }

                Box(
                    Modifier.width(150.dp)
                ){
                    Image(painter = painterResource(id = dish.imageResource),
                        contentDescription = "Image dish",
                        Modifier.clip(RoundedCornerShape(10.dp))
                        )
                }
            }
        }
    }
    Divider(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        thickness = 1.dp,
    )
}
