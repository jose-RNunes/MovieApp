package br.com.arch.movieapp.data.remote

import br.com.arch.movieapp.data.remote.response.MovieResponse
import br.com.arch.movieapp.data.remote.response.PageResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getAllMovies(@Query("page") page: Int): PageResponse

    @GET("movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") id: String): MovieResponse
}