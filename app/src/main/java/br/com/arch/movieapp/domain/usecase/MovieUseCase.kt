package br.com.arch.movieapp.domain.usecase

import br.com.arch.movieapp.domain.model.MovieModel
import br.com.arch.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(page: Int): List<MovieModel> {
        return movieRepository.getAllMovies(page)
    }
}