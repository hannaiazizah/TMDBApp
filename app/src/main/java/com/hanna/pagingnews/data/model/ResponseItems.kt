package com.hanna.pagingnews.data.model

import com.google.gson.annotations.SerializedName

data class ResponseItems<T>(
    @field:SerializedName("results") val results: List<T>
)