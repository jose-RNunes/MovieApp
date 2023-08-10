package br.com.arch.movieapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.arch.movieapp.domain.model.MovieModel
import br.com.arch.movieapp.ui.movies.mapper.MovieUiModelMapper
import br.com.arch.movieapp.ui.movies.model.MovieUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState> = _uiState

    fun setMovie(movieUiModel: MovieUiModel?) {
        _uiState.update { state -> state.copy(movieUiModel = movieUiModel) }
    }
}