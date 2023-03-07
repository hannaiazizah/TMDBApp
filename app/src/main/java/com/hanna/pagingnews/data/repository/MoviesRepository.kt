package com.hanna.pagingnews.data.repository

import androidx.paging.PagingData
import com.hanna.pagingnews.data.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getMovies(): Flow<PagingData<MovieResponse>>
}