package com.hanna.pagingmovies.data.service

import com.hanna.pagingmovies.data.model.genre.GenreResponse
import com.hanna.pagingmovies.data.model.discover.MovieResponse
import com.hanna.pagingmovies.data.model.ResponseItems
import com.hanna.pagingmovies.data.model.detail.MovieDetailResponse
import com.hanna.pagingmovies.data.model.reviews.ReviewResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("genre/movie/list")
    suspend fun getMovieGenres(): Response<GenreResponse>

    @GET("discover/movie")
    suspend fun discoverMoviesByGenre(
        @Query("with_genre") genreId: Int,
        @Query("page") page: Int
    ): ResponseItems<MovieResponse>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int
    ): ResponseItems<ReviewResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("append_to_response") appendToResponse: String
    ): Response<MovieDetailResponse>
}