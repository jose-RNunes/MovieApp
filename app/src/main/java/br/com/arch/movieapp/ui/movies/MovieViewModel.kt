package br.com.arch.movieapp.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import br.com.arch.movieapp.ui.movies.mapper.MovieUiModelMapper
import br.com.arch.movieapp.ui.movies.model.MovieUiModel
import br.com.arch.movieapp.ui.movies.paging.MoviePaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieUiModelMapper: MovieUiModelMapper,
    private val moviePaging: MoviePaging
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieUiState())
    val uiState: StateFlow<MovieUiState> = _uiState

    fun init() {
        val movies = Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { moviePaging }
        ).flow
            .map { paging ->
                paging.map { movieModel -> movieUiModelMapper.converter(movieModel) }
            }
            .cachedIn(viewModelScope)
        _uiState.update { state -> state.copy(movies = movies) }
    }

    fun onMovieSelected(movieUiModel: MovieUiModel) {
        _uiState.update { state ->
            state.copy(
                movieSelected = movieUiModel,
                isNavigateToDetail = true
            )
        }
    }

    fun alreadyNavigated() {
        _uiState.update { state -> state.copy(isNavigateToDetail = false) }
    }

    private companion object {
        const val PAGE_SIZE = 20
    }
}