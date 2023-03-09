package com.hanna.pagingmovies.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hanna.pagingmovies.data.core.handleApi
import com.hanna.pagingmovies.data.model.detail.MovieDetailResponse
import com.hanna.pagingmovies.data.model.detail.MovieDetailsResponse
import com.hanna.pagingmovies.data.model.discover.MovieResponse
import com.hanna.pagingmovies.data.model.genre.GenreResponse
import com.hanna.pagingmovies.data.model.detail.ReviewResponse
import com.hanna.pagingmovies.data.service.MovieService
import com.hanna.pagingmovies.data.source.MoviesPagingSource
import com.hanna.pagingmovies.data.source.DetailsPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

class MoviesRepositoryImpl(
    private val service: MovieService
) : MoviesRepository {
    override fun discoverMoviesByGenre(genreId: Int): Flow<PagingData<MovieResponse>> {
        return Pager(
            PagingConfig(pageSize = 20)
        ) {
            MoviesPagingSource(service, genreId)
        }.flow.distinctUntilChanged()
    }

    override suspend fun getGenresMovies(): Result<GenreResponse> {
        return handleApi { service.getMovieGenres() }
    }

    override fun getMoviesDetail(movieId: Int): Flow<PagingData<MovieDetailsResponse>> {
        return Pager(
            PagingConfig(
                pageSize = 20,
                initialLoadSize =1,
                prefetchDistance = 0
            )
        ) {
            DetailsPagingSource(service, movieId)
        }.flow.distinctUntilChanged()
    }
}