package br.com.arch.movieapp.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import br.com.arch.movieapp.databinding.FragmentMoviesBinding
import br.com.arch.movieapp.ui.MainActivity
import br.com.arch.movieapp.ui.detail.MovieDetailFragment
import br.com.arch.movieapp.ui.movies.adapter.MovieAdapter
import br.com.arch.movieapp.ui.movies.model.MovieUiModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private var binding: FragmentMoviesBinding? = null

    private val viewModel: MovieViewModel by viewModels()

    private val movieAdapter = MovieAdapter { movieUiModel ->
        viewModel.onMovieSelected(movieUiModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMoviesBinding.inflate(inflater, container, false)
            .also { binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()

        setupObservers()

        viewModel.init()
    }

    private fun bindViews() = binding?.run {
        rvMovies.adapter = movieAdapter

        movieAdapter.addLoadStateListener { state ->
             when(state.refresh) {
                is LoadState.Loading -> {
                    progress.isVisible = true
                }
                is LoadState.Error -> {
                    progress.isVisible = false
                    tvError.isVisible = true
                    tvError.text = (state.refresh as LoadState.Error).error.message
                }
                is LoadState.NotLoading -> {
                    progress.isVisible = false
                }
             }
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { state ->
                    if (state.isNavigateToDetail) {
                        navigateToDetail(state.movieSelected)
                    } else {
                        state.movies?.collectLatest { movies ->
                            movieAdapter.submitData(movies)
                        }
                    }
                }
            }
        }
    }

    private fun navigateToDetail(movieUiModel: MovieUiModel?) {
        movieUiModel?.let {
            val fragment = MovieDetailFragment.newInstance(movieUiModel)
            (activity as? MainActivity)?.replaceFragment(fragment, MOVIE_DETAIL_TAG)
        }
        viewModel.alreadyNavigated()
    }

    private companion object {
        const val MOVIE_DETAIL_TAG = "MOVIE_DETAIL_TAG"
    }
}