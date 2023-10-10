package com.example.littlelemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuNetwork(
    @SerialName("menu")
    val mensList: List<MenuItemNetwork> = listOf(
        MenuItemNetwork(1, "Spinach Artichoke Dip", 10),
        MenuItemNetwork(2, "Hummus", 10),
        MenuItemNetwork(3, "Fried Calamari Rings", 51),
        MenuItemNetwork(4, "Fried Mushroom", 12),
        MenuItemNetwork(5, "Greek", 7),
        MenuItemNetwork(6, "Caesar", 7),
        MenuItemNetwork(7, "Mediterranean Tuna Salad", 10),
        MenuItemNetwork(8, "Grilled Chicken Salad", 12),
        MenuItemNetwork(9, "Water", 3),
        MenuItemNetwork(10, "Coke", 3),
        MenuItemNetwork(11, "Beer", 7),
        MenuItemNetwork(12, "Iced Tea", 3),
    )
)

@Serializable
data class MenuItemNetwork(
    @SerialName("id")
    val id: Int,

    @SerialName("title")
    val title: String,

    @SerialName("price")
    val price: Int
) {
    fun toMenuItemRoom() = MenuItemRoom(
        id,
        title,
        price.toDouble(),
    )
}




