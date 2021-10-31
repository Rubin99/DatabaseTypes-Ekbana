package com.example.databasetypes.roomdatabase

import androidx.lifecycle.LiveData

class MovieRepository(private val movieDao: MovieDao) {

    val getAll: LiveData<List<Movie>> = movieDao.getAll()

    suspend fun addMovie(movie: Movie) {
        movieDao.insert(movie)
    }

    suspend fun updateMovie(movie: Movie) {
        movieDao.updateMovie(movie)
    }

    suspend fun deleteMovie(movie: Movie) {
        movieDao.delete(movie)
    }

    suspend fun deleteAllMovies() {
        movieDao.deleteAll()
    }
}