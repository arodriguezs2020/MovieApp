package com.example.movieapp.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.movieapp.R
import com.example.movieapp.core.Resource
import com.example.movieapp.data.local.AppDatabase
import com.example.movieapp.data.local.LocalMovieDataSource
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.data.remote.RemoteMovieDataSource
import com.example.movieapp.databinding.FragmentMovieBinding
import com.example.movieapp.presentation.MovieViewModel
import com.example.movieapp.presentation.MovieViewModelFactory
import com.example.movieapp.repository.MovieRepositoryImpl
import com.example.movieapp.repository.RetrofitClient
import com.example.movieapp.ui.movie.adapters.MovieAdapter
import com.example.movieapp.ui.movie.adapters.concat.PopularConcatAdapter
import com.example.movieapp.ui.movie.adapters.concat.TopRatedConcatAdapter
import com.example.movieapp.ui.movie.adapters.concat.UpcomingConcatAdapter

class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {

    // Hacemos una variable del Fragmento
    private lateinit var binding: FragmentMovieBinding

    // Hacemos una variable del viewModel
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImpl(
                RemoteMovieDataSource(RetrofitClient.webservice),
                LocalMovieDataSource(AppDatabase.getDatabase(requireContext()).movieDao())
            )
        )
    }

    // Creamos una variable con la clase ConcatAdapter
    private lateinit var concatadapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Bindeamos el fragmento con nuestra vista
        binding = FragmentMovieBinding.bind(view)
        concatadapter = ConcatAdapter()

        // Con este metodo estamos controlando cuando esta cargando los datos, cuando los trae
        // correctamente y cuando hay un error
        optimizandoResultadoAPI()
    }

    private fun optimizandoResultadoAPI() {
        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    rellenarConcatAdapter(result)
                    binding.rvMovies.adapter = concatadapter
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun rellenarConcatAdapter(result: Resource.Success<Triple<MovieList, MovieList, MovieList>>) {
        concatadapter.apply {
            addAdapter(
                0,
                UpcomingConcatAdapter(
                    MovieAdapter(
                        result.data.first.results,
                        this@MovieFragment
                    )
                )
            )
            addAdapter(
                1,
                TopRatedConcatAdapter(
                    MovieAdapter(
                        result.data.second.results,
                        this@MovieFragment
                    )
                )
            )
            addAdapter(
                2,
                PopularConcatAdapter(
                    MovieAdapter(
                        result.data.third.results,
                        this@MovieFragment
                    )
                )
            )
        }
    }

    override fun onMovieClick(movie: Movie) {
        Log.d("Movie", "onMovieClick: $movie")
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date
        )
        findNavController().navigate(action)
    }
}