package br.com.arch.movieapp.data.remote.response

data class PageResponse(
    val id: Int?,
    val page: Int?,
    val results: List<MovieResponse>?
)