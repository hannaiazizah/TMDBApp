package com.hanna.pagingmovies.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.hanna.pagingmovies.data.repository.MoviesRepository
import com.hanna.pagingmovies.domain.core.BaseSuspendUseCase
import com.hanna.pagingmovies.domain.core.BaseUseCase
import com.hanna.pagingmovies.domain.model.ReviewUiModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetReviewsUseCase @Inject constructor(
    private val repository: MoviesRepository
): BaseUseCase<Flow<PagingData<ReviewUiModel>>, GetReviewsUseCase.Params>() {
    data class Params(
        val movieId: Int,
    )

    override fun run(params: Params): Flow<PagingData<ReviewUiModel>> {
        return repository.getReviews(params.movieId)
            .map {
                it.map { response ->
                    ReviewUiModel.toUiModel(response)
                }
            }
    }
}