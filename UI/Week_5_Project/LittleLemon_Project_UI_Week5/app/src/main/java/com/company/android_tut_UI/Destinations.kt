package com.company.android_tut_UI

interface Destinations {
    val route: String
}

object Home : Destinations {
    override val route = "Home"
}

object DishDetails : Destinations {
    override val route = "Menu"
    const val argDishId = "dishId"
}
