package com.example.movieapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// ---
//
//      Creamos el Modelo de los datos que vamos a recoger de la API:
//          1. Primero creamos la Movie con todos los datos
//          2. Creamos la MovieList para almacenar una lista de esas Movies
//
// ---

data class Movie(
    val id: Int = -1,
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val original_language: String = "",
    val overview: String = "",
    val popularity: Double = -1.0,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = -1.0,
    val vote_count: Int = -1,
    val movie_type: String = ""
)

data class MovieList(val results: List<Movie> = listOf())

// ---> ROOM <--- //


// --- Con este metodo creamos la tabla de la base de datos --- //
@Entity
data class MovieEntity(
    @PrimaryKey
    val id: Int = -1,
    @ColumnInfo(name = "adult")
    val adult: Boolean = false,
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String = "",
    @ColumnInfo(name = "original_language")
    val original_language: String = "",
    @ColumnInfo(name = "overview")
    val overview: String = "",
    @ColumnInfo(name = "popularity")
    val popularity: Double = -1.0,
    @ColumnInfo(name = "poster_path")
    val poster_path: String = "",
    @ColumnInfo(name = "release_date")
    val release_date: String = "",
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "video")
    val video: Boolean = false,
    @ColumnInfo(name = "vote_average")
    val vote_average: Double = -1.0,
    @ColumnInfo(name = "vote_count")
    val vote_count: Int = -1,
    @ColumnInfo(name = "movie_type")
    val movie_type: String = ""
)

// --- Con este metodo trasformamos una List<MovieEntity> en una MovieList --- //
fun List<MovieEntity>.toMovieList(): MovieList {
    val resultList = mutableListOf<Movie>()
    this.forEach { movieEntity ->
        resultList.add(movieEntity.toMovie())
    }
    return MovieList(resultList)
}

// --- Creamos una funcion para trasformar un MovieEntity a una Movie --- //
fun MovieEntity.toMovie(): Movie = Movie(
    this.id,
    this.adult,
    this.backdrop_path,
    this.original_language,
    this.overview,
    this.popularity,
    this.poster_path,
    this.release_date,
    this.title,
    this.video,
    this.vote_average,
    this.vote_count,
    this.movie_type
)

// --- Función auxiliar para trasformar una Movie en una MovieEntity --- //
fun Movie.toMovieEntity(movieType: String): MovieEntity = MovieEntity(
    this.id,
    this.adult,
    this.backdrop_path,
    this.original_language,
    this.overview,
    this.popularity,
    this.poster_path,
    this.release_date,
    this.title,
    this.video,
    this.vote_average,
    this.vote_count,
    movie_type = movieType
)