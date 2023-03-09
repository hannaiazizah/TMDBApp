package com.hanna.pagingmovies.presentation.detail.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hanna.pagingmovies.domain.model.DetailUiModel
import com.hanna.pagingmovies.domain.model.DetailsUiModel
import com.hanna.pagingmovies.domain.model.ReviewUiModel

class DetailsDiffCallback: DiffUtil.ItemCallback<DetailsUiModel>() {
    override fun areItemsTheSame(oldItem: DetailsUiModel, newItem: DetailsUiModel): Boolean {
        return isReviewUiModelTheSame(oldItem, newItem) ||
                isDetailUiModelTheSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: DetailsUiModel, newItem: DetailsUiModel): Boolean {
        return oldItem == newItem
    }

    private fun isReviewUiModelTheSame(
        oldItem: DetailsUiModel,
        newItem: DetailsUiModel
    ): Boolean {
        return oldItem is ReviewUiModel &&
                newItem is ReviewUiModel &&
                oldItem.id == newItem.id
    }

    private fun isDetailUiModelTheSame(
        oldItem: DetailsUiModel,
        newItem: DetailsUiModel
    ): Boolean {
        return oldItem is DetailUiModel &&
                newItem is DetailUiModel &&
                oldItem.id == newItem.id
    }
}