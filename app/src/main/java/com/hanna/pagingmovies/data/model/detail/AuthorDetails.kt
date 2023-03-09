package com.hanna.pagingmovies.data.model.detail

import com.google.gson.annotations.SerializedName

data class AuthorDetails(
    @SerializedName("username") val username: String,
    @SerializedName("avatar_path") val avatar: String? = null
)
