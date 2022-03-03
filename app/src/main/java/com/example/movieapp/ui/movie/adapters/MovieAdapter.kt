package com.example.movieapp.ui.movie.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.core.BaseViewHolder
import com.example.movieapp.data.model.Movie
import com.example.movieapp.databinding.MovieItemBinding

@Suppress("DEPRECATION")
class MovieAdapter(private val moviesList: List<Movie>,
                   private val itemClickListener: OnMovieClickListener): RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnMovieClickListener {
        fun onMovieClick(movie: Movie)
    }

    // Sobreescribimos los metodos del Adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MoviesViewHolder(itemBinding, parent.context)

        // Le indicamos que cuando clicken en la imagen que haga una acción
        itemBinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.onMovieClick(moviesList[position])
        }
        // devolvemos el holder
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder) {
            is MoviesViewHolder -> holder.bind(moviesList[position])
        }
    }

    // Devuelves el numero de elementos que nos vienen
    override fun getItemCount(): Int = moviesList.size

    private inner class MoviesViewHolder(val binding: MovieItemBinding, val context: Context): BaseViewHolder<Movie>(binding.root){
        override fun bind(item: Movie) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500/${item.poster_path}").centerCrop().into(binding.imgMovie)
        }
    }
}