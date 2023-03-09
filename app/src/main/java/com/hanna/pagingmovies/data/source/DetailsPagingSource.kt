package com.hanna.pagingmovies.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hanna.pagingmovies.data.core.handleApi
import com.hanna.pagingmovies.data.model.detail.MovieDetailsResponse
import com.hanna.pagingmovies.data.service.MovieService
import java.io.IOException
import retrofit2.HttpException

class DetailsPagingSource(
    private val service: MovieService,
    private val movieId: Int
) : PagingSource<Int, MovieDetailsResponse>() {
    /**
     * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
     */
    override fun getRefreshKey(state: PagingState<Int, MovieDetailsResponse>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDetailsResponse> {
        val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX
        val data = mutableListOf<MovieDetailsResponse>()
        return try {
            if (pageIndex == TMDB_STARTING_PAGE_INDEX) {
                val detail = service.getMovieDetails(
                    movieId,
                    "videos,reviews"
                )
                if (detail.isSuccessful && detail.body() != null) {
                    data.add(detail.body()!!)
                }
            }
            val response = service.getMovieReviews(
                movieId = movieId,
                page = pageIndex
            )
            val reviews = response.results
            data.addAll(reviews)
            val nextKey =
                if (reviews.isEmpty()) {
                    null
                } else {
                    // By default, initial load size = 3 * NETWORK PAGE SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                    pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
                }
            LoadResult.Page(
                data = data,
                prevKey = if (pageIndex == TMDB_STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    companion object {
        private const val TMDB_STARTING_PAGE_INDEX = 1
        private const val NETWORK_PAGE_SIZE = 20
    }

}