package br.com.arch.movieapp.data.repository

import br.com.arch.movieapp.data.mapper.MovieMapper
import br.com.arch.movieapp.data.remote.MovieApi
import br.com.arch.movieapp.domain.model.MovieModel
import br.com.arch.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieMapper: MovieMapper
) : MovieRepository {

    override suspend fun getAllMovies(page: Int): List<MovieModel> {
        return movieApi.getAllMovies(page).results?.map { movieResponse ->
            movieMapper.converter(movieResponse)
        }.orEmpty()
    }
}