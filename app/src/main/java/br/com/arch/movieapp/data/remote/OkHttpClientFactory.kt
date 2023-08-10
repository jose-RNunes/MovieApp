package br.com.arch.movieapp.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object OkHttpClientFactory {

    fun provideOkHttpClient(
        errorInterceptor: ErrorInterceptor,
        authenticateInterceptor: AuthenticateInterceptor
    ) = OkHttpClient.Builder()
        .addInterceptor(authenticateInterceptor)
        .addInterceptor(errorInterceptor)
        .addInterceptor(loggingInterceptor())
        .build()

    fun loggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}