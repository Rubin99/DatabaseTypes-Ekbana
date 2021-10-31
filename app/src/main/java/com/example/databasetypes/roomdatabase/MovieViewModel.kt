package com.example.databasetypes.roomdatabase

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    val getAllMovies: LiveData<List<Movie>>
    private val repository: MovieRepository

    init {
        val movieDao = MovieRoomDatabase.getDatabase(application).movieDao()
        repository = MovieRepository(movieDao)
        getAllMovies =repository.getAll
    }

    fun addMovie(movie: Movie){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMovie(movie)
        }
    }
    fun updateMovie(movie: Movie){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMovie(movie)
        }
    }
    fun deleteMovie(movie: Movie){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMovie(movie)
        }
    }
    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllMovies()
        }
    }

}