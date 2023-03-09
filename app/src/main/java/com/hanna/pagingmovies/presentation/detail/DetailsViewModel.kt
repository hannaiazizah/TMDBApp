package com.hanna.pagingmovies.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hanna.pagingmovies.domain.model.DetailsUiModel
import com.hanna.pagingmovies.domain.usecase.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCase: GetMovieDetailsUseCase
): ViewModel() {

    fun getMovieDetails(movieId: Int): Flow<PagingData<DetailsUiModel>> {
        val params= GetMovieDetailsUseCase.Params(movieId)
        return useCase.run(params)
            .cachedIn(viewModelScope)
    }
}