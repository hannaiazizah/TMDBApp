package com.hanna.pagingmovies.data.model.genre

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("genres") val genres: List<GenresMovieResponse>
)

data class GenresMovieResponse(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
)
