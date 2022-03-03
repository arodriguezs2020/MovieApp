package com.example.movieapp.repository

import com.example.movieapp.data.model.MovieList

// ---
//      Creamos la Interfaz MovieRepository con los metodos que nos servirán para traernos los datos de la API
// ---

interface MovieRepository {
    suspend fun getUpcomingMovies(): MovieList
    suspend fun getTopRatedMovies(): MovieList
    suspend fun getPopularMovies(): MovieList
}