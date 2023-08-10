package br.com.arch.movieapp.domain.repository

import br.com.arch.movieapp.domain.model.MovieModel

interface MovieRepository {

    suspend fun getAllMovies(page: Int): List<MovieModel>
}