package com.hanna.pagingmovies.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.hanna.pagingmovies.data.repository.MoviesRepository
import com.hanna.pagingmovies.domain.core.BaseUseCase
import com.hanna.pagingmovies.domain.model.MovieUiModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DiscoverMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
): BaseUseCase<Flow<PagingData<MovieUiModel>>, DiscoverMoviesUseCase.Params>() {
    data class Params(
        val genreId: Int,
    )

    override suspend fun run(params: Params): Flow<PagingData<MovieUiModel>> {
        return moviesRepository.discoverMoviesByGenre(params.genreId)
            .map {
                it.map { response ->
                    MovieUiModel.toUiModel(response)
                }
            }
    }
}