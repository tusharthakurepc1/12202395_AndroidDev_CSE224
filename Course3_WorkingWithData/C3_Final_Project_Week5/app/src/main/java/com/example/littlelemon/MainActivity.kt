package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                // add databaseMenuItems code here
                val databaseMenuItems = database.menuItemDao().getAll().observeAsState(emptyList())
                // add orderMenuItems variable here
                var orderMenuItems by remember {
                    mutableStateOf(false)
                }
                // add menuItems variable here
                var menuItems = if (orderMenuItems) {
                    databaseMenuItems.value.sortedBy { it.title }
                } else {
                    databaseMenuItems.value
                }

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "logo",
                        modifier = Modifier.padding(50.dp)
                    )
                    // add Button code here
                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp),
                        onClick = {
                            orderMenuItems = !orderMenuItems
                        }) {
                        Text(text = "Tap to Order By Name")
                    }
                    // add searchPhrase variable here
                    var searchPhrase by remember {
                        mutableStateOf("")
                    }
                    // Add OutlinedTextField
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 50.dp),
//                            .border(BorderStroke(2.dp, Color.Black))

                        value = searchPhrase,
                        onValueChange = { newText ->
                            searchPhrase = newText
                        },
                        label = { Text(text = "Search") },
                    )
                    // add is not empty check here
                    if (searchPhrase.isNotEmpty()) {
                        menuItems = menuItems.filter {
                            return@filter it.title.contains(searchPhrase, ignoreCase = true)
                        }
                    }


                    MenuItemsList(items = menuItems)
                }
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuItemDao().isEmpty()) {
                // add code here
                val menuList = fetchMenu()
                saveMenuToDatabase(menuList)

            }
        }
    }

    private suspend fun fetchMenu(): List<MenuItemNetwork> {
        val menUrl =
            "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/littleLemonSimpleMenu.json"
        val response = httpClient.get(menUrl).body<MenuNetwork>()
        return response.mensList
    }

    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }
}

@Composable
private fun MenuItemsList(items: List<MenuItemRoom>) {

    val mensList: List<MenuItemRoom> = listOf(
        MenuItemRoom(1, "Spinach Artichoke Dip", 10.00),
        MenuItemRoom(2, "Hummus", 10.40),
        MenuItemRoom(3, "Fried Calamari Rings", 51.00),
        MenuItemRoom(4, "Fried Mushroom", 12.99),
        MenuItemRoom(5, "Greek", 7.23),
        MenuItemRoom(6, "Caesar", 7.23),
        MenuItemRoom(7, "Mediterranean Tuna Salad", 10.00),
        MenuItemRoom(8, "Grilled Chicken Salad", 12.11),
        MenuItemRoom(9, "Water", 3.5),
        MenuItemRoom(10, "Coke", 3.67),
        MenuItemRoom(11, "Beer", 7.89),
        MenuItemRoom(12, "Iced Tea", 3.5),
    )

    var iter:Int = 0;
    Column(

    ) {
        repeat(mensList.size){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(mensList.get(iter).title)
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp),
                    textAlign = TextAlign.Right,
                    text = mensList.get(iter).price.toString()
                )
                iter++;
            }
        }
    }


}
