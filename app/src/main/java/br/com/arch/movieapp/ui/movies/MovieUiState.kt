package br.com.arch.movieapp.ui.movies

import androidx.paging.PagingData
import br.com.arch.movieapp.ui.movies.model.MovieUiModel
import kotlinx.coroutines.flow.Flow

data class MovieUiState(
    val movies: Flow<PagingData<MovieUiModel>>? = null,
    val movieSelected: MovieUiModel? = null,
    val isNavigateToDetail: Boolean = false
)