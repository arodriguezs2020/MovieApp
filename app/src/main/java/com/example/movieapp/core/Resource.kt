package com.example.movieapp.core

import java.lang.Exception

// ---
//
//    Esta clase lo que hace es decir los tres estados que se pueden dar a la hora de llamar a una API:
//         1. Loading: Para cuando esta cargando los datos
//         2. Success: Para cuando nos trae sin errores
//         3. Failure: Para cuando nos da un error a la hora de traer los datos de la API
//
// ---

sealed class Resource<out T> {
    class Loading<out T> : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure(val exception: Exception) : Resource<Nothing>()
}