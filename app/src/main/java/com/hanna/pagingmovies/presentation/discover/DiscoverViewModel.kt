package com.hanna.pagingmovies.presentation.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hanna.pagingmovies.domain.model.MovieUiModel
import com.hanna.pagingmovies.domain.usecase.DiscoverMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val discoverMoviesUseCase: DiscoverMoviesUseCase
): ViewModel() {
    fun fetchMovieDiscovers(genreId: Int): Flow<PagingData<MovieUiModel>> {
        val params = DiscoverMoviesUseCase.Params(genreId)
        return discoverMoviesUseCase.run(params)
            .cachedIn(viewModelScope)
    }
}