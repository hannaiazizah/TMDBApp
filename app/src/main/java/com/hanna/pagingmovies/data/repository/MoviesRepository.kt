package com.hanna.pagingmovies.data.repository

import androidx.paging.PagingData
import com.hanna.pagingmovies.data.model.detail.MovieDetailResponse
import com.hanna.pagingmovies.data.model.detail.MovieDetailsResponse
import com.hanna.pagingmovies.data.model.genre.GenreResponse
import com.hanna.pagingmovies.data.model.discover.MovieResponse
import com.hanna.pagingmovies.data.model.detail.ReviewResponse
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun discoverMoviesByGenre(genreId: Int): Flow<PagingData<MovieResponse>>

    suspend fun getGenresMovies(): Result<GenreResponse>

    fun getMoviesDetail(movieId: Int): Flow<PagingData<MovieDetailsResponse>>
}