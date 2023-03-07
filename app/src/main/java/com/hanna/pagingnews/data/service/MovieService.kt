package com.hanna.pagingnews.data.service

import com.hanna.pagingnews.data.model.MovieResponse
import com.hanna.pagingnews.data.model.ResponseItems
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    /**
     * @return List of [MovieResponse]
     *
     * @param language the language to obtain the data.
     * @param page the current page of items.
     */
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): ResponseItems<MovieResponse>
}