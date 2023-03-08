package com.hanna.pagingmovies.domain.model

import com.hanna.pagingmovies.data.model.detail.MovieDetailResponse

data class DetailUiModel(
    val id: Int,
    val title: String,
    val backgroundImage: String,
    val overview: String,
    val status: String,
    val totalReview: Int,
    val genre: List<GenreUiModel>,
    val rate: Double,
    val rateCount: Int,
    val releaseDate: String,
    val videoUrl: String?
) {
    companion object {
        fun toUiModel(detailResponse: MovieDetailResponse): DetailUiModel {
            return with(detailResponse) {
                DetailUiModel(
                    id = this.id,
                    title = this.title,
                    overview = this.overview,
                    status = this.status,
                    totalReview = this.reviews.totalReviews,
                    genre = this.genres.genres.map { GenreUiModel.toUiModel(it) },
                    backgroundImage = "https://image.tmdb.org/t/p/w500" + this.imagePath,
                    rate = this.rate,
                    rateCount = this.rateCount,
                    releaseDate = this.releaseDate.toUiDate(),
                    videoUrl = getTrailerUrl(this)
                )
            }
        }

        private fun getTrailerUrl(detailResponse: MovieDetailResponse): String? {
            val video = detailResponse.videos.results
            val trailer = video.find { it.type == "Trailer" }
            if (trailer != null && trailer.site.contains("Youtube", true)) {
                return "https://www.youtube.com/watch?v=" + trailer.key
            }
            return null
        }
    }
}
