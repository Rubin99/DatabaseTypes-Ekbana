package com.example.databasetypes.roomdatabase

import android.os.Parcelable
import android.widget.ImageView
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "movie_list")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val movieUrl: String,
    val movieName: String,
    val rating1TV: Double,
    val rating2TV: Int,
    val ratingMetascoreTV: Int
)