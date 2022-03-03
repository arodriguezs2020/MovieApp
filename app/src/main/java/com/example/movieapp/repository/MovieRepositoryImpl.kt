package com.example.movieapp.repository

import com.example.movieapp.core.InternetCheck
import com.example.movieapp.data.local.LocalMovieDataSource
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.data.model.toMovieEntity
import com.example.movieapp.data.remote.RemoteMovieDataSource

// ---
//
//      Creamos la clase que va a implementar la interfaz MovieRepository y que devolverá los datos que
//      le lleguen del servidor
//
// ---

class MovieRepositoryImpl(
    private val dataSourceRemote: RemoteMovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource
) : MovieRepository {
    override suspend fun getUpcomingMovies(): MovieList {
        return if (InternetCheck.isNetworkAvalable()) {
            dataSourceRemote.getUpcomingMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
            }
            dataSourceLocal.getUpcomingMovies()
        } else {
            dataSourceLocal.getUpcomingMovies()
        }
    }

    override suspend fun getTopRatedMovies(): MovieList {
        return if (InternetCheck.isNetworkAvalable()) {
            dataSourceRemote.getTopRatedMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("toprated"))
            }
            dataSourceLocal.getTopRatedMovies()
        } else {
            dataSourceLocal.getTopRatedMovies()
        }
    }

    override suspend fun getPopularMovies(): MovieList {
        return if (InternetCheck.isNetworkAvalable()) {
            dataSourceRemote.getPopularMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
            }
            dataSourceLocal.getPopularMovies()
        } else {
            dataSourceLocal.getPopularMovies()
        }
    }
}