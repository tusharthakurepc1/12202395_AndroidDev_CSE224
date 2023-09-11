package com.company.kotlin_project

sealed class SortType {
    object Alphabetically : SortType()
    object PriceAsc : SortType()
    object PriceDesc : SortType()
}
