package com.example.movieapp.data.local

import com.example.movieapp.data.model.MovieEntity
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.data.model.toMovieList

// ---
//
//      Esta clase coge una instancia de la lase MovieDao y nos muestra tres metodos:
//          1. getUpcomingMovies(): Este metodo nos filtra los datos que nos llegan del DAO por "upcoming"
//          2. getTopRatedMovies(): Este metodo nos filtra los datos que nos llegan del DAO por "toprated"
//          3. getPopularMovies():  Este metodo nos filtra los datos que nos llegan del DAO por "popular"
//
// ---

class LocalMovieDataSource(private val movieDao: MovieDao) {

    suspend fun getUpcomingMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "upcoming" }.toMovieList()
    }

    suspend fun getTopRatedMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "toprated" }.toMovieList()
    }

    suspend fun getPopularMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "popular" }.toMovieList()
    }

    suspend fun saveMovie(movie: MovieEntity) {
        movieDao.saveMovie(movie)
    }
}