package com.example.databasetypes.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface  MovieDao {
    @Query("SELECT * FROM movie_list ORDER BY ratingMetascoreTV ASC")
    fun getByRating(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie_list")
    fun getAll(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("DELETE FROM movie_list")
    suspend fun deleteAll()

    @Update
    suspend fun updateMovie(movie: Movie)
}