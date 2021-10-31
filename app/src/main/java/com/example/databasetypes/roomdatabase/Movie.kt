package com.example.databasetypes.roomdatabase

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "movie_list")
data class Movie(
    @PrimaryKey
    val id: String,
    //val movieUrl: String,
    val movieName: String,
    val rating1TV: Int,
    val rating2TV: Int,
    val ratingMetascoreTV: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(movieName)
        parcel.writeInt(rating1TV)
        parcel.writeInt(rating2TV)
        parcel.writeInt(ratingMetascoreTV)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}