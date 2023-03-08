package com.hanna.pagingmovies.data.model.detail

import com.google.gson.annotations.SerializedName
import com.hanna.pagingmovies.data.model.ResponseItems
import com.hanna.pagingmovies.data.model.genre.GenreResponse

data class MovieDetailResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title")val title: String,
    @SerializedName("overview")val overview: String,
    @SerializedName("release_date")val releaseDate: String,
    @SerializedName("vote_average")val rate: Double,
    @SerializedName("vote_count")val rateCount: Int,
    @SerializedName("backdrop_path") val imagePath: String,
    @SerializedName("genres") val genres: GenreResponse,
    @SerializedName("status") val status: String,
    @SerializedName("videos") val videos: ResponseItems<VideoResponse>,
    @SerializedName("reviews") val reviews: TotalReviewResponse
)
