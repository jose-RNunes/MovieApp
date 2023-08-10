package br.com.arch.movieapp.ui.movies.adapter

import androidx.recyclerview.widget.RecyclerView
import br.com.arch.movieapp.databinding.ItemMovieBinding
import br.com.arch.movieapp.ui.movies.model.MovieUiModel
import br.com.arch.movieapp.utils.loadImageView

class MovieViewHolder(
    private val binding: ItemMovieBinding,
    private val onMovieSelected: ((MovieUiModel) -> Unit)
): RecyclerView.ViewHolder(binding.root) {

    fun bind(movieUiModel: MovieUiModel) {
        binding.verticalImage.loadImageView(movieUiModel.imageUrl())
        binding.verticalTitle.text = movieUiModel.title
        binding.verticalImdb.text = movieUiModel.imdb
        binding.root.setOnClickListener {
            onMovieSelected.invoke(movieUiModel)
        }
    }
}