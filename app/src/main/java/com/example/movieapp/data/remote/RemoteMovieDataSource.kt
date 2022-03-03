package com.example.movieapp.data.remote

import com.example.movieapp.application.AppConstants
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.repository.WebService

// ---
//
//      Creamos el MovieDataSource con los tres metodos que se conectan con la interfaz WebService
//      y a la cual le pasamos la API_KEY para la llamada
//
// ---

class RemoteMovieDataSource(private val webService: WebService) {

    suspend fun getUpcomingMovies(): MovieList = webService.getUpcomingMovies(AppConstants.API_KEY)

    suspend fun getTopRatedMovies(): MovieList = webService.getTopRatedMovies(AppConstants.API_KEY)

    suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(AppConstants.API_KEY)

}