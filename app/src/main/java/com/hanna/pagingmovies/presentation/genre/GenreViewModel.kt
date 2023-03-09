package com.hanna.pagingmovies.presentation.genre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanna.pagingmovies.domain.core.BaseUseCase
import com.hanna.pagingmovies.domain.core.Either
import com.hanna.pagingmovies.domain.core.Failure
import com.hanna.pagingmovies.domain.model.GenreUiModel
import com.hanna.pagingmovies.domain.usecase.GetGenresUseCase
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class GenreViewModel @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase
): ViewModel() {
    private val _genreResult by lazy {
        MutableStateFlow<Either<Failure, List<GenreUiModel>>>(Either.fail(Failure.Empty))
    }
    val genreResult: StateFlow<Either<Failure, List<GenreUiModel>>> = _genreResult

    private val _isLoading by lazy { MutableSharedFlow<Boolean>() }
    val isLoading: SharedFlow<Boolean> = _isLoading

    fun getGenres() {
        viewModelScope.launch {
            _isLoading.emit(true)
            val result = getGenresUseCase.run(BaseUseCase.None())
            _genreResult.emit(result)
            _isLoading.emit(false)
        }
    }

}