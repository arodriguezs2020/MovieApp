package com.example.movieapp.ui.movie.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.core.BaseConcatHolder
import com.example.movieapp.databinding.PopularMoviesRowBinding
import com.example.movieapp.ui.movie.adapters.MovieAdapter

// ---
//      Creamos un adaptador para cada una de las llamadas a la API y luego las juntaremos todas
// ---
class PopularConcatAdapter(private val moviesAdapter: MovieAdapter): RecyclerView.Adapter<BaseConcatHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding = PopularMoviesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    // Bindeamos el adaptador
    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder) {
            is ConcatViewHolder -> holder.bind(moviesAdapter)
        }
    }

    // Devolvemos el total de la lista que en cada una va a ser 1
    override fun getItemCount(): Int  = 1

    private inner class ConcatViewHolder(val binding: PopularMoviesRowBinding): BaseConcatHolder<MovieAdapter>(binding.root) {
        override fun bind(adapter: MovieAdapter) {
            binding.rvPopularMovies.adapter = adapter
        }
    }
}