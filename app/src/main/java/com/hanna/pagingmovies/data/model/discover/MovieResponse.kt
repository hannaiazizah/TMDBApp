package com.hanna.pagingmovies.data.model.discover

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title")val title: String,
    @SerializedName("overview")val overview: String,
    @SerializedName("release_date")val releaseDate: String,
    @SerializedName("vote_average")val rate: Double,
    @SerializedName("vote_count")val rateCount: Long,
    @SerializedName("poster_path") val imagePath: String? = null,
)
