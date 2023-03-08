package com.hanna.pagingmovies.data.model.detail

import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("key") val key: String,
    @SerializedName("type") val type: String,
    @SerializedName("site") val site: String
)
