package com.hanna.pagingmovies.domain.usecase

import com.hanna.pagingmovies.data.repository.MoviesRepository
import com.hanna.pagingmovies.domain.core.Either
import com.hanna.pagingmovies.domain.core.Failure
import com.hanna.pagingmovies.domain.core.UseCase
import com.hanna.pagingmovies.domain.model.DetailUiModel
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MoviesRepository
): UseCase<DetailUiModel, GetMovieDetailsUseCase.Params>() {

    data class Params(
        val movieId: Int,
    )

    override suspend fun run(params: Params): Either<Failure, DetailUiModel> {
        val result = repository.getMoviesDetail(params.movieId)
        val data = result.getOrNull()
        return if (data != null) {
            val uiModel = DetailUiModel.toUiModel(data)
            Either.success(uiModel)
        } else {
            val e = result.exceptionOrNull()
            Either.fail(Failure.ServerError(e))
        }
    }
}