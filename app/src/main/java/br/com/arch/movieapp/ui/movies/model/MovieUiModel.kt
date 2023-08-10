package br.com.arch.movieapp.ui.movies.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieUiModel(
    val id: Long,
    val title: String,
    val imdb:String,
    val image: String,
    val poster:String,
    val rating: Float,
    val description: String
): Parcelable {

    fun imageUrl() = "https://image.tmdb.org/t/p/w500$image"
}