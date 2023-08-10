package br.com.arch.movieapp.data.remote.response

import com.squareup.moshi.Json

data class MovieResponse(
    val adult: Boolean?,
    val id: Long?,
    val title: String?,
    val overview: String?,
    val popularity: Double?,
    val video: Boolean?,
    @Json(name = "original_language")
    val originalLanguage: String?,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "original_title")
    val originalTitle: String?,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "media_type")
    val mediaType: String?,
    @Json(name = "release_date")
    val releaseDate: String?,
    @Json(name = "vote_average")
    val voteAverage: Double?,
    @Json(name = "vote_count")
    val voteCount: Long?
)