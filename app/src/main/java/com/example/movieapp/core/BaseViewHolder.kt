package com.example.movieapp.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

// Esta clase se utilizará en varias clases y por eso se crea en core
abstract class BaseViewHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}