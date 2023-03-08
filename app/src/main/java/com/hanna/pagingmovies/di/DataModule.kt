package com.hanna.pagingmovies.di

import android.content.Context
import com.hanna.pagingmovies.BuildConfig
import com.hanna.pagingmovies.data.core.ApiInterceptor
import com.hanna.pagingmovies.data.repository.MoviesRepository
import com.hanna.pagingmovies.data.repository.MoviesRepositoryImpl
import com.hanna.pagingmovies.data.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return ApiInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        apiInterceptor: Interceptor
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val cacheDir = context.cacheDir
        val cacheSize = 10L * 1024L * 1024L // 10 MiB

        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder
            .cache(Cache(cacheDir, cacheSize))
            .addInterceptor(loggingInterceptor)
            .addInterceptor(apiInterceptor)
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)

        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.TMDB_API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieService(
        retrofit: Retrofit
    ): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        service: MovieService
    ): MoviesRepository {
        return MoviesRepositoryImpl(service)
    }
}