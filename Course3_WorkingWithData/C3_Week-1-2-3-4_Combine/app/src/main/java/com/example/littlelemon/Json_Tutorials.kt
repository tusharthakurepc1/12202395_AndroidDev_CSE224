package com.example.littlelemon

import android.provider.Settings.Global.getString
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json


fun main() {
    val reservations =
        """
            "date" : "2023/10/5",
            "reservations": [
                {
                    "tablefor": 2,
                    "time": "20:00"
                },
                {   
                    "tablefor": 4,
                    "time": "19:30"
                }
            ]
        """.trimIndent()

    val gson = Gson();

}


fun httpTest(){
    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    val menuItemsLiveData = MutableLiveData<List<String>>()

}

private suspend fun getMenu(category: String): List<String> {
    val response: Map<String, MenuCategory> =
        client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/littleLemonMenu.json")
            .body()

    return response[category]?.menu ?: listOf()
}
