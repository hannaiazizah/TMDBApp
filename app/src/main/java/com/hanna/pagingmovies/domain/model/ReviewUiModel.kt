package com.hanna.pagingmovies.domain.model

import com.hanna.pagingmovies.data.model.reviews.ReviewResponse

data class ReviewUiModel(
    val author: String,
    val username: String,
    val content: String,
    val avatar: String,
    val date: String
) {
    companion object {
        fun toUiModel(review: ReviewResponse): ReviewUiModel {
            return with(review) {
                ReviewUiModel(
                    author = this.name,
                    username = this.authorDetails.username,
                    content = this.content,
                    avatar = this.authorDetails.avatar ?: "",
                    date = this.createdDate.toUiFullDate()
                )
            }
        }
    }
}
