package br.com.arch.movieapp.ui.movies.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.arch.movieapp.domain.model.MovieModel
import br.com.arch.movieapp.domain.usecase.MovieUseCase
import javax.inject.Inject

class MoviePaging @Inject constructor(
    private val movieUseCase: MovieUseCase
) : PagingSource<Int, MovieModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            val page = params.key ?: 1
            val movies = movieUseCase(page)
            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (movies.isNotEmpty()) page + 1 else null
            LoadResult.Page(movies, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? = null
}