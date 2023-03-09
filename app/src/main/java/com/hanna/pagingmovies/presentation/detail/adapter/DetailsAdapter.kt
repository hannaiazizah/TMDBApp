package com.hanna.pagingmovies.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.hanna.pagingmovies.databinding.ItemMovieDetailsBinding
import com.hanna.pagingmovies.databinding.ItemReviewBinding
import com.hanna.pagingmovies.domain.model.DetailUiModel
import com.hanna.pagingmovies.domain.model.DetailsUiModel

class DetailsAdapter: PagingDataAdapter<DetailsUiModel, DetailsViewHolder>(DetailsDiffCallback()) {
    private var onWatchTrailerListener: ((DetailUiModel) -> Unit)? = null

    fun setupWatchTrailerListener(listener: (DetailUiModel) -> Unit) {
        onWatchTrailerListener = listener
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        when (holder) {
            is DetailViewHolder -> holder.bind(getItem(position))
            is ReviewViewHolder -> holder.bind(getItem(position))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            DETAIL_VIEW_TYPE -> {
                DetailViewHolder(
                    ItemMovieDetailsBinding.inflate(layoutInflater, parent, false),
                    onWatchTrailerListener
                )
            }
            else -> {
                ReviewViewHolder(
                    ItemReviewBinding.inflate(layoutInflater, parent, false)
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DetailUiModel -> DETAIL_VIEW_TYPE
            else -> REVIEW_VIEW_TYPE
        }
    }

    companion object {
        private const val DETAIL_VIEW_TYPE = 1
        private const val REVIEW_VIEW_TYPE = 2
    }

}