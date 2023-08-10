package br.com.arch.movieapp.ui.movies.adapter

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import br.com.arch.movieapp.ui.movies.model.MovieUiModel

class MovieDiffUtil: ItemCallback<MovieUiModel>() {
    override fun areItemsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
        return oldItem.id == newItem.id
    }
}