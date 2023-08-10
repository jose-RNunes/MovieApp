package br.com.arch.movieapp.di

import br.com.arch.movieapp.ui.movies.mapper.MovieUiModelMapper
import br.com.arch.movieapp.ui.movies.mapper.MovieUiModelMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UiModule {

    @Binds
    abstract fun bindsMovieUiModelMapper(
        movieUiModelMapperImpl: MovieUiModelMapperImpl
    ): MovieUiModelMapper
}