package com.hanna.pagingmovies.data.model.detail

import com.google.gson.annotations.SerializedName

data class TotalReviewResponse(
    @SerializedName("total_results") val totalReviews: Int,
)
