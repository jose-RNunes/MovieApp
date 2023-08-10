package br.com.arch.movieapp.di

import android.content.Context
import br.com.arch.movieapp.data.mapper.MovieMapper
import br.com.arch.movieapp.data.mapper.MovieMapperImpl
import br.com.arch.movieapp.data.provider.ResourceProvider
import br.com.arch.movieapp.data.provider.ResourceProviderImpl
import br.com.arch.movieapp.data.remote.AuthenticateInterceptor
import br.com.arch.movieapp.data.remote.AuthenticateInterceptorImpl
import br.com.arch.movieapp.data.remote.ErrorInterceptor
import br.com.arch.movieapp.data.remote.ErrorInterceptorImpl
import br.com.arch.movieapp.data.repository.MovieRepositoryImpl
import br.com.arch.movieapp.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun providesContext(@ApplicationContext context: Context): Context

    @Binds
    abstract fun providesResourceProvider(resourceProviderImpl: ResourceProviderImpl): ResourceProvider

    @Binds
    abstract fun bindsErrorInterceptor(errorInterceptor: ErrorInterceptorImpl): ErrorInterceptor

    @Binds
    abstract fun bindsAuthenticationInterceptor(
        authenticateInterceptorImpl: AuthenticateInterceptorImpl
    ): AuthenticateInterceptor

    @Binds
    abstract fun bindsMovieMapper(movieMapperImpl: MovieMapperImpl): MovieMapper

    @Binds
    abstract fun bindsMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository
}