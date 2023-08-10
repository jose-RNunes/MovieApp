package br.com.arch.movieapp.data.remote

import java.io.IOException

abstract class DefaultException(open val errorMessage: String? = String()): IOException(errorMessage)

data class NetworkException(override val errorMessage: String): DefaultException(errorMessage)

data class UnexpectedErrorResponse(override val errorMessage: String): DefaultException(errorMessage)

data class MovieNotFound(override val errorMessage: String): DefaultException(errorMessage)