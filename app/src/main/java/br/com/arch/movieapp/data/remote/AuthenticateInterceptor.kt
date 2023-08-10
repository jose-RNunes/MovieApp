package br.com.arch.movieapp.data.remote

import br.com.arch.movieapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

interface AuthenticateInterceptor : Interceptor

class AuthenticateInterceptorImpl @Inject constructor() : AuthenticateInterceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val requestBuilder = request.newBuilder()
            .addHeader(ACCEPT_KEY, ACCEPT_VALUE)
            .addHeader(AUTHORIZATION_KEY, BuildConfig.TMDB_API_KEY)
            .build()

        return chain.proceed(requestBuilder)
    }

    private companion object {
        const val ACCEPT_KEY = "accept"
        const val ACCEPT_VALUE = "application/json"
        const val AUTHORIZATION_KEY = "Authorization"
    }
}