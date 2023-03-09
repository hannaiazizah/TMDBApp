package com.hanna.pagingmovies.domain.model

import com.hanna.pagingmovies.data.model.detail.ReviewResponse

data class ReviewUiModel(
    val id: String,
    val author: String,
    val username: String,
    val content: String,
    val avatar: String,
    val date: String
): DetailsUiModel() {
    companion object {
        fun toUiModel(review: ReviewResponse): ReviewUiModel {
            return with(review) {
                ReviewUiModel(
                    id = this.id,
                    author = this.name,
                    username = this.authorDetails.username,
                    content = this.content,
                    avatar = this.authorDetails.avatar?.substring(1) ?: "",
                    date = this.createdDate.toUiFullDate()
                )
            }
        }
    }
}
