package com.hanna.pagingmovies.domain.usecase

import com.hanna.pagingmovies.data.repository.MoviesRepository
import com.hanna.pagingmovies.domain.core.BaseSuspendUseCase
import com.hanna.pagingmovies.domain.model.GenreUiModel
import com.hanna.pagingmovies.domain.core.Either
import com.hanna.pagingmovies.domain.core.Failure
import com.hanna.pagingmovies.domain.core.UseCase
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val repository: MoviesRepository
): UseCase<List<GenreUiModel>, BaseSuspendUseCase.None>() {
    override suspend fun run(params: None): Either<Failure, List<GenreUiModel>> {
        val result = repository.getGenresMovies()
        val data = result.getOrNull()
        return if (data != null) {
            val uiModel = data.genres.map {
                GenreUiModel.toUiModel(it)
            }
            Either.success(uiModel)
        } else {
            val e = result.exceptionOrNull()
            Either.fail(Failure.ServerError(e))
        }
    }

}