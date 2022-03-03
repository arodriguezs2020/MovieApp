package com.example.movieapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.data.model.MovieEntity

// ---
//
//      Creamos la Database y le creamos una instancia de la base de datos:
//      Y ponemos dos metodos, uno para devolver la base de datos y otra para destruirla
//
// ---

@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "movie_table"
            ).build()
            return INSTANCE!!
        }
/* Esta función nos sirve para borrar la base de datos SQLite

        fun destroyInstance() {
            INSTANCE = null
        }

 */
    }
}