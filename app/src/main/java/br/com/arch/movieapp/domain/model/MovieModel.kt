package br.com.arch.movieapp.domain.model

data class MovieModel(
    val id: Long,
    val title: String,
    val imdb:String,
    val image: String,
    val poster:String,
    val rating: Float,
    val description: String
)
