package com.hanna.pagingmovies.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.hanna.pagingmovies.data.model.detail.MovieDetailResponse
import com.hanna.pagingmovies.data.model.detail.ReviewResponse
import com.hanna.pagingmovies.data.repository.MoviesRepository
import com.hanna.pagingmovies.domain.core.BaseUseCase
import com.hanna.pagingmovies.domain.model.DetailUiModel
import com.hanna.pagingmovies.domain.model.DetailsUiModel
import com.hanna.pagingmovies.domain.model.ReviewUiModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MoviesRepository
): BaseUseCase<Flow<PagingData<DetailsUiModel>>, GetMovieDetailsUseCase.Params>() {

    data class Params(
        val movieId: Int,
    )

    override fun run(params: Params): Flow<PagingData<DetailsUiModel>> {
        return repository.getMoviesDetail(params.movieId)
            .map {
                it.map { response ->
                    when(response) {
                        is MovieDetailResponse -> DetailUiModel.toUiModel(response)
                        is ReviewResponse -> ReviewUiModel.toUiModel(response)
                    }
                }
            }
    }
}