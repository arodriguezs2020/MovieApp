package com.example.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.Resource
import com.example.movieapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieViewModel(private val repo: MovieRepository) : ViewModel() {

    // --- Creamos un metodo que nos va a devolver tres llamadas a la API, este hilo se eliminara
    // cuando el viewModel se elimine y en el hilo principal --- //
    fun fetchMainScreenMovies() = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        // Con esta llamada le decimos que esta cargando los datos
        emit(Resource.Loading())

        try {
            // Con esta llamada le decimos si ha salido bien la llamada
            emit(Resource.Success(Triple(repo.getUpcomingMovies(), repo.getTopRatedMovies(), repo.getPopularMovies())))
        } catch (e: Exception) {
            // Con esta llamada le decimos si ha habido algún error en la llamada a la API
            emit(Resource.Failure(e))
        }
    }
}

// Este metodo Factory es muy necesario para la llamada del ViewModel en el MovieFragment
class MovieViewModelFactory(private val repo: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}