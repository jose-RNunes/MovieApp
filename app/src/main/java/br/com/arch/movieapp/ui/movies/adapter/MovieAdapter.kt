package br.com.arch.movieapp.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import br.com.arch.movieapp.databinding.ItemMovieBinding
import br.com.arch.movieapp.ui.movies.model.MovieUiModel

class MovieAdapter(private val onMovieSelected: ((MovieUiModel) -> Unit)) : PagingDataAdapter<MovieUiModel, MovieViewHolder>(MovieDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onMovieSelected
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }
}