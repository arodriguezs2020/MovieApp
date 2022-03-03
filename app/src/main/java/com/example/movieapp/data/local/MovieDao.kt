package com.example.movieapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.model.MovieEntity

// ---
//
//      Creamos una interfaz DAO con los metodos que vamos a utilizar para interactuar con la base
//      de datos SQLite
//
// ---
@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieEntity")
    suspend fun getAllMovies(): List<MovieEntity>

    // Para resolver el conflicto que puede ser de campos repetidos se utiliza la siguiente consulta
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: MovieEntity)

}