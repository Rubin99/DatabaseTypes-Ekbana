package com.example.databasetypes.roomdatabase

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface MovieDao {
    @Query("SELECT * FROM movie_list ORDER BY ratingMetascoreTV ASC")
    fun getByRating(): Flow<List<Movie>>

    @Query("SELECT * FROM movie_list")
    fun getAll(): Flow<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("DELETE FROM movie_list")
    suspend fun deleteAll()
}