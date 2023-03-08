package com.hanna.pagingmovies.data.model.reviews

import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    @SerializedName("id") val id: String,
    @SerializedName("author") val name: String,
    @SerializedName("content") val content: String,
    @SerializedName("created_at") val createdDate: String,
    @SerializedName("author_details") val authorDetails: AuthorDetails
)
