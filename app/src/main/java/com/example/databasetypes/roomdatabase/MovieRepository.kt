package com.example.databasetypes.roomdatabase

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val movieDao: MovieDao) {

    val allMoviesByRating: Flow<List<Movie>> = movieDao.getByRating()
    val allMovies: Flow<List<Movie>> = movieDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(movie: Movie){
        movieDao.insert(movie)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(movie: Movie){
        movieDao.delete(movie)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll(){
        movieDao.deleteAll()
    }
}