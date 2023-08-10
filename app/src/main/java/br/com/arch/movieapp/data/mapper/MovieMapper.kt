package br.com.arch.movieapp.data.mapper

import br.com.arch.movieapp.data.remote.response.MovieResponse
import br.com.arch.movieapp.domain.model.MovieModel
import br.com.arch.movieapp.utils.Mapper
import javax.inject.Inject

interface MovieMapper: Mapper<MovieResponse, MovieModel>

class MovieMapperImpl @Inject constructor() : MovieMapper {
    override fun converter(from: MovieResponse): MovieModel {
       return MovieModel(
           id = from.id ?: 0,
           imdb = from.voteAverage.toString(),
           title = from.title.orEmpty(),
           image = from.backdropPath.orEmpty(),
           poster = from.posterPath.orEmpty(),
           rating = (from.voteAverage?.div(2))?.toFloat() ?: 0f,
           description = from.overview.orEmpty()
       )
    }
}