package br.com.arch.movieapp.ui.movies.mapper

import br.com.arch.movieapp.domain.model.MovieModel
import br.com.arch.movieapp.ui.movies.model.MovieUiModel
import br.com.arch.movieapp.utils.Mapper
import javax.inject.Inject

interface MovieUiModelMapper : Mapper<MovieModel, MovieUiModel>

class MovieUiModelMapperImpl @Inject constructor() : MovieUiModelMapper {
    override fun converter(from: MovieModel): MovieUiModel {
        return MovieUiModel(
            id = from.id,
            title = from.title,
            imdb = from.imdb,
            image = from.image,
            poster = from.poster,
            rating = from.rating,
            description = from.description
        )
    }
}