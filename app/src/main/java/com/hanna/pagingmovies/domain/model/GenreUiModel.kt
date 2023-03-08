package com.hanna.pagingmovies.domain.model

import com.hanna.pagingmovies.data.model.genre.GenresMovieResponse

data class GenreUiModel(
    val id: Int,
    val name: String
) {
    companion object {
        fun toUiModel(genre: GenresMovieResponse): GenreUiModel {
            return with(genre) {
                GenreUiModel(
                    id = this.id,
                    name = this.name
                )
            }
        }
    }
}