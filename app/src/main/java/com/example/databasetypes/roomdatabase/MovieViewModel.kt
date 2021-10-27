package com.example.databasetypes.roomdatabase

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    val allMovies: LiveData<List<Movie>> = repository.allMovies.asLiveData()
    val allMoviesByRating: LiveData<List<Movie>> = repository.allMoviesByRating.asLiveData()

    fun insert(movie: Movie) = viewModelScope.launch {
        repository.insert(movie)
    }

    fun delete(movie: Movie) = viewModelScope.launch {
        repository.delete(movie)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    class MovieViewModelFactory(private val repository: MovieRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MovieViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }

}