package com.hanna.pagingmovies.domain.model

import com.hanna.pagingmovies.data.model.discover.MovieResponse

data class MovieUiModel(
    val title: String,
    val posterUrl: String,
    val vote: Double,
    val id: Int
) {
    companion object {
        fun toUiModel(movie: MovieResponse): MovieUiModel {
            return with(movie) {
                MovieUiModel(
                    id = this.id,
                    title = this.title,
                    posterUrl = "https://image.tmdb.org/t/p/w500" + this.imagePath,
                    vote = this.rate
                )
            }
        }
    }
}
