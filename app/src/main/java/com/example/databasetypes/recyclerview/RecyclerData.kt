package com.example.databasetypes.recyclerview

data class RecyclerData (
    val movieNameTV: String,
    val rating1TV: Double,
    val rating2TV: Int,
    val ratingMetascoreTV: Int,
    var bookmarked: Boolean,
    var imageUrl: String
)