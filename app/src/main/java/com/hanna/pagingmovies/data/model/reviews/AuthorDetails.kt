package com.hanna.pagingmovies.data.model.reviews

import com.google.gson.annotations.SerializedName

data class AuthorDetails(
    @SerializedName("username") val username: String,
    @SerializedName("avatar_path") val avatar: String? = null
)
