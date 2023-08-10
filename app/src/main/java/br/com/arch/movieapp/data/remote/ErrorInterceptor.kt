package br.com.arch.movieapp.data.remote

import br.com.arch.movieapp.R
import br.com.arch.movieapp.data.provider.ResourceProvider
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

interface ErrorInterceptor : Interceptor

class ErrorInterceptorImpl @Inject constructor(
    private val resourceProvider: ResourceProvider
) : ErrorInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val response: Response
        try {
            response = chain.proceed(request)
        } catch (exception: Exception) {
            val error = when (exception) {
                is HttpException -> NetworkException(exception.response()?.message().orEmpty())
                is UnknownHostException -> NetworkException(resourceProvider.getString(R.string.network_error))
                is IOException -> NetworkException(resourceProvider.getString(R.string.network_error))
                else -> UnexpectedErrorResponse(resourceProvider.getString(R.string.unexpected_error))
            }
            throw error
        }
        return response
    }
}