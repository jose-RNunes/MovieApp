package br.com.arch.movieapp.di

import br.com.arch.movieapp.data.remote.AuthenticateInterceptor
import br.com.arch.movieapp.data.remote.ErrorInterceptor
import br.com.arch.movieapp.data.remote.MovieApi
import br.com.arch.movieapp.data.remote.RetrofitFactory
import br.com.arch.movieapp.data.remote.MoshiFactory
import br.com.arch.movieapp.data.remote.OkHttpClientFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Singleton
    @Provides
    fun providesRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return RetrofitFactory.provideRetrofit(
            baseUrl = "https://api.themoviedb.org/3/",
            moshi = moshi,
            client = okHttpClient
        )
    }

    @Singleton
    @Provides
    fun providesMoshi(): Moshi {
        return MoshiFactory.build()
    }

    @Provides
    fun providesHttpClient(
        errorInterceptor: ErrorInterceptor,
        authenticateInterceptor: AuthenticateInterceptor
    ): OkHttpClient {
        return OkHttpClientFactory.provideOkHttpClient(errorInterceptor, authenticateInterceptor)
    }
}