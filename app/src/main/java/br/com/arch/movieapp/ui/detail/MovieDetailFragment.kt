package br.com.arch.movieapp.ui.detail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import br.com.arch.movieapp.databinding.FragmentMovieDetailBinding
import br.com.arch.movieapp.ui.movies.model.MovieUiModel
import br.com.arch.movieapp.utils.loadImageView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieDetailFragment: Fragment() {

    private var binding: FragmentMovieDetailBinding? = null

    private val viewModel: MovieDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMovieDetailBinding.inflate(layoutInflater, container, false)
            .also { binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()

        setupObservers()

        viewModel.setMovie(getMovieExtra())
    }

    private fun setupListeners() {
        binding?.toolbar?.setNavigationOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { state ->
                    bindMovie(state.movieUiModel)
                }
            }
        }
    }

    private fun bindMovie(movieUiModel: MovieUiModel?) = binding?.run {
        val safeMovie = movieUiModel ?: return@run
        imageDetail.loadImageView(safeMovie.imageUrl())
        tvRealName.text = safeMovie.title
        rbRating.rating = safeMovie.rating
        tvOverview.text = safeMovie.description
    }

    private fun getMovieExtra(): MovieUiModel? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(MOVIE_EXTRA, MovieUiModel::class.java)
        } else {
            arguments?.getParcelable(MOVIE_EXTRA) as? MovieUiModel
        }
    }

    companion object {
        private const val MOVIE_EXTRA = "movie_extra"
        fun newInstance(movieUiModel: MovieUiModel): MovieDetailFragment {
            return MovieDetailFragment().apply {
                arguments = bundleOf(MOVIE_EXTRA to movieUiModel)
            }
        }
    }
}