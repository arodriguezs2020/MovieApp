package com.example.movieapp.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.core.BaseConcatHolder
import com.example.movieapp.core.Resource
import com.example.movieapp.data.remote.MovieDataSource
import com.example.movieapp.databinding.FragmentMovieBinding
import com.example.movieapp.presentation.MovieViewModel
import com.example.movieapp.presentation.MovieViewModelFactory
import com.example.movieapp.repository.MovieRepositoryImpl
import com.example.movieapp.repository.RetrofitClient

class MovieFragment: Fragment(R.layout.fragment_movie) {

    // Hacemos una variable del Fragmento
    private lateinit var binding: FragmentMovieBinding
    // Hacemos una variable del viewModel
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImpl(
                MovieDataSource(RetrofitClient.webservice)
            )
        )
    }

    // private lateinit var concatadapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Bindeamos el fragmento con nuestra vista
        binding = FragmentMovieBinding.bind(view)

        // Con esta sentencia estamos controlando cuando esta cargando los datos, cuando los trae
        // correctamente y cuando hay un error
        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when(result) {
                is Resource.Loading -> {
                    Log.d("LiveData", "Loading...")
                }
                is Resource.Success -> {
                    Log.d("LiveData", "Upcoming: ${result.data.first} \n \n TopRated: ${result.data.second} \n \n Popular: ${result.data.third}")
                }
                is Resource.Failure -> {
                    Log.d("Error", "${result.exception}")
                }
            }
        })
    }
}