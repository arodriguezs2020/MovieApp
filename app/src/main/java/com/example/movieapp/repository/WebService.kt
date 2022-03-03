package com.example.movieapp.repository

import com.example.movieapp.application.AppConstants
import com.example.movieapp.data.model.MovieList
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// ---
//
//      Creamos la interfaz WebService que utilizando Retrofit hará las llamadas al Servidor
//
// ---

interface WebService {
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query ("api_key") apiKey: String): MovieList
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query ("api_key") apiKey: String): MovieList
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query ("api_key") apiKey: String): MovieList
}


// ---
//
//      Creamos el objeto RetrofitClient donde contruiremos el webservice y pasando la información
//      que nos llega en Gson a la clase Movie.
//
// ---

object RetrofitClient {
    val webservice: WebService by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}