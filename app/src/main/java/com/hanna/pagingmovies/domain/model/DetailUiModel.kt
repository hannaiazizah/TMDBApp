package com.hanna.pagingmovies.domain.model

import com.hanna.pagingmovies.data.model.detail.MovieDetailResponse

data class DetailUiModel(
    val id: Int,
    val title: String,
    val backgroundImage: String,
    val overview: String,
    val status: String,
    val totalReview: String,
    val genre: List<GenreUiModel>,
    val rate: Double,
    val rateCount: Int,
    val releaseDate: String,
    val videoKey: String?
): DetailsUiModel() {
    companion object {
        fun toUiModel(detailResponse: MovieDetailResponse): DetailUiModel {
            return with(detailResponse) {
                DetailUiModel(
                    id = this.id,
                    title = this.title,
                    overview = this.overview ?: "",
                    status = this.status,
                    totalReview = "See ${this.reviews.totalReviews} review(s)",
                    genre = this.genres.map { GenreUiModel.toUiModel(it) },
                    backgroundImage = "https://image.tmdb.org/t/p/w500" + this.imagePath,
                    rate = this.rate,
                    rateCount = this.rateCount,
                    releaseDate = "Release date: ${this.releaseDate.toUiDate()}",
                    videoKey = getTrailerUrl(this)
                )
            }
        }

        private fun getTrailerUrl(detailResponse: MovieDetailResponse): String? {
            val video = detailResponse.videos.results
            val trailer = video.find { it.type == "Trailer" }
            return trailer?.key
        }
    }
}
